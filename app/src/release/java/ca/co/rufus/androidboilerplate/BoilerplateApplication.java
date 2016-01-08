package ca.co.rufus.androidboilerplate;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import ca.co.rufus.androidboilerplate.injection.component.ApplicationComponent;
import ca.co.rufus.androidboilerplate.injection.component.DaggerApplicationComponent;
import ca.co.rufus.androidboilerplate.injection.module.ApplicationModule;
import io.fabric.sdk.android.Fabric;

public class BoilerplateApplication extends Application {

    ApplicationComponent mApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());


        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static BoilerplateApplication get(Context context) {
        return (BoilerplateApplication) context.getApplicationContext();
    }


    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}

