package ca.co.rufus.androidboilerplate.ui.main;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import ca.co.rufus.androidboilerplate.data.model.Repository;
import rx.Subscriber;
import rx.Subscription;
import timber.log.Timber;
import ca.co.rufus.androidboilerplate.BoilerplateApplication;
import ca.co.rufus.androidboilerplate.R;
import ca.co.rufus.androidboilerplate.data.DataManager;
import ca.co.rufus.androidboilerplate.data.model.Ribot;
import ca.co.rufus.androidboilerplate.ui.base.BasePresenter;
import ca.co.rufus.androidboilerplate.util.SchedulerAppliers;

public class MainPresenter extends BasePresenter<MainMvpView> {

    @Inject protected DataManager mDataManager;
    private Subscription mSubscription;

    public MainPresenter(Context context) {
        super(context);
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
        BoilerplateApplication.get(getContext()).getComponent().inject(this);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadRibots() {
        checkViewAttached();
        mSubscription = mDataManager.getRepository()
                .compose(SchedulerAppliers.<List<Repository>>defaultSchedulers(getContext()))
                .subscribe(new Subscriber<List<Repository>>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the ribots.");
                        String errorString = getContext().getString(R.string.error_loading_ribots);
                        getMvpView().showError(errorString);
                    }

                    @Override
                    public void onNext(List<Repository> ribots) {
                        if (ribots.isEmpty()) {
                            getMvpView().showRibotsEmpty();
                        } else {
                            getMvpView().showRibots(ribots);
                        }
                    }
                });
    }

}
