package ca.co.rufus.androidboilerplate;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;

import ca.co.rufus.androidboilerplate.injection.DaggerDebugApplicationComponent;
import ca.co.rufus.androidboilerplate.injection.DebugApplicationComponent;
import ca.co.rufus.androidboilerplate.injection.component.ApplicationComponent;
import ca.co.rufus.androidboilerplate.injection.component.DaggerApplicationComponent;
import ca.co.rufus.androidboilerplate.injection.module.ApplicationModule;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class BoilerplateApplication extends Application  {

    ApplicationComponent mApplicationComponent;
    DebugApplicationComponent mDebugApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

            Fabric.with(this, new Crashlytics());
            //debug only
            Stetho.initializeWithDefaults(this);
            Timber.plant(new Timber.DebugTree() {
                //add line number to the tag
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ':' + element.getLineNumber();
                }
            });


        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mDebugApplicationComponent = DaggerDebugApplicationComponent.builder().build();
    }

    public static BoilerplateApplication get(Context context) {
        return (BoilerplateApplication) context.getApplicationContext();
    }

    public DebugApplicationComponent getDebugComponent() {
        return mDebugApplicationComponent;
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}

