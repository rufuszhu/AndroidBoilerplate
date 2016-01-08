package ca.co.rufus.androidboilerplate.injection.module;

import android.content.Context;
import android.content.SharedPreferences;

import ca.co.rufus.androidboilerplate.data.remote.GitHubService;
import dagger.Module;
import dagger.Provides;
import ca.co.rufus.androidboilerplate.data.local.DatabaseHelper;
import ca.co.rufus.androidboilerplate.data.local.PreferencesHelper;
import ca.co.rufus.androidboilerplate.injection.scope.PerDataManager;

/**
 * Provide dependencies to the DataManager, mainly Helper classes and Retrofit services.
 */
@Module
public class DataManagerModule {

    private final Context mContext;

    public DataManagerModule(Context context) {
        mContext = context;
    }


    @Provides
    @PerDataManager
    DatabaseHelper provideDatabaseHelper() {
        return new DatabaseHelper(mContext);
    }

    @Provides
    @PerDataManager
    PreferencesHelper providePreferencesHelper() {
        return new PreferencesHelper(mContext);
    }

    @Provides
    @PerDataManager
    SharedPreferences provideSharedPreferences(PreferencesHelper preferencesHelper) {
        return preferencesHelper.getSharePreference();
    }

    @Provides
    @PerDataManager
    GitHubService provideRibotsService() {
        return GitHubService.Creator.newRibotsService();
    }
}
