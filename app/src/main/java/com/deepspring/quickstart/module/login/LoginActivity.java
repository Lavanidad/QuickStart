package com.deepspring.quickstart.module.login;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.deepspring.libcommon.base.activity.BaseActivity;
import com.deepspring.libcommon.common.BaseConstants;
import com.deepspring.libcommon.utils.EditTextUtils;
import com.deepspring.libcommon.utils.Md5;
import com.deepspring.libcommon.utils.SPManager;
import com.deepspring.quickstart.R;
import com.deepspring.quickstart.module.main.MainActivity;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView, View.OnClickListener {

    public static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.bt_login)
    Button bt_login;

    private String userName;
    private String password;
    private String equipmentId;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        equipmentId = SPManager.instance().getString(BaseConstants.SP_KEY_EQUIPMENTID);
    }

    @Override
    protected void initListener() {
        super.initListener();
        listenerEditText();
        bt_login.setEnabled(false);
        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                userName = et_name.getText().toString().trim();
                password = Md5.md5(et_pwd.getText().toString().trim());
                mPresenter.getPostLogin(this, userName, password, equipmentId);
                break;
            default:
                break;
        }
    }

    //监听 editText 状态
    private void listenerEditText() {
        EditTextUtils.textChangeListener textChangeListener = new EditTextUtils.textChangeListener(bt_login);
        textChangeListener.addAllEditText(et_name, et_pwd);
    }

    @SuppressLint("CheckResult")
    @Override
    public void showLoginSuccess() {
        //关闭登录页面
        Toasty.success(this, "登陆成功", Toast.LENGTH_SHORT, true);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @SuppressLint("CheckResult")
    @Override
    public void showLoginFail(String errorMsg) {
        Toasty.error(this, errorMsg, Toasty.LENGTH_SHORT, true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
