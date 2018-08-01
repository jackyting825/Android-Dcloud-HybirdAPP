package com.bz.nativeapp.plus_helper;

import android.app.Activity;
import android.content.Intent;

import com.blankj.utilcode.util.ActivityUtils;
import com.bz.nativeapp.native_activity.LoginActivity;

/**
 * Android端H5+调用跳转到Android原生的activity
 * IOS参考:
 * http://ask.dcloud.net.cn/question/14751
 * http://ask.dcloud.net.cn/question/28355
 * Created by bz on 18-5-5.
 */
public class PlusActivityHelper {

    /**
     * js调用后进行跳转
     *
     * @param activity 传入当前Activity
     */
    public static void jumpToLoginActivity(Activity activity, String[] params) {
        String val = "";
        for (String param : params) {
            val += param;
        }
        Intent mIntent = new Intent(activity, LoginActivity.class);
        mIntent.putExtra("text", val);
        ActivityUtils.startActivity(mIntent);
    }

}
