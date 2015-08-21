package com.wangjie.apkpluginloader;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/20/15.
 */
public interface PluginCompat {

    void onPluginCreate(Bundle savedInstanceState);

    void onPluginStart();

    void onPluginRestart();

    void onPluginActivityResult(int requestCode, int resultCode, Intent data);

    void onPluginResume();

    void onPluginPause();

    void onPluginStop();

    void onPluginDestroy();

    void onPluginSaveInstanceState(Bundle outState);

    void onPluginNewIntent(Intent intent);

    void onPluginRestoreInstanceState(Bundle savedInstanceState);

    boolean onPluginTouchEvent(MotionEvent event);

    boolean onPluginKeyUp(int keyCode, KeyEvent event);

    boolean onPluginKeyDown(int keyCode, KeyEvent event);

    void onPluginWindowAttributesChanged(WindowManager.LayoutParams params);

    void onPluginWindowFocusChanged(boolean hasFocus);

    void onPluginBackPressed();
}
