package ca.co.rufus.androidboilerplate.test.common.injection.component;

import dagger.Component;
import ca.co.rufus.androidboilerplate.injection.component.DataManagerComponent;
import ca.co.rufus.androidboilerplate.injection.scope.PerDataManager;
import ca.co.rufus.androidboilerplate.test.common.injection.module.DataManagerTestModule;

@PerDataManager
@Component(dependencies = TestComponent.class, modules = DataManagerTestModule.class)
public interface DataManagerTestComponent extends DataManagerComponent {
}
