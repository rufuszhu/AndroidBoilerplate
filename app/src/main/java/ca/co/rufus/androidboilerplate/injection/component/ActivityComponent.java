package ca.co.rufus.androidboilerplate.injection.component;

import dagger.Component;
import ca.co.rufus.androidboilerplate.injection.module.PresentersModule;
import ca.co.rufus.androidboilerplate.injection.scope.PerActivity;
import ca.co.rufus.androidboilerplate.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {PresentersModule.class})
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
