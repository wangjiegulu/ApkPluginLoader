package com.wangjie.apkpluginloader.example;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.wangjie.apkpluginloader.PluginProxyActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main_open_plugin_a_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_open_plugin_a_btn:
                Intent intent = new Intent(this, PluginProxyActivity.class);
//                Intent intent = PluginProxyActivity.getPluginProxyIntent(this);
                intent.putExtra(PluginProxyActivity.REQUEST_PLUGIN_ACTIVITY, "com.wangjie.apkpluginsample.PluginMainActivity");
                intent.putExtra(PluginProxyActivity.REQUEST_PLUGIN_DEX_PATH, Environment.getExternalStorageDirectory().getAbsolutePath() + "/wangjie/ApkPluginSample-debug.apk");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
