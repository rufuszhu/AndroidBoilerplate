package ca.co.rufus.androidboilerplate.injection.component;

import android.app.Application;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import ca.co.rufus.androidboilerplate.injection.AppContainerModule;
import ca.co.rufus.androidboilerplate.ui.base.AppContainer;
import dagger.Component;
import ca.co.rufus.androidboilerplate.data.DataManager;
import ca.co.rufus.androidboilerplate.data.SyncService;
import ca.co.rufus.androidboilerplate.injection.module.ApplicationModule;
import ca.co.rufus.androidboilerplate.injection.module.DefaultSchedulersModule;
import ca.co.rufus.androidboilerplate.ui.main.MainPresenter;
import ca.co.rufus.androidboilerplate.util.SchedulerApplier;

@Singleton
@Component(modules = {ApplicationModule.class, DefaultSchedulersModule.class, AppContainerModule.class})
public interface ApplicationComponent {

    void inject(SyncService syncService);
    void inject(SchedulerApplier.DefaultSchedulers defaultSchedulers);
    void inject(MainPresenter mainPresenter);

    Application application();
    DataManager dataManager();
    Bus eventBus();
    AppContainer appcontainer();
}
