package com.example.web_viewtask2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class MySharedpreference {
    private static MySharedpreference mySharedpreference = new MySharedpreference();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static MySharedpreference getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("FlagQuiz", Context.MODE_PRIVATE);
        }
        return mySharedpreference;
    }

    public void setList(String key,String list) {
        editor = sharedPreferences.edit();
        editor.putString(key, list).commit();
    }

    public String getList(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void clear() {
        editor = sharedPreferences.edit();
        editor.clear();
    }
}
