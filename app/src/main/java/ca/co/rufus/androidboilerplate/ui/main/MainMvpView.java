package ca.co.rufus.androidboilerplate.ui.main;

import java.util.List;

import ca.co.rufus.androidboilerplate.data.model.RepoOwnerJoin;
import ca.co.rufus.androidboilerplate.data.model.Repository;
import ca.co.rufus.androidboilerplate.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<RepoOwnerJoin> repositories);

    void showRibotsEmpty();

    void showError(String errorMessage);

}
