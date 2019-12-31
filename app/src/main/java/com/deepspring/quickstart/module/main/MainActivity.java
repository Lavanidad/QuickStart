package com.deepspring.quickstart.module.main;


import com.deepspring.libcommon.base.activity.BaseActivity;
import com.deepspring.libcommon.rx.RxPermission;
import com.deepspring.quickstart.R;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        super.initView();
        //检查权限
        RxPermission.checkPermissionRequestEach(this);
        mContext = this;
    }


    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

}
