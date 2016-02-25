package ca.co.rufus.androidboilerplate.injection.component;

import ca.co.rufus.androidboilerplate.injection.module.ApiModule;
import dagger.Component;
import ca.co.rufus.androidboilerplate.data.DataManager;
import ca.co.rufus.androidboilerplate.injection.module.DataManagerModule;
import ca.co.rufus.androidboilerplate.injection.scope.PerDataManager;

@PerDataManager
@Component(dependencies = ApplicationComponent.class, modules = {DataManagerModule.class, ApiModule.class})
public interface DataManagerComponent {

    void inject(DataManager dataManager);
}
