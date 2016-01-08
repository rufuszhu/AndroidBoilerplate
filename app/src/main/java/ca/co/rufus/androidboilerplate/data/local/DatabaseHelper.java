package ca.co.rufus.androidboilerplate.data.local;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.Collection;
import java.util.List;

import ca.co.rufus.androidboilerplate.data.model.Repository;
import ca.co.rufus.androidboilerplate.data.model.User;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by rufus on 2016-01-07.
 */
public class DatabaseHelper {
    public Observable<Repository> setReposToDb(final Collection<Repository> newRibots) {
        return Observable.create(new Observable.OnSubscribe<Repository>() {
            @Override
            public void call(Subscriber<? super Repository> subscriber) {
                ActiveAndroid.beginTransaction();
                try {
                    new Delete().from(User.class).execute();
                    new Delete().from(Repository.class).execute();

                    for (Repository repository : newRibots) {
                        repository.owner.save();
                        repository.save();
                        subscriber.onNext(repository);
                    }

                    subscriber.onCompleted();
                } finally {
                    ActiveAndroid.endTransaction();
                }
            }
        });
    }

    public Observable<List<Repository>> getReposFromDb() {
        return Observable.create(new Observable.OnSubscribe<List<Repository>>() {
            @Override
            public void call(Subscriber<? super List<Repository>> subscriber) {
                subscriber.onNext((List) new Select().from(Repository.class).execute());
                subscriber.onCompleted();
            }
        });
    }
}
