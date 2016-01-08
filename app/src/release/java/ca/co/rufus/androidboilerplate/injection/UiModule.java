package ca.co.rufus.androidboilerplate.injection;

import android.app.Application;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import ca.co.rufus.androidboilerplate.data.DataManager;
import ca.co.rufus.androidboilerplate.ui.base.AppContainer;
import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies. Mainly singleton object that can be injected from
 * anywhere in the app.
 */
@Module
public class UiModule {

    @Provides
    @Singleton
    AppContainer provideAppContainer() {
        return AppContainer.DEFAULT;
    }

}
