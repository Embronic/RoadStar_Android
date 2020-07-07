package com.road.star.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static SharedPreferences preferences;

    private static SharedPreferences.Editor editor;

    private PreferenceManager() {

    }

    public static void init(Context context) {
        if (preferences == null)
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static boolean keyExist(String key) {
        return preferences.contains(key);
    }

    public static void removeKey(String key) {
        editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void removeAllKey() {
        editor = preferences.edit();
        for (String key : preferences.getAll().keySet()) {
            editor.remove(key);
            editor.apply();
        }
    }

    public static String getStringValue(String key) {
        return preferences.getString(key, "");
    }

    public static void setStringValue(String key, String value) {
        editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

   /* public static void setLeadData(String key, LeadIDResponse data) {
        editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString(key, json);
        editor.apply();
    }

    public static LeadIDResponse getLeadData(String key) {
        Gson gson = new Gson();
        String json = preferences.getString(key, "");
        return gson.fromJson(json, LeadIDResponse.class);
    }*/


    public static boolean getBoolValue(String key) {
        return preferences.getBoolean(key, false);
    }

    public static void setBoolValue(String key, boolean value) {
        editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static Integer getIntValue(String key) {
        return preferences.getInt(key, 0);
    }

    public static void setIntValue(String key, Integer value) {
        editor = preferences.edit();
        editor.putInt(key, value).apply();
    }

}
