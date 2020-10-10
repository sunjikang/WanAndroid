package com.xing.main.presenter;

import com.xing.commonbase.base.BaseObserver;
import com.xing.commonbase.mvp.BasePresenter;
import com.xing.main.apiservice.MainApiService;
import com.xing.main.bean.xboot.User;
import com.xing.main.contract.MineContract;

public class MinePresenter extends BasePresenter<MineContract.View> implements MineContract.Presenter {


    /**
     * 获取 userInfo 数据
     */
    @Override
    public void getUserInfo() {
        addSubscribe(create(MainApiService.class).getUserInfo(), new BaseObserver<User>(getView()) {
            @Override
            protected void onSuccess(User user) {
                if (isViewAttached()) {
                    getView().onUserInfo(user);
                }
            }
        });
    }
}
