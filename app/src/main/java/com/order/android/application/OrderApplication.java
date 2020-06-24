package com.order.android.application;

import android.app.Application;
import android.util.Log;

import com.tencent.mmkv.MMKV;

public class OrderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MMKV.initialize(this);
        Log.e("width",""+getResources().getDisplayMetrics().widthPixels);
        Log.e("height",""+getResources().getDisplayMetrics().heightPixels);
    }
}
