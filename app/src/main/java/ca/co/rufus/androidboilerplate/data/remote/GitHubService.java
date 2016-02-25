package ca.co.rufus.androidboilerplate.data.remote;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ca.co.rufus.androidboilerplate.data.model.RepositoriesResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Result;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import static java.util.concurrent.TimeUnit.SECONDS;

public interface GithubService {

    String ENDPOINT = "https://api.github.com/";

    @GET("search/repositories")
    Observable<Result<RepositoriesResponse>> repositories(
            @Query("q") SearchQuery query,
            @Query("sort") Sort sort,
            @Query("order") Order order);

//    /********
//     * Helper class that sets up a new services
//     *******/
//    class Creator {
//
//        public static GithubService newGithubService() {
//            Gson gson = new GsonBuilder()
//                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//                    .create();
//
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .connectTimeout(10, SECONDS)
//                    .readTimeout(10, SECONDS)
//                    .writeTimeout(10, SECONDS).build();
//
//
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            // set your desired log level
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            client.interceptors().add(logging);
//            client.interceptors().add(new StethoInterceptor());
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(GithubService.ENDPOINT)
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .build();
//            return retrofit.create(GithubService.class);
//        }
//    }
}
