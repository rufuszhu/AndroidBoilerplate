package ca.co.rufus.androidboilerplate.injection;

import android.app.Application;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import ca.co.rufus.androidboilerplate.data.DataManager;
import ca.co.rufus.androidboilerplate.data.SyncService;
import ca.co.rufus.androidboilerplate.injection.component.ApplicationComponent;
import ca.co.rufus.androidboilerplate.injection.module.ApplicationModule;
import ca.co.rufus.androidboilerplate.injection.module.DefaultSchedulersModule;
import ca.co.rufus.androidboilerplate.ui.DebugAppContainer;
import ca.co.rufus.androidboilerplate.ui.DebugView;
import ca.co.rufus.androidboilerplate.ui.base.AppContainer;
import ca.co.rufus.androidboilerplate.ui.main.MainPresenter;
import ca.co.rufus.androidboilerplate.util.SchedulerApplier;
import dagger.Component;

@Singleton
@Component(modules = {UiModule.class, DebugDataModule.class})
public interface DebugApplicationComponent {

    void inject(DebugAppContainer debugAppContainer);
    void inject(DebugView debugView);


    AppContainer appcontainer();
}
