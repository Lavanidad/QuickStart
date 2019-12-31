package com.deepspring.libcommon.rx;

import android.Manifest;
import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

import com.deepspring.libcommon.utils.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;


/**
 * @author fzy
 * @date 2019/10/18.
 * description： 需要手动添加危险权限
 */
public class RxPermission {

    public static final String TAG = RxPermission.class.getSimpleName();

    @SuppressLint("CheckResult")
    public static void checkPermissionRequestEach(AppCompatActivity activity) {
        RxPermissions permissions = new RxPermissions(activity);
        permissions.setLogging(true);
        //多个
        permissions.requestEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)
                .subscribe(permission -> {
                    LogUtils.loge(TAG, "checkPermissionRequestEach--:" + "-permission-:" + permission.name + "--------");
                    if (permission.name.equalsIgnoreCase(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        if (permission.granted) {
                            //同意后调用
                            LogUtils.loge(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-:" + true);
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            //禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
                            LogUtils.loge(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-shouldShowRequestPermissionRationale:" + false);
                        } else {
                            //禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
                            LogUtils.loge(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-:" + false);
                        }
                    }
                    if (permission.name.equalsIgnoreCase(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        if (permission.granted) {
                            LogUtils.loge(TAG, "checkPermissionRequestEach--:" + "-WRITE_EXTERNAL_STORAGE-:" + true);
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            LogUtils.loge(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-shouldShowRequestPermissionRationale:" + false);
                        } else {
                            LogUtils.loge(TAG, "checkPermissionRequestEach--:" + "-WRITE_EXTERNAL_STORAGE-:" + false);
                        }
                    }
                    //单个
                    if (permission.name.equalsIgnoreCase(Manifest.permission.CAMERA)) {
                        if (permission.granted) {
                            LogUtils.loge(TAG, "checkPermissionRequestEach--:" + "-READ_CALENDAR-:" + true);
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            LogUtils.loge(TAG, "checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-shouldShowRequestPermissionRationale:" + false);
                        } else {
                            LogUtils.loge(TAG, "checkPermissionRequestEach--:" + "-READ_CALENDAR-:" + false);
                        }
                    }
                });
    }

}
