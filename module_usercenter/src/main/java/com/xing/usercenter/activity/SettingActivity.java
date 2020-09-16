package com.xing.usercenter.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xing.commonbase.base.BaseActivity;
import com.xing.commonbase.base.BaseMVPActivity;
import com.xing.commonbase.constants.Constants;
import com.xing.commonbase.util.SharedPreferenceUtil;
import com.xing.commonbase.util.ToastUtil;
import com.xing.usercenter.R;
import com.xing.usercenter.bean.RegisterResult;
import com.xing.usercenter.contract.RegisterContract;
import com.xing.usercenter.presenter.RegisterPresenter;

@Route(path = "/user/SettingActivity")
public class SettingActivity extends BaseActivity{
    EditText hostEditText;
    EditText onlyEditText;
    Button saveBtn;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.setting);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        hostEditText = findViewById(R.id.et_setting_host);
        onlyEditText = findViewById(R.id.et_setting_only);
        saveBtn = findViewById(R.id.btn_save);

        String host = SharedPreferenceUtil.read(Constants.HOST, Constants.HOST, "");
        hostEditText.setText(host);
    }


    @Override
    protected void initData() {
        super.initData();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String host = hostEditText.getText().toString().trim();
                if (TextUtils.isEmpty(host)) {
                    ToastUtil.show(mContext, "请输入访问地址");
                    return;
                }
                SharedPreferenceUtil.write(Constants.HOST, Constants.HOST, host);
                finish();
            }
        });
    }
}
