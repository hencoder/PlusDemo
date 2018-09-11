package com.hencoder.a23plugin;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

public class PluginFakeActivity {
    Activity proxyActivity;

    public void onCreate(Bundle bundle) {
        proxyActivity.setContentView(R.layout.activity_plugin);

        Resources res = proxyActivity.getResources();
    }
}
