package ca.co.rufus.androidboilerplate.ui.main;

import java.util.List;

import ca.co.rufus.androidboilerplate.data.model.Ribot;
import ca.co.rufus.androidboilerplate.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError(String errorMessage);

}
