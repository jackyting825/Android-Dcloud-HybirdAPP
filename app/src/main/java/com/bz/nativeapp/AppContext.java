package com.bz.nativeapp;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by bz on 18-8-1.
 */

public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化工具类
        Utils.init(this);
    }
}
