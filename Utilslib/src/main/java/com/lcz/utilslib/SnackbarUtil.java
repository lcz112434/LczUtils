package com.lcz.utilslib;


import android.view.View;

import com.google.android.material.snackbar.Snackbar;

/**
 * Created by codeest on 16/9/3.
 */

public class SnackbarUtil {

    //弹出Snackbar
    public static void show(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    //弹出短时Snackbar
    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}
