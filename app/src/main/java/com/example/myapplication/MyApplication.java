package com.example.myapplication;

import android.app.Application;

/**
 * Created by 유환주 on 2016-11-20.
 */
public class MyApplication extends Application
{
    private String mGlobalString;

    public String getGlobalString()
    {
        return mGlobalString;
    }

    public void setGlobalString(String globalString)
    {
        this.mGlobalString = globalString;
    }
}