package ca.co.rufus.androidboilerplate.util;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

import rx.functions.Action0;

/**
 * Provide some extra helper methods to post events to an Otto event Bus.
 */
public final class EventBusUtil {

    /// Helper method to post events from doOnCompleted.
    private Action0 postEventAction(final Bus bus, final Object event) {
        return new Action0() {
            @Override
            public void call() {
                postEventSafely(bus, event);
            }
        };
    }

    // Helper method to post an event from a different thread to the main one.
    private void postEventSafely(final Bus bus, final Object event) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                bus.post(event);
            }
        });
    }

}
