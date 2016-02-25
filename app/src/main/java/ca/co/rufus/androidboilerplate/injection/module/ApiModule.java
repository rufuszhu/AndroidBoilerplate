package ca.co.rufus.androidboilerplate.injection.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ca.co.rufus.androidboilerplate.data.model.AutoValueAdapterFactory;
import ca.co.rufus.androidboilerplate.data.remote.GithubService;
import ca.co.rufus.androidboilerplate.injection.scope.PerDataManager;
import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by rzhu on 1/8/2016.
 */
@Module
public class ApiModule {
    public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("https://api.github.com/");

    @Provides
    @PerDataManager
    HttpUrl provideBaseUrl() {
        return PRODUCTION_API_URL;
    }

    @Provides
    @PerDataManager
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(10, SECONDS)
                .readTimeout(10, SECONDS)
                .writeTimeout(10, SECONDS)
                .addInterceptor(logging)
                .addInterceptor(new StethoInterceptor())
                .build();
    }

    @Provides
    @PerDataManager
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .registerTypeAdapterFactory(new AutoValueAdapterFactory())
                .create();
    }

    @Provides
    @PerDataManager
    Retrofit provideRetrofit(HttpUrl baseUrl, OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @PerDataManager
    GithubService provideGithubService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }
}
