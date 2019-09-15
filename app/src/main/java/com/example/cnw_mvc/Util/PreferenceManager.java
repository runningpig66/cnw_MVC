package com.example.cnw_mvc.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("cnw_MVC", Context.MODE_PRIVATE);
    }

    public static boolean isFirstTime(Context context, String key) {
        if (getBoolean(context, key, false)) {
            return false;
        } else {
            putBoolean(context, key, true);
            return true;
        }
    }

    public static boolean contains(Context context, String key) {
        return PreferenceManager.getSharedPreferences(context).contains(key);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return PreferenceManager.getSharedPreferences(context).getInt(key, defaultValue);
    }

    public static Integer getInt(Context context, String key, Integer defaultValue) {
        if (PreferenceManager.getSharedPreferences(context).contains(key)) {
            return PreferenceManager.getSharedPreferences(context).getInt(key, defaultValue);
        } else {
            return null;
        }
    }

    public static boolean putInt(Context context, String key, int pValue) {
        SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(context).edit();
        editor.putInt(key, pValue);
        return editor.commit();
    }

    public static long getLong(Context context, String key, long defaultValue) {
        return PreferenceManager.getSharedPreferences(context).getLong(key, defaultValue);
    }

    public static boolean putInt(Context context, String key, long pValue) {
        SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(context).edit();
        editor.putLong(key, pValue);
        return editor.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return PreferenceManager.getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static boolean putBoolean(Context context, String key, boolean pValue) {
        SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(context).edit();
        editor.putBoolean(key, pValue);
        return editor.commit();
    }

    public static String getString(Context context, String key, String defaultValue) {
        return PreferenceManager.getSharedPreferences(context).getString(key, defaultValue);
    }

    public static boolean putString(Context context, String key, String pValue) {
        SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(context).edit();
        editor.putString(key, pValue);
        return editor.commit();
    }

    public static boolean remove(Context context, String key) {
        SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(context).edit();
        editor.remove(key);
        return editor.commit();
    }
}
