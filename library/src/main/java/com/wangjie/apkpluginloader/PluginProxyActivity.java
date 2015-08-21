package com.wangjie.apkpluginloader;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import dalvik.system.DexClassLoader;

import java.io.File;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/20/15.
 */
public class PluginProxyActivity extends Activity{
    private static final String TAG = PluginProxyActivity.class.getSimpleName();
    public static String REQUEST_PLUGIN_ACTIVITY = "REQUEST_PLUGIN_ACTIVITY";
    public static String REQUEST_PLUGIN_DEX_PATH = "REQUEST_PLUGIN_DEX_PATH";

    private PluginCompatActivity realActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String pluginActivityClazzName = intent.getStringExtra(REQUEST_PLUGIN_ACTIVITY);
        if (null == pluginActivityClazzName) {
            finish();
            return;
        }
        try {
//            Class<?> pluginActivityClazz = this.getClassLoader().loadClass(pluginActivityClazzName);
//            ActivityInfo activityInfo = new ActivityInfo();
//            activityInfo.applicationInfo = getApplicationInfo();
//            realActivity = (PluginCompatActivity) new Instrumentation().newActivity(pluginActivityClazz, this, null, getApplication(), intent, activityInfo, null, null, null, null);

            dexPath = intent.getStringExtra(REQUEST_PLUGIN_DEX_PATH);
            File dexOutputDir = this.getDir("dex", 0);
            final String dexOutputPath = dexOutputDir.getAbsolutePath();
//            ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
//            DexClassLoader dexClassLoader = new DexClassLoader(dexPath, dexOutputPath, null, localClassLoader);
            DexClassLoader dexClassLoader = new DexClassLoader(dexPath, dexOutputPath, null, this.getClassLoader());
            Class<?> pluginActivityClazz = dexClassLoader.loadClass(pluginActivityClazzName);
            ActivityInfo activityInfo = new ActivityInfo();
            activityInfo.applicationInfo = getApplicationInfo();
            realActivity = (PluginCompatActivity) new Instrumentation().newActivity(pluginActivityClazz, this, null, getApplication(), intent, activityInfo, null, null, null, null);

            // 绑定Resource
            realActivity.setAttachedProxyActivity(this);
        } catch (Exception e) {
            Log.e(TAG, "", e);
            finish();
            return;
        }
        realActivity.onPluginCreate(savedInstanceState);
        setContentView(realActivity.getWindow().getDecorView());
    }


    private String dexPath;

    public String getDexPath() {
        return dexPath;
    }

    @Override
    public void onStart() {
        super.onStart();
        realActivity.onPluginStart();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        realActivity.onPluginRestart();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        realActivity.onPluginActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        realActivity.onPluginResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        realActivity.onPluginPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        realActivity.onPluginStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realActivity.onPluginDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        realActivity.onPluginSaveInstanceState(outState);
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        realActivity.onPluginNewIntent(intent);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        realActivity.onPluginRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        return realActivity.onPluginTouchEvent(event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        return realActivity.onPluginKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        return realActivity.onPluginKeyDown(keyCode, event);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        super.onWindowAttributesChanged(params);
        realActivity.onPluginWindowAttributesChanged(params);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        realActivity.onPluginWindowFocusChanged(hasFocus);
    }

    @Override
    public void onBackPressed() {
        realActivity.onPluginBackPressed();
        super.onBackPressed();
    }

    public static Intent getPluginIntent(Context context, Class activityClazz){
        Intent intent = new Intent(context, PluginProxyActivity.class);
        intent.putExtra(PluginProxyActivity.REQUEST_PLUGIN_ACTIVITY, activityClazz.getName());
        return intent;
    }
}
