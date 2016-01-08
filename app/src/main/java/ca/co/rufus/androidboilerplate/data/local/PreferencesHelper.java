package ca.co.rufus.androidboilerplate.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    private SharedPreferences mPref;

    public static final String PREF_FILE_NAME = "android_boilerplate_pref_file";


    public PreferencesHelper(Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharePreference() {
        return mPref;
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

}
