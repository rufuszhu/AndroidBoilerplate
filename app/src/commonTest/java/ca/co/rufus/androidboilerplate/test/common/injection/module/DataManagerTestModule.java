package ca.co.rufus.androidboilerplate.test.common.injection.module;

import android.content.Context;

import ca.co.rufus.androidboilerplate.data.remote.GithubService;
import dagger.Module;
import dagger.Provides;
import ca.co.rufus.androidboilerplate.data.local.DatabaseHelper;
import ca.co.rufus.androidboilerplate.data.local.PreferencesHelper;
import ca.co.rufus.androidboilerplate.injection.scope.PerDataManager;

import static org.mockito.Mockito.mock;

/**
 * Provides dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary
 */
@Module
public class DataManagerTestModule {

    private final Context mContext;

    public DataManagerTestModule(Context context) {
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
    GithubService provideRibotsService() {
        return mock(GithubService.class);
    }
}
