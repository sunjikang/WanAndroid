package com.xing.manage.presenter;

import android.util.Log;

import com.xing.commonbase.base.BaseObserver;
import com.xing.commonbase.mvp.BasePresenter;
import com.xing.manage.apiservice.ManageApiService;
import com.xing.manage.bean.ActPage;
import com.xing.manage.bean.manage.FinishedBean;
import com.xing.manage.bean.ProcessNodeVo;
import com.xing.manage.contract.FinishedContract;


public class FinishedPresenter extends BasePresenter<FinishedContract.View> implements FinishedContract.Presenter {
    /**
     * 获取 已办列表 数据
     */
    @Override
    public void getList(int pageNumber, int pageSize) {
        addSubscribe(create(ManageApiService.class).getFinishedList(pageNumber, pageSize), new BaseObserver<ActPage<FinishedBean>>(getView()) {
            @Override
            protected void onSuccess(ActPage<FinishedBean> page) {
                Log.e("sjk","========="+page.getContent().toString());
                if (isViewAttached()) {
                    getView().onList(page.getContent());
                }
            }
        });
    }

    @Override
    public void pass(String id, String procInstId, String[] assignees, Integer priority, String comment, Boolean sendMessage, Boolean sendSms, Boolean sendEmail) {
        addSubscribe(create(ManageApiService.class).pass(id, procInstId,assignees,priority,comment,sendMessage,sendSms,sendEmail), new BaseObserver<Object>(getView()) {
            @Override
            protected void onSuccess(Object result) {
                if (isViewAttached()) {
                    getView().onPass();
                }
            }
        });
    }

    @Override
    public void back(String id, String procInstId, String comment, Boolean sendMessage, Boolean sendSms, Boolean sendEmail) {
        addSubscribe(create(ManageApiService.class).back(id, procInstId,comment,sendMessage,sendSms,sendEmail), new BaseObserver<Object>(getView()) {
            @Override
            protected void onSuccess(Object result) {
                if (isViewAttached()) {
                    getView().onBack();
                }
            }
        });
    }

    @Override
    public void delegate(String id, String userId, String procInstId, String comment, Boolean sendMessage, Boolean sendSms, Boolean sendEmail) {
        addSubscribe(create(ManageApiService.class).delegate(id,userId, procInstId,comment,sendMessage,sendSms,sendEmail), new BaseObserver<Object>(getView()) {
            @Override
            protected void onSuccess(Object result) {
                if (isViewAttached()) {
                    getView().onDelegate();
                }
            }
        });
    }

    @Override
    public void getNextNode(String procDefId, String currActId) {
        addSubscribe(create(ManageApiService.class).getNextNode(procDefId, currActId), new BaseObserver<ProcessNodeVo>(getView()) {
            @Override
            protected void onSuccess(ProcessNodeVo data) {
                if (isViewAttached()) {
                    getView().onNextNode(data);
                }
            }
        });
    }
}
