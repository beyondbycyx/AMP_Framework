package com.hugo.goopleplay.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hq on 2015/10/7.
 */
public final class PreUtils {

    public static final String KEY_STRING_DEF = "pre_string_def";
    public static final Boolean KEY_BOOLEAN_DEF = false;
    private static final String SHARED_PREFERENCE_DEFAULT = "shared_preference_default";
    public static String KEY_FIRST_ENTER = "key_first_enter";

    private   static SharedPreferences sp = null;

    public static boolean getBoolean(Context context, String key ) {

        openPreference(context);
        return sp.getBoolean(key, false);

    }

    public static String getString(Context context, String key) {
        openPreference(context);
        return sp.getString(key, KEY_STRING_DEF);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        openPreference(context);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.apply();


    }

    public static void putString(Context context, String key, String value) {
        openPreference(context);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.apply();
    }


    /*
    * 这里应该交给application类，在启动时就初始化，sp
    * */
    public static void openPreference (Context context) {

        if (sp == null) {
            sp =context.getSharedPreferences(SHARED_PREFERENCE_DEFAULT, Context.MODE_PRIVATE);
        }
    }

    public static int getInt(Context context ,String key) {
        openPreference(context);
        return sp.getInt(key, -1);
    }

    public static void putInt(Context context, String key , int value) {
        openPreference(context);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.apply();

    }
}
