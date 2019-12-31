package com.deepspring.quickstart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;


import com.deepspring.libcommon.common.BaseConstants;
import com.deepspring.libcommon.utils.SPManager;
import com.deepspring.quickstart.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * @author fzy
 * @date 2019/12/17.
 * description：
 */
public class MyApplication extends DaggerApplication {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        //设备号
        SPManager.instance().setString(BaseConstants.SP_KEY_EQUIPMENTID,
                Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.create();
    }


    public static Context getContext() {
        return mContext;
    }
}
