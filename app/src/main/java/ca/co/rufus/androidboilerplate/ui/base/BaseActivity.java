package ca.co.rufus.androidboilerplate.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ca.co.rufus.androidboilerplate.BoilerplateApplication;
import ca.co.rufus.androidboilerplate.injection.component.ActivityComponent;
import ca.co.rufus.androidboilerplate.injection.component.DaggerActivityComponent;
import ca.co.rufus.androidboilerplate.injection.module.PresentersModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(BoilerplateApplication.get(this).getComponent())
                    .presentersModule(new PresentersModule(this))
                    .build();
        }
        return mActivityComponent;
    }

}
