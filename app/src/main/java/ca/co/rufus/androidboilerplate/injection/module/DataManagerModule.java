package ca.co.rufus.androidboilerplate.injection.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;

import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Singleton;

import ca.co.rufus.androidboilerplate.R;
import ca.co.rufus.androidboilerplate.data.local.DatabaseHelper;
import ca.co.rufus.androidboilerplate.data.local.DbOpenHelper;
import ca.co.rufus.androidboilerplate.injection.scope.PerDataManager;
import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

import static android.content.Context.MODE_PRIVATE;

/**
 * Provide dependencies to the DataManager, mainly Helper classes and Retrofit services.
 */
@Module
public class DataManagerModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application app) {
        return app.getSharedPreferences(app.getApplicationContext().getString(R.string.app_name), MODE_PRIVATE);
    }

    @Provides
    @Singleton
    RxSharedPreferences provideRxSharedPreferences(SharedPreferences prefs) {
        return RxSharedPreferences.create(prefs);
    }

    @Provides
    @Singleton
    SQLiteOpenHelper provideOpenHelper(Application application) {
        return new DbOpenHelper(application);
    }

    @Provides
    @Singleton
    SqlBrite provideSqlBrite() {
        return SqlBrite.create(new SqlBrite.Logger() {
            @Override
            public void log(String message) {
                Timber.tag("Database").v(message);
            }
        });
    }

    @Provides
    @Singleton
    BriteDatabase provideDatabase(SqlBrite sqlBrite, SQLiteOpenHelper helper) {
        return sqlBrite.wrapDatabaseHelper(helper);
    }

    @Provides
    @PerDataManager
    DatabaseHelper provideDatabaseHelper(Application application) {
        return new DatabaseHelper(application);
    }
}