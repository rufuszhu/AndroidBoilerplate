package ca.co.rufus.androidboilerplate.injection.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.squareup.okhttp.HttpUrl;

import javax.inject.Singleton;

import ca.co.rufus.androidboilerplate.data.local.DatabaseHelper;
import ca.co.rufus.androidboilerplate.data.local.PreferencesHelper;
import ca.co.rufus.androidboilerplate.data.remote.GitHubService;
import ca.co.rufus.androidboilerplate.injection.scope.PerDataManager;
import dagger.Module;
import dagger.Provides;

/**
 * Provide dependencies to the DataManager, mainly Helper classes
 */
@Module
public class NetworkModule {

    public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("https://api.github.com/");

    @Provides
    @Singleton
    HttpUrl provideBaseUrl() {
        return PRODUCTION_API_URL;
    }

    @Provides
    @PerDataManager
    GitHubService provideRibotsService() {
        return GitHubService.Creator.newRibotsService();
    }
}
