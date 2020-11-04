package com.lcz.utilslib.Display;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.view.Display;

import java.lang.reflect.Method;

public class DisplayUtil {
    /**
     * 获取手机出厂时默认的densityDpi
     *
     * @return
     */
    @SuppressLint("PrivateApi")
    public static int getDefaultDisplayDensity() {
        try {
            Class<?> aClass = Class.forName("android.view.WindowManagerGlobal");
            Method method = aClass.getMethod("getWindowManagerService");
            method.setAccessible(true);
            Object iwm = method.invoke(aClass);
            Method getInitialDisplayDensity = iwm.getClass().getMethod("getInitialDisplayDensity", int.class);
            getInitialDisplayDensity.setAccessible(true);
            Object densityDpi = getInitialDisplayDensity.invoke(iwm, Display.DEFAULT_DISPLAY);
            return (int) densityDpi;
        } catch (Exception e) {
            e.printStackTrace();
            return 500;
        }
    }

    /**
     * 获取手机出厂时默认的densityDpi并设置给手机
     * 在需要用到的activity中的onCreate中调用该方法
     *
     * @param context
     */
    public static void setDefaultDisplay(Context context) {
        if (Build.VERSION.SDK_INT > 23) {
            Configuration origConfig = context.getResources().getConfiguration();
            origConfig.densityDpi = getDefaultDisplayDensity();//获取手机出厂时默认的densityDpi
            context.getResources().updateConfiguration(origConfig, context.getResources().getDisplayMetrics());
        }
    }
}
