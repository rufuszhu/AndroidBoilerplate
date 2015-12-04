package ca.co.rufus.androidboilerplate.injection.module;

import android.content.Context;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;
import ca.co.rufus.androidboilerplate.injection.scope.PerActivity;
import ca.co.rufus.androidboilerplate.ui.main.MainPresenter;

/**
 * This module provides instances of Presenters.
 */
@Module
public class PresentersModule {

    private WeakReference<Context> mContextWeakRef;

    public PresentersModule(Context context) {
        mContextWeakRef = new WeakReference<>(context);
    }

    @Provides
    @PerActivity
    MainPresenter providesMainPresenter() {
        return new MainPresenter(mContextWeakRef.get());
    }

}
