package com.xing.main.presenter;

import com.xing.commonbase.base.BaseObserver;
import com.xing.commonbase.mvp.BasePresenter;
import com.xing.main.apiservice.MainApiService;
import com.xing.main.bean.BannerResult;
import com.xing.main.bean.HomeArticleResult;
import com.xing.main.bean.UserResult;
import com.xing.main.bean.WeChatAuthorResult;
import com.xing.main.contract.HomeContract;
import com.xing.main.contract.MineContract;

import java.util.List;

public class MinePresenter extends BasePresenter<MineContract.View> implements MineContract.Presenter {


    /**
     * 获取 userInfo 数据
     */
    @Override
    public void getUserInfo() {
        addSubscribe(create(MainApiService.class).getUserInfo(), new BaseObserver<UserResult>(getView()) {
            @Override
            protected void onSuccess(UserResult userResult) {
                if (isViewAttached()) {
                    getView().onUserInfo(userResult);
                }
            }
        });
    }
}
