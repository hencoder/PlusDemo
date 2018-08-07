package com.hencoder.a15;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    ThreadLocal<Integer> threadNumber;
    AtomicInteger number = new AtomicInteger(1);
    ArrayMap<Thread, Integer> numberMap = new ArrayMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("aaa");
                }
            }
        }.start();

        Handler handler = new Handler();
        Executors.newSingleThreadExecutor();
    }
}
