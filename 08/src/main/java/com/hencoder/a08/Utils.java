package com.hencoder.a08;

import android.content.res.Resources;
import android.util.TypedValue;

public class Utils {
    public static float dp2Px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
