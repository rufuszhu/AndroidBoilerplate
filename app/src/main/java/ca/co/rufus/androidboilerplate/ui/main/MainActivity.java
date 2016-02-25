package ca.co.rufus.androidboilerplate.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.co.rufus.androidboilerplate.R;
import ca.co.rufus.androidboilerplate.data.SyncService;
import ca.co.rufus.androidboilerplate.data.model.RepoOwnerJoin;
import ca.co.rufus.androidboilerplate.ui.base.AppContainer;
import ca.co.rufus.androidboilerplate.ui.base.BaseActivity;
import ca.co.rufus.androidboilerplate.util.DialogFactory;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainPresenter mMainPresenter;
    private GithubRepoAdapter mGithubRepoAdapter;
    @Inject
    AppContainer appContainer;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        ViewGroup container = appContainer.bind(this);
        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(R.layout.activity_main, container);
        ButterKnife.bind(this, container);

        startService(SyncService.getStartIntent(this));
        mGithubRepoAdapter = new GithubRepoAdapter(this);
        mRecyclerView.setAdapter(mGithubRepoAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainPresenter.attachView(this);
        mMainPresenter.loadRepos();



//        Gson gson = new GsonBuilder()
//                .registerTypeAdapterFactory(new AutoValueAdapterFactory())
//                .create();
//        Repository inTest = Repository.create(1, 1, "a", "b", 12, 12, 12, "http", "123");
//        Timber.i("IN: " + inTest);
//        String json = gson.toJson(inTest);
//        Timber.i("JSON: " + json);
//        Test outTest = gson.fromJson(json, Repository.class);
//        Timber.i("OUT: " + outTest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMainPresenter.detachView();
    }

    /*****
     * MVP View methods implementation
     *****/

    @Override
    public void showRepos(List<RepoOwnerJoin> repoOwnerJoins) {
        mGithubRepoAdapter.setRepos(repoOwnerJoins);
        mGithubRepoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {
        DialogFactory.createGenericErrorDialog(this, errorMessage).show();
    }

    @Override
    public void showReposEmpty() {
        mGithubRepoAdapter.setRepos(Collections.<RepoOwnerJoin>emptyList());
        mGithubRepoAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_ribots, Toast.LENGTH_LONG).show();
    }

}
