package ca.co.rufus.androidboilerplate.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.PowerManager;
import android.support.v4.view.GravityCompat;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.co.rufus.androidboilerplate.R;
import ca.co.rufus.androidboilerplate.ui.base.AppContainer;
import rx.subscriptions.CompositeSubscription;

import static android.content.Context.POWER_SERVICE;
import static android.os.PowerManager.ACQUIRE_CAUSES_WAKEUP;
import static android.os.PowerManager.FULL_WAKE_LOCK;
import static android.os.PowerManager.ON_AFTER_RELEASE;
import static android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;

/**
 * An {@link AppContainer} for debug builds which wrap the content view with a sliding drawer on
 * the right that holds all of the debug information and settings.
 */
@Singleton
public final class DebugAppContainer implements AppContainer {


  static class ViewHolder {
    @Bind(R.id.debug_drawer_layout) DebugDrawerLayout drawerLayout;
    @Bind(R.id.debug_drawer) ViewGroup debugDrawer;
    @Bind(R.id.content) ViewGroup content;

  }

  @Inject public DebugAppContainer() {
  }

  @Override public ViewGroup bind(final Activity activity) {
    activity.setContentView(R.layout.debug_activity_frame);

    final ViewHolder viewHolder = new ViewHolder();
    ButterKnife.bind(viewHolder, activity);

    final Context drawerContext = new ContextThemeWrapper(activity, R.style.Theme_U2020_Debug);
    final DebugView debugView = new DebugView(drawerContext);
    viewHolder.debugDrawer.addView(debugView);

    viewHolder.drawerLayout.setDrawerShadow(R.drawable.debug_drawer_shadow, GravityCompat.END);
    viewHolder.drawerLayout.setDrawerListener(new DebugDrawerLayout.SimpleDrawerListener() {
      @Override public void onDrawerOpened(View drawerView) {
//        debugView.onDrawerOpened();
      }
    });


    // If you have not seen the debug drawer before, show it with a message
//    if (!seenDebugDrawer.get()) {
//      viewHolder.drawerLayout.postDelayed(() -> {
//        viewHolder.drawerLayout.openDrawer(GravityCompat.END);
//        Toast.makeText(drawerContext, R.string.debug_drawer_welcome, Toast.LENGTH_LONG).show();
//      }, 1000);
//      seenDebugDrawer.set(true);
//    }

//    final CompositeSubscription subscriptions = new CompositeSubscription();
//    setupMadge(viewHolder, subscriptions);
//    setupScalpel(viewHolder, subscriptions);

//    final Application app = activity.getApplication();
//    app.registerActivityLifecycleCallbacks(new EmptyActivityLifecycleCallbacks() {
//      @Override public void onActivityDestroyed(Activity lifecycleActivity) {
//        if (lifecycleActivity == activity) {
//          subscriptions.unsubscribe();
//          app.unregisterActivityLifecycleCallbacks(this);
//        }
//      }
//    });

    riseAndShine(activity);
    return viewHolder.content;
  }


  /**
   * Show the activity over the lock-screen and wake up the device. If you launched the app manually
   * both of these conditions are already true. If you deployed from the IDE, however, this will
   * save you from hundreds of power button presses and pattern swiping per day!
   */
  public static void riseAndShine(Activity activity) {
    activity.getWindow().addFlags(FLAG_SHOW_WHEN_LOCKED);

    PowerManager power = (PowerManager) activity.getSystemService(POWER_SERVICE);
    PowerManager.WakeLock lock =
        power.newWakeLock(FULL_WAKE_LOCK | ACQUIRE_CAUSES_WAKEUP | ON_AFTER_RELEASE, "wakeup!");
    lock.acquire();
    lock.release();
  }
}
