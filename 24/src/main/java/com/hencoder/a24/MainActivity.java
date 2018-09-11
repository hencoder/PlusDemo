package com.hencoder.a24;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.PathClassLoader;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView titleTv;
    Button showTitleBt;
    Button hotfixBt;
    Button removeHotfixBt;
    Button killSelfBt;

    File apk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apk = new File(getCacheDir() + "/hotfix.dex");

        titleTv = findViewById(R.id.titleTv);
        showTitleBt = findViewById(R.id.showTitleBt);
        hotfixBt = findViewById(R.id.hotfixBt);
        removeHotfixBt = findViewById(R.id.removeHotfixBt);
        killSelfBt = findViewById(R.id.killSelfBt);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                switch (v.getId()) {
                    case R.id.showTitleBt:
                        Util util = new Util();
                        titleTv.setText(util.getTitle());
                        break;
                    case R.id.hotfixBt:
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("https://api.hencoder.com/patch/upload/hotfix.dex")
                                .get()
                                .build();
                        client.newCall(request)
                                .enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        v.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(MainActivity.this, "出错啦", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        try {
                                            FileOutputStream fos = new FileOutputStream(apk);
                                            fos.write(response.body().bytes());
                                            fos.close();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        v.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(MainActivity.this, "成功啦", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });
                        break;
                    case R.id.removeHotfixBt:
                        if (apk.exists()) {
                            apk.delete();
                        }
                        break;
                    case R.id.killSelfBt:
                        android.os.Process.killProcess(android.os.Process.myPid());
                        break;
                }
            }
        };

        showTitleBt.setOnClickListener(onClickListener);
        hotfixBt.setOnClickListener(onClickListener);
        removeHotfixBt.setOnClickListener(onClickListener);
        killSelfBt.setOnClickListener(onClickListener);
    }
}
