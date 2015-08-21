package com.wangjie.apkpluginsample;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.wangjie.apkpluginloader.PluginCompatActivity;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 8/20/15.
 */
public class PluginAActivity extends PluginCompatActivity implements View.OnClickListener {
    private static final String TAG = PluginAActivity.class.getSimpleName();
    private TextView contentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin__activity_plugin_a);
        contentTv = (TextView) findViewById(R.id.plugin__activity_plugin_a_content_tv);
        findViewById(R.id.plugin__activity_plugin_a_btn).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plugin__activity_plugin_a_btn:
                Toast.makeText(this, "hello plugin a", Toast.LENGTH_SHORT).show();
                contentTv.setText("now: " + System.currentTimeMillis());
                break;
            default:
                break;
        }
    }


}
