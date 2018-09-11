package com.hencoder.a23;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Utils utils = new Utils();
//        utils.shout();

        try {
            Class utilsClass = Class.forName("com.hencoder.a23.hidden.Utils");
            Constructor utilsConstructor = utilsClass.getDeclaredConstructors()[0];
            utilsConstructor.setAccessible(true);
            Object utils = utilsConstructor.newInstance();
            Method shoutMethod = utilsClass.getDeclaredMethod("shout");
            shoutMethod.setAccessible(true);
            shoutMethod.invoke(utils);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        File apk = new File(getCacheDir() + "/24plugin-debug1.apk");
        if (!apk.exists()) {
            try {
                InputStream is = getAssets().open("24plugin-debug.apk");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();


                FileOutputStream fos = new FileOutputStream(apk);
                fos.write(buffer);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        DexClassLoader classLoader = new DexClassLoader(apk.getPath(), getCacheDir().getPath(), null, null);
        try {
            Class pluginUtilsClass = classLoader.loadClass("com.hencoder.a23plugin.Utils");
            Object pluginUtils = pluginUtilsClass.newInstance();
            Method shoutMethod = pluginUtilsClass.getDeclaredMethod("shout");
            shoutMethod.setAccessible(true);
            shoutMethod.invoke(pluginUtils);
            Intent intent = new Intent();
            intent.setClassName(this, "com.hencoder.a24plugin.PluginActivity");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
