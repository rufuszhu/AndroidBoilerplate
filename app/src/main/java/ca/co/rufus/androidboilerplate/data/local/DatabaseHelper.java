package ca.co.rufus.androidboilerplate.data.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.Collection;
import java.util.List;

import ca.co.rufus.androidboilerplate.data.model.Owner;
import ca.co.rufus.androidboilerplate.data.model.RepoOwnerJoin;
import ca.co.rufus.androidboilerplate.data.model.Repository;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import timber.log.Timber;

/**
 * Created by rufus on 2016-01-07.
 */
public class DatabaseHelper {

    private BriteDatabase mDb;

    public DatabaseHelper(Context context) {
        mDb = SqlBrite.create().wrapDatabaseHelper(new DbOpenHelper(context));
    }

    public BriteDatabase getBriteDb() {
        return mDb;
    }

    /**
     * Remove all the data from all the tables in the database.
     */
    public Observable<Void> clearTables() {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    Cursor cursor = mDb.query("SELECT name FROM sqlite_master WHERE type='table'");
                    while (cursor.moveToNext()) {
                        mDb.delete(cursor.getString(cursor.getColumnIndex("name")), null);
                    }
                    cursor.close();
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }


    public Observable<Repository> setReposToDb(final Collection<Repository> newRepos) {
        return Observable.create(new Observable.OnSubscribe<Repository>() {
            @Override
            public void call(Subscriber<? super Repository> subscriber) {
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Repository.TABLE, null);

                    for (Repository repo : newRepos) {
                        long result = mDb.insert(Repository.TABLE,
                                Repository.toContentValues(repo),
                                SQLiteDatabase.CONFLICT_REPLACE);
                        if (result >= 0) {
                            mDb.insert(Owner.TABLE,
                                    Owner.toContentValues(repo.owner()),
                                    SQLiteDatabase.CONFLICT_REPLACE);
                            subscriber.onNext(repo);
                        }
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<List<RepoOwnerJoin>> getReposJoinsFromDb() {
        Timber.i(RepoOwnerJoin.QUERY);
        return mDb.createQuery(RepoOwnerJoin.TABLES, RepoOwnerJoin.QUERY).mapToList(RepoOwnerJoin.MAPPER);
    }
}
