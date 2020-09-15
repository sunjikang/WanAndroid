package com.xing.usercenter.activity;

import android.graphics.Paint;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.xing.commonbase.base.BaseMVPActivity;
import com.xing.commonbase.constants.Constants;
import com.xing.commonbase.http.RetrofitClient;
import com.xing.commonbase.manager.UserLoginManager;
import com.xing.commonbase.util.SharedPreferenceUtil;
import com.xing.commonbase.util.SoftKeyboardUtil;
import com.xing.commonbase.widget.loading.ProgressDialog;
import com.xing.usercenter.R;
import com.xing.usercenter.bean.LoginResult;
import com.xing.usercenter.contract.LoginContract;
import com.xing.usercenter.presenter.LoginPresenter;

@Route(path = "/user/LoginActivity")
public class LoginActivity extends BaseMVPActivity<LoginPresenter>
        implements LoginContract.View, View.OnClickListener {

    private Button loginBtn;
    private TextView registerTxtView;
    private TextView settingTxtView;
    private CheckBox pwdVisibleCheckBox;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private ImageView captchaImg;
    private EditText codeEditText;

    private String captchaId;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        loginBtn = findViewById(R.id.btn_login);
        registerTxtView = findViewById(R.id.tv_register);
        settingTxtView = findViewById(R.id.tv_setting);
        pwdVisibleCheckBox = findViewById(R.id.cb_login_pwd_visible);
        usernameEditText = findViewById(R.id.et_login_username);
        passwordEditText = findViewById(R.id.et_login_password);
        captchaImg = findViewById(R.id.iv_login_captcha_img);
        codeEditText = findViewById(R.id.et_login_code);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        // 添加下划线
        registerTxtView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //初始化用户名密码
        String cacheUsername = SharedPreferenceUtil.read(Constants.USER_LOGIN, Constants.USERNAME, "");
        String cachePassword = SharedPreferenceUtil.read(Constants.USER_LOGIN, Constants.PASSWORD, "");
        if (!TextUtils.isEmpty(cacheUsername)) {
            usernameEditText.setText(cacheUsername);
            usernameEditText.setSelection(cacheUsername.length());
        }
        if (!TextUtils.isEmpty(cachePassword)) {
            passwordEditText.setText(cachePassword);
            passwordEditText.setSelection(cachePassword.length());
        }
        loginBtn.setOnClickListener(this);//登录按钮
        registerTxtView.setOnClickListener(this);//去注册
        settingTxtView.setOnClickListener(this);//去设置
        captchaImg.setOnClickListener(this);//验证码图片
        pwdVisibleCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordEditText.setSelection(passwordEditText.getText().toString().length());
                } else {
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordEditText.setSelection(passwordEditText.getText().toString().length());
                }
            }
        });
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    SoftKeyboardUtil.hideSoftKeyboard(passwordEditText);
                    login();
                    return true;
                }
                return false;
            }
        });
        //初始化获取验证码
        presenter.init();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_login) {
            login();
        } else if (i == R.id.tv_register) {
            gotoRegisterActivity();
        } else if (i == R.id.tv_setting) {
            gotoSettingActivity();
        } else if (i == R.id.iv_login_captcha_img) {
            presenter.init();
        }
    }

    /**
     * 开始登陆
     * xing2019 / 123456
     */
    private void login() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String code = codeEditText.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(mContext, R.string.please_input_username, Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(mContext, R.string.please_input_password, Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(mContext, R.string.please_input_code, Toast.LENGTH_LONG).show();
            return;
        }
        presenter.login(username, password, code, captchaId);
//        gotoMainActivity();
    }


    @Override
    public void loginSuccess() {
        UserLoginManager.getInstance().setLoggedin(true);
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        SharedPreferenceUtil.write(Constants.USER_LOGIN, Constants.USERNAME, username);
        SharedPreferenceUtil.write(Constants.USER_LOGIN, Constants.PASSWORD, password);
        gotoMainActivity();
    }

    @Override
    public void loginError() {
        this.captchaId = "";
        codeEditText.setText("");
        presenter.init();
    }

    @Override
    public void initSuccess(String captchaId) {
//        presenter.draw(captchaId);
        this.captchaId = captchaId;
        Glide.with(mContext).load(RetrofitClient.API_HOST + "xboot/common/captcha/draw/" + this.captchaId).into(captchaImg);
    }


    /**
     * 跳转注册页面
     */
    private void gotoRegisterActivity() {
        ARouter.getInstance().build("/user/RegisterActivity").navigation();
    }

    /**
     * 跳转主界面
     */
    private void gotoMainActivity() {
        ARouter.getInstance().build("/main/MainActivity").navigation();
        finish();
    }

    /**
     * 跳转质检界面
     */
    private void gotoQualityActivity() {
        ARouter.getInstance().build("/quality/QualityActivity").navigation();
        finish();
    }

    /**
     * 跳转到设置界面
     */
    private void gotoSettingActivity() {
        ARouter.getInstance().build("/user/SettingActivity").navigation();
    }


    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(this);
    }

    @Override
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        passwordEditText = null;
        pwdVisibleCheckBox = null;
    }
}
