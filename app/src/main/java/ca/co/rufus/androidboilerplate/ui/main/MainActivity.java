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
import ca.co.rufus.androidboilerplate.data.model.Repository;
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
        mMainPresenter.loadRibots();
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
    public void showRibots(List<Repository> ribots) {
        mGithubRepoAdapter.setRibots(ribots);
        mGithubRepoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {
        DialogFactory.createGenericErrorDialog(this, errorMessage).show();
    }

    @Override
    public void showRibotsEmpty() {
        mGithubRepoAdapter.setRibots(Collections.<Repository>emptyList());
        mGithubRepoAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_ribots, Toast.LENGTH_LONG).show();
    }

}
