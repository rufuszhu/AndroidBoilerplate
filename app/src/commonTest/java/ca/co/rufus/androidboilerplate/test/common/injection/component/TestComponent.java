package ca.co.rufus.androidboilerplate.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import ca.co.rufus.androidboilerplate.injection.component.ApplicationComponent;
import ca.co.rufus.androidboilerplate.test.common.injection.module.ApplicationTestModule;
import ca.co.rufus.androidboilerplate.test.common.injection.module.DefaultSchedulersTestModule;

@Singleton
@Component(modules = {ApplicationTestModule.class, DefaultSchedulersTestModule.class})
public interface TestComponent extends ApplicationComponent {

}
