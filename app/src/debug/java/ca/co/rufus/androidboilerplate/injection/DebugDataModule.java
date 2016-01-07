package ca.co.rufus.androidboilerplate.injection;

import android.content.SharedPreferences;

import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;

import javax.inject.Named;
import javax.inject.Singleton;

import ca.co.rufus.androidboilerplate.util.ApiEndpoints;
import dagger.Module;
import dagger.Provides;

@Module
public final class DebugDataModule {

    private static final int DEFAULT_ANIMATION_SPEED = 1; // 1x (normal) speed.
    private static final boolean DEFAULT_SCALPEL_ENABLED = false; // No crazy 3D view tree.
    private static final boolean DEFAULT_SCALPEL_WIREFRAME_ENABLED = false; // Draw views by default.
    private static final boolean DEFAULT_SEEN_DEBUG_DRAWER = false; // Show debug drawer first time.

    @Provides
    @Singleton
    RxSharedPreferences provideRxSharedPreferences(SharedPreferences prefs) {
        return RxSharedPreferences.create(prefs);
    }

    @Provides
    @Singleton
    @Named("ApiEndpoint")
    Preference<String> provideEndpointPreference(RxSharedPreferences preferences) {
        return preferences.getString("debug_endpoint", ApiEndpoints.MOCK_MODE.url);
    }

    @Provides
    @Singleton
    @Named("IsMockMode")
    boolean provideIsMockMode(@Named("ApiEndpoint") Preference<String> endpoint) {

        return ApiEndpoints.isMockMode(endpoint.get());
    }

    @Provides
    @Singleton
    @Named("NetworkDelay")
    Preference<Long> provideNetworkDelay(RxSharedPreferences preferences) {
        return preferences.getLong("debug_network_delay", 2000l);
    }

    @Provides
    @Singleton
    @Named("NetworkFailurePercent")
    Preference<Integer> provideNetworkFailurePercent(RxSharedPreferences preferences) {
        return preferences.getInteger("debug_network_failure_percent", 3);
    }

    @Provides
    @Singleton
    @Named("NetworkVariancePercent")
    Preference<Integer> provideNetworkVariancePercent(RxSharedPreferences preferences) {
        return preferences.getInteger("debug_network_variance_percent", 40);
    }

//    @Provides
//    @Singleton
//    @Named("IsMockMode")
//    Preference<InetSocketAddress> provideNetworkProxyAddress(RxSharedPreferences preferences) {
//        return preferences.getObject("debug_network_proxy",
//                InetSocketAddressPreferenceAdapter.INSTANCE);
//    }

    @Provides
    @Singleton
    @Named("AnimationSpeed")
    Preference<Integer> provideAnimationSpeed(RxSharedPreferences preferences) {
        return preferences.getInteger("debug_animation_speed", DEFAULT_ANIMATION_SPEED);
    }

    @Provides
    @Singleton
    @Named("SeenDebugDrawer")
    Preference<Boolean> provideSeenDebugDrawer(RxSharedPreferences preferences) {
        return preferences.getBoolean("debug_seen_debug_drawer", DEFAULT_SEEN_DEBUG_DRAWER);
    }

    @Provides
    @Singleton
    @Named("ScalpelEnabled")
    Preference<Boolean> provideScalpelEnabled(RxSharedPreferences preferences) {
        return preferences.getBoolean("debug_scalpel_enabled", DEFAULT_SCALPEL_ENABLED);
    }

    @Provides
    @Singleton
    @Named("ScalpelWireframeEnabled")
    Preference<Boolean> provideScalpelWireframeEnabled(RxSharedPreferences preferences) {
        return preferences.getBoolean("debug_scalpel_wireframe_drawer",
                DEFAULT_SCALPEL_WIREFRAME_ENABLED);
    }
}
