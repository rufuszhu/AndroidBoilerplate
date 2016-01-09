package ca.co.rufus.androidboilerplate.injection.module;

import com.squareup.okhttp.HttpUrl;

import javax.inject.Singleton;

import ca.co.rufus.androidboilerplate.data.remote.GithubService;
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
    GithubService provideRibotsService() {
        return GithubService.Creator.newGithubService();
    }


}
