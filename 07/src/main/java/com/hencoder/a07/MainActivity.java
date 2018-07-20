package com.hencoder.a07;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hencoder.a07.view.FancyFlipView;

public class MainActivity extends AppCompatActivity {
    FancyFlipView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.view);

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "rightFlip", 1);
        animator1.setDuration(500);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "flipRotation", 270);
        animator2.setDuration(1000);
        animator2.setStartDelay(300);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(view, "leftFlip", 1);
        animator3.setDuration(500);
        animator3.setStartDelay(300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator1, animator2, animator3);
        animatorSet.setStartDelay(600);
        animatorSet.start();
    }
}
