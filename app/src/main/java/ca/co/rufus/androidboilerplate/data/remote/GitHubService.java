package ca.co.rufus.androidboilerplate.data.remote;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import ca.co.rufus.androidboilerplate.data.model.RepositoriesResponse;
import retrofit.GsonConverterFactory;
import retrofit.Result;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

import static java.util.concurrent.TimeUnit.SECONDS;

public interface GithubService {

    String ENDPOINT = "https://api.ribot.io/";

    @GET("search/repositories")
    Observable<Result<RepositoriesResponse>> repositories(
            @Query("q") SearchQuery query,
            @Query("sort") Sort sort,
            @Query("order") Order order);

    /********
     * Helper class that sets up a new services
     *******/
    class Creator {

        public static GithubService newGithubService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();

            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(10, SECONDS);
            client.setReadTimeout(10, SECONDS);
            client.setWriteTimeout(10, SECONDS);
            client.networkInterceptors().add(new StethoInterceptor());
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.interceptors().add(logging);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GithubService.ENDPOINT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(GithubService.class);
        }
    }
}
