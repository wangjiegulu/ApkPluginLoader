package com.wangjie.apkpluginloader;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/20/15.
 */
public class PluginCompatActivity extends Activity implements PluginCompat {

    private PluginProxyActivity attachedProxyActivity;

    public void setAttachedProxyActivity(PluginProxyActivity attachedProxyActivity) {
        this.attachedProxyActivity = attachedProxyActivity;
        loadResources();
    }

    private AssetManager pluginAssetManager;
    private Resources pluginResources;

    protected void loadResources() {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, attachedProxyActivity.getDexPath());
            this.pluginAssetManager = assetManager;
            Resources superRes = super.getResources();
            pluginResources = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
            Resources.Theme mTheme = pluginResources.newTheme();
            mTheme.setTo(super.getTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void startActivity(Intent intent) {
        attachedProxyActivity.startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void startActivity(Intent intent, Bundle options) {
        attachedProxyActivity.startActivity(intent, options);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        attachedProxyActivity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
    }

    @Override
    public void onPluginCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_AppCompat_Light);
        onCreate(savedInstanceState);
    }

    @Override
    public AssetManager getAssets() {
        return pluginAssetManager == null ? super.getAssets() : pluginAssetManager;
    }

    @Override
    public Resources getResources() {
        return pluginResources == null ? super.getResources() : pluginResources;
    }


    @Override
    public void onPluginStart() {
        onStart();
    }

    @Override
    public void onPluginRestart() {
        onRestart();
    }

    @Override
    public void onPluginActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPluginResume() {
        onResume();
    }

    @Override
    public void onPluginPause() {
        onPause();
    }

    @Override
    public void onPluginStop() {
        onStop();
    }

    @Override
    public void onPluginDestroy() {
        attachedProxyActivity = null;
        onDestroy();
    }

    @Override
    public void onPluginSaveInstanceState(Bundle outState) {
        onSaveInstanceState(outState);
    }

    @Override
    public void onPluginNewIntent(Intent intent) {
        onNewIntent(intent);
    }

    @Override
    public void onPluginRestoreInstanceState(Bundle savedInstanceState) {
        onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onPluginTouchEvent(MotionEvent event) {
        return onTouchEvent(event);
    }

    @Override
    public boolean onPluginKeyUp(int keyCode, KeyEvent event) {
        return onKeyUp(keyCode, event);
    }

    @Override
    public boolean onPluginKeyDown(int keyCode, KeyEvent event) {
        return onKeyDown(keyCode, event);
    }

    @Override
    public void onPluginWindowAttributesChanged(WindowManager.LayoutParams params) {
        onWindowAttributesChanged(params);
    }

    @Override
    public void onPluginWindowFocusChanged(boolean hasFocus) {
        onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onPluginBackPressed() {
        onBackPressed();
    }
}
