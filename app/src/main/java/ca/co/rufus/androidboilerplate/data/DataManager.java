package ca.co.rufus.androidboilerplate.data;

import android.content.Context;

import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import ca.co.rufus.androidboilerplate.data.model.RepositoriesResponse;
import ca.co.rufus.androidboilerplate.data.model.Repository;
import ca.co.rufus.androidboilerplate.data.remote.GithubService;
import ca.co.rufus.androidboilerplate.data.remote.Order;
import ca.co.rufus.androidboilerplate.data.remote.SearchQuery;
import ca.co.rufus.androidboilerplate.data.remote.Sort;
import retrofit.Result;
import rx.Observable;
import rx.functions.Func1;
import ca.co.rufus.androidboilerplate.BoilerplateApplication;
import ca.co.rufus.androidboilerplate.data.local.DatabaseHelper;
import ca.co.rufus.androidboilerplate.injection.component.DaggerDataManagerComponent;
import ca.co.rufus.androidboilerplate.injection.module.DataManagerModule;

public class DataManager {

    @Inject
    protected GithubService mGithubService;
    @Inject
    protected DatabaseHelper mDatabaseHelper;
//    @Inject
//    protected PreferencesHelper mPreferencesHelper;
    @Inject
    protected Bus mBus;

    public DataManager(Context context) {
        injectDependencies(context);
    }

    protected void injectDependencies(Context context) {
        DaggerDataManagerComponent.builder()
                .applicationComponent(BoilerplateApplication.get(context).getComponent())
                .dataManagerModule(new DataManagerModule())
                .build()
                .inject(this);
    }

//    public PreferencesHelper getPreferencesHelper() {
//        return mPreferencesHelper;
//    }

    public Observable<Repository> syncRepos(SearchQuery searchQuery, Sort sort, Order order) {
        return mGithubService.repositories(searchQuery, sort, order)
                .concatMap(new Func1<Result<RepositoriesResponse>, Observable<? extends Repository>>() {
                    @Override
                    public Observable<? extends Repository> call(Result<RepositoriesResponse> result) {
                        return mDatabaseHelper.setReposToDb(result.response().body().items);
                    }
                });

    }

    public Observable<List<Repository>> getRepository() {
        return mDatabaseHelper.getReposFromDb().distinct();
    }

}
