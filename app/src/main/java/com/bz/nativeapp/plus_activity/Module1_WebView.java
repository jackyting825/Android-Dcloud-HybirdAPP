package com.bz.nativeapp.plus_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import io.dcloud.EntryProxy;
import io.dcloud.RInformation;
import io.dcloud.common.DHInterface.ICore;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.IWebviewStateListener;
import io.dcloud.feature.internal.sdk.SDK;

/**
 * Created by bz on 18-4-11.
 */

public class Module1_WebView extends Activity {


    boolean doHardAcc = true;
    EntryProxy mEntryProxy = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (mEntryProxy == null) {
            FrameLayout rootView = new FrameLayout(this);
            // 创建5+内核运行事件监听
            WebviewModule1Listener wm = new WebviewModule1Listener(this, rootView);
            // 初始化5+内核
            mEntryProxy = EntryProxy.init(this, wm);
            // 启动5+内核
            mEntryProxy.onCreate(this, savedInstanceState, SDK.IntegratedMode.WEBVIEW, null);
            setContentView(rootView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return mEntryProxy.onActivityExecute(this, ISysEventListener.SysEventType.onCreateOptionMenu, menu);
    }

    @Override
    public void onPause() {
        super.onPause();
        mEntryProxy.onPause(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mEntryProxy.onResume(this);
    }

    @SuppressLint("WrongConstant")
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getFlags() != 0x10600000) {// 非点击icon调用activity时才调用newintent事件
            mEntryProxy.onNewIntent(this, intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEntryProxy.onStop(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean _ret = mEntryProxy.onActivityExecute(this, ISysEventListener.SysEventType.onKeyDown, new Object[]{keyCode, event});
        return _ret ? _ret : super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean _ret = mEntryProxy.onActivityExecute(this, ISysEventListener.SysEventType.onKeyUp, new Object[]{keyCode, event});
        return _ret ? _ret : super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        boolean _ret = mEntryProxy.onActivityExecute(this, ISysEventListener.SysEventType.onKeyLongPress, new Object[]{keyCode, event});
        return _ret ? _ret : super.onKeyLongPress(keyCode, event);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        try {
            int temp = this.getResources().getConfiguration().orientation;
            if (mEntryProxy != null) {
                mEntryProxy.onConfigurationChanged(this, temp);
            }
            super.onConfigurationChanged(newConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 系统字体变大导致的布局异常
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mEntryProxy.onActivityExecute(this, ISysEventListener.SysEventType.onActivityResult, new Object[]{requestCode, resultCode, data});
    }

}

class WebviewModule1Listener implements ICore.ICoreStatusListener {

    LinearLayout btns = null;
    Activity activity = null;
    ViewGroup mRootView = null;
    IWebview webview = null;
    ProgressDialog pd = null;
    View splashView = null;

    public WebviewModule1Listener(Activity activity, ViewGroup rootView) {
        this.activity = activity;
        mRootView = rootView;
        btns = new LinearLayout(activity);
        mRootView.setBackgroundColor(0xffffffff);
        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                webview.onRootViewGlobalLayout(mRootView);
            }
        });
    }

    /**
     * 5+内核初始化完成时触发
     */
    @Override
    public void onCoreInitEnd(ICore coreHandler) {
        // 设置单页面集成的appid
        String appid = "5plus";
        // 单页面集成时要加载页面的路径，可以是本地文件路径也可以是网络路径
        String url = "file:///android_asset/apps/5plus/www/modules/vuex.html";
        webview = SDK.createWebview(activity, url, appid, new IWebviewStateListener() {
            @Override
            public Object onCallBack(int pType, Object pArgs) {
                Log.i("5plus", pArgs.toString());
                switch (pType) {
                    case IWebviewStateListener.ON_WEBVIEW_READY:
                        Log.i("5plus", "ON_WEBVIEW_READY");
                        // 准备完毕之后添加webview到显示父View中，设置排版不显示状态，避免显示webview时，html内容排版错乱问题
                        ((IWebview) pArgs).obtainFrameView().obtainMainView().setVisibility(View.INVISIBLE);
                        SDK.attach(mRootView, ((IWebview) pArgs));
                        // 加载load图
                        splashView = new FrameLayout(activity);
                        splashView.setBackgroundResource(RInformation.DRAWABLE_SPLASH);
                        mRootView.addView(splashView);
                        break;
                    case IWebviewStateListener.ON_PAGE_STARTED:
                        Log.i("5plus", "ON_PAGE_STARTED");
                        // 首页面开始加载事件
                        break;
                    case IWebviewStateListener.ON_PROGRESS_CHANGED:
                        Log.i("5plus", "ON_PROGRESS_CHANGED");
                        Log.i("5plus", pArgs.toString());
                        Log.i("5plus", "----------");
                        // 首页面加载进度变化
                        break;
                    case IWebviewStateListener.ON_RECEIVED_ERROR:
                        Log.i("5plus", "ON_RECEIVED_ERROR");
                        break;
                    case IWebviewStateListener.ON_RECEIVED_TITLE:
                        Log.i("5plus", "ON_RECEIVED_TITLE");
                        break;
                    case IWebviewStateListener.ON_PAGE_FINISHED:
                        Log.i("5plus", "ON_PAGE_FINISHED");
                        // 页面加载完毕，设置显示webview
                        webview.obtainFrameView().obtainMainView().setVisibility(View.VISIBLE);
                        break;
                    case IWebviewStateListener.ON_WEBVIEW_RENDERING:
                        Log.d("5plus", "ON_WEBVIEW_RENDERING");
                        // 关闭load图
                        // mRootView.removeView(splashView);
                        break;
                    case IWebviewStateListener.ON_LOAD_RESOURCE:
                        Log.d("5plus", "ON_LOAD_RESOURCE");
                        // 关闭load图
                        // mRootView.removeView(splashView);
                        break;
                    default:
                        break;
                }
                if ("100".equals(pArgs.toString())) {
                    // 关闭load图
                    mRootView.removeView(splashView);
                }
                return null;
            }
        });
    }

    // 5+SDK 开始初始化时触发
    @Override
    public void onCoreReady(ICore coreHandler) {
        try {
            // 初始化5+ SDK，
            // 5+SDK的其他接口需要在SDK初始化后才能調用
            SDK.initSDK(coreHandler);
            // 当前应用可使用全部5+API
            SDK.requestAllFeature();
            // 注册自定义原生和H5通讯插件
            regNewApi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 通过代码注册扩展插件的示例
    private void regNewApi() {
        // 扩展插件在js层的标识,
        String featureName = "vuexplugin";
        // 扩展插件的原生类名
        String className = "com.bz.nativeapp.plus_plugins.Module1Plugin";
        // / 扩展插件的JS层封装的方法
        // String content = "(function(plus){function test(){return plus.bridge.execSync('T','test',[arguments]);}plus.T = {test:test};})(window.plus);";
        // 向5+SDK注册扩展插件
        SDK.registerJsApi(featureName, className, null);
    }

    @Override
    public boolean onCoreStop() {
        return false;
    }
}
