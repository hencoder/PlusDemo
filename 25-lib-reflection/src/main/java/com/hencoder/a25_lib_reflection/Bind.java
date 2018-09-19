package com.hencoder.a25_lib_reflection;

import android.app.Activity;

import java.lang.reflect.Field;

public class Bind {
    public static void bind(Activity activity) {
        Field[] fields = activity.getClass().getDeclaredFields();
        for (Field field : fields) {
            BindView bindView = field.getAnnotation(BindView.class);
            if (bindView != null) {
                field.setAccessible(true);
                try {
                    field.set(activity, activity.findViewById(bindView.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
