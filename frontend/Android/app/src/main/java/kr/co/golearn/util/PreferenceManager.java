package kr.co.golearn.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    public static final String IS_SAVED_ID = "IS_SAVED_ID";
    public static final String IS_AUTO_LOGIN = "IS_AUTO_LOGIN";
    public static final String LOGIN_ID = "LOGIN_ID";
    public static final String LOGIN_PW = "LOGIN_PW";
    public static final String TOKEN_KEY = "TOKEN_KEY";
    public static final String USER_NO = "USER_NO";
    public static final String USER_NICKNAME = "USER_NICKNAME";
    public static final String USER_PROFILE = "USER_PROFILE";

    private static final String PREFERENCES_NAME = "LEARNANDGO_PREFERENCE";
    private static final String DEFAULT_VALUE_STRING = "NO_DATA";
    private static final boolean DEFAULT_VALUE_BOOLEAN = false;
    private static final long DEFAULT_VALUE_LONG = 0L;

    private SharedPreferences getReferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setString(Context context, String key, String value) {
        SharedPreferences prefs = getReferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void setBoolean(Context context, String key, Boolean value) {
        SharedPreferences prefs = getReferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void setLong(Context context, String key, Long value) {
        SharedPreferences prefs = getReferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public String getString(Context context, String key) {
        SharedPreferences prefs = getReferences(context);
        return prefs.getString(key, DEFAULT_VALUE_STRING);
    }

    public Boolean getBoolean(Context context, String key) {
        SharedPreferences prefs = getReferences(context);
        return prefs.getBoolean(key, DEFAULT_VALUE_BOOLEAN);
    }

    public Long getLong(Context context, String key) {
        SharedPreferences prefs = getReferences(context);
        return prefs.getLong(key, DEFAULT_VALUE_LONG);
    }

    public void removeKey(Context context, String key) {
        SharedPreferences prefs = getReferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }

    public void clear(Context context) {
        SharedPreferences prefs = getReferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}
