package ca.co.rufus.androidboilerplate.ui.main;

import java.util.List;

import ca.co.rufus.androidboilerplate.data.model.RepoOwnerJoin;
import ca.co.rufus.androidboilerplate.data.model.Repository;
import ca.co.rufus.androidboilerplate.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRepos(List<RepoOwnerJoin> repositories);

    void showReposEmpty();

    void showError(String errorMessage);

}
