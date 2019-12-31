package com.deepspring.libcommon.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.deepspring.libcommon.common.ContextProvider;


/**
 * @author fzy
 * @date 2019/12/17.
 * description：通用工具
 */
public class Tools {

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextProvider.getInstance().getContext().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * 隐藏键盘
     *
     * @param etInput
     */
    public static void hideSoftInput(EditText etInput) {
        InputMethodManager imm = (InputMethodManager) etInput.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        // 强制隐藏键盘
        imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0);
    }

    /**
     * 隐藏 activity 当前的软键盘
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 底部虚拟按键栏的高度
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static int getSoftButtonsBarHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }


    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }

}
