package com.xing.usercenter.presenter;

import android.text.TextUtils;

import com.xing.commonbase.base.BaseObserver;
import com.xing.commonbase.constants.Constants;
import com.xing.commonbase.mvp.BasePresenter;
import com.xing.commonbase.util.SharedPreferenceUtil;
import com.xing.usercenter.apiservice.UserCenterApiService;
import com.xing.usercenter.bean.LoginResult;
import com.xing.usercenter.contract.LoginContract;

public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter {

    @Override
    public void login(String username, String password, String code, String captchaId) {
        addSubscribe(create(UserCenterApiService.class).login(username, password, code, captchaId), new BaseObserver<String>(getView()) {

            @Override
            protected void onSuccess(String data) {
                if (isViewAttached()) {
                    saveToken(data);
                    getView().loginSuccess();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().loginError();
            }
        });
    }

    @Override
    public void init() {
        //初始化时清空token
        SharedPreferenceUtil.write(Constants.File_TOKEN, Constants.ACCESS_TOKEN, "");
        addSubscribe(create(UserCenterApiService.class).init(), new BaseObserver<String>(getView()) {

            @Override
            protected void onSuccess(String data) {
                if (isViewAttached()) {
                    getView().initSuccess(data);
                }
            }
        });
    }

    /**
     * 保存 token 至本地
     *
     * @param result
     */
    private void saveToken(String result) {
        if (!TextUtils.isEmpty(result)) {
            SharedPreferenceUtil.write(Constants.File_TOKEN, Constants.ACCESS_TOKEN, result);
        }
    }

}
