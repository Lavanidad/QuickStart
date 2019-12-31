package com.deepspring.libcommon.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author fzy
 * @date 2019/12/24.
 * description： activity基类
 */
public abstract class AbstractSimpleActivity extends AppCompatActivity {

    protected AbstractSimpleActivity mContext;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mUnbinder = null;
    }

    /**
     * view 的创建 留给子类实现
     */
    protected abstract void initView();


    /**
     * 初始化数据留给子类实现
     */
    protected abstract void initData();

    protected abstract void initListener();

    /**
     * 获取布局对象 留给子类实现
     */
    protected abstract int getLayout();

}
