package com.hencoder.a17liba;

import com.hencoder.a17libb.BUtils;

public class AUtils {
    public static String getName() {
        return "a";
    }

    public static String getCombinedName() {
        return getName() + BUtils.getName();
    }
}
