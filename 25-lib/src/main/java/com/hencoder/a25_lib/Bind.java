package com.hencoder.a25_lib;

import android.app.Activity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Bind {
    public static void bind(Activity activity) {
        try {
            Class bindingClass = Class.forName(activity.getClass().getCanonicalName() + "$Binding");
            Constructor constructor = bindingClass.getDeclaredConstructor(Class.forName(activity.getClass().getCanonicalName()));
            constructor.newInstance(activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
