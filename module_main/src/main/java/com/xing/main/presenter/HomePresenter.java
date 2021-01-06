package com.xing.main.presenter;

import android.util.Log;

import com.xing.commonbase.base.BaseObserver;
import com.xing.commonbase.mvp.BasePresenter;
import com.xing.main.apiservice.MainApiService;
import com.xing.main.bean.CheckVersion;
import com.xing.main.bean.Model;
import com.xing.main.contract.HomeContract;

import java.util.List;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter{

    /**
     *  获得models
     */
    @Override
    public void getModels() {
        addSubscribe(create(MainApiService.class).getModels(), new BaseObserver<List<Model>>(getView()) {

            @Override
            protected void onSuccess(List<Model> data) {
                if (isViewAttached()) {
                    getView().getModels(data);
                }

            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().getModelsFail(e.toString());
             }
        });
    }
}
