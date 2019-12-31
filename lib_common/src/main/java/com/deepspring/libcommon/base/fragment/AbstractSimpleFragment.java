package com.deepspring.libcommon.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author fzy
 * @date 2019/12/24.
 * description：
 */
public abstract class AbstractSimpleFragment extends Fragment {

    private Unbinder mUnbinder;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        initListener();
        return view;
    }

    /**
     * 初始化 view
     */
    protected abstract void initView();

    //获取LayoutId
    protected abstract int getLayoutId();

    //处理数据
    protected abstract void initData();

    protected abstract void initListener();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


}
