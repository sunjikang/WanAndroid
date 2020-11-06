package com.xing.main.presenter.handle;

import com.xing.commonbase.base.BaseObserver;
import com.xing.commonbase.mvp.BasePresenter;
import com.xing.main.apiservice.MainApiService;
import com.xing.main.bean.xboot.ActPage;
import com.xing.main.bean.xboot.ProcessNodeVo;
import com.xing.main.bean.xboot.TodoResult;
import com.xing.main.contract.handle.TodoContract;

import java.util.List;

public class TodoPresenter extends BasePresenter<TodoContract.View> implements TodoContract.Presenter {
    /**
     * 获取 userInfo 数据
     */
    @Override
    public void getTodoList(int pageNumber, int pageSize) {
        addSubscribe(create(MainApiService.class).getTodoList(pageNumber, pageSize), new BaseObserver<ActPage<TodoResult>>(getView()) {
            @Override
            protected void onSuccess(ActPage<TodoResult> page) {
                if (isViewAttached()) {
                    getView().onTodoList(page.getContent());
                }
            }
        });
    }

    @Override
    public void pass(String id, String procInstId, String[] assignees, Integer priority, String comment, Boolean sendMessage, Boolean sendSms, Boolean sendEmail) {
        addSubscribe(create(MainApiService.class).pass(id, procInstId,assignees,priority,comment,sendMessage,sendSms,sendEmail), new BaseObserver<Object>(getView()) {
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
        addSubscribe(create(MainApiService.class).back(id, procInstId,comment,sendMessage,sendSms,sendEmail), new BaseObserver<Object>(getView()) {
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
        addSubscribe(create(MainApiService.class).delegate(id,userId, procInstId,comment,sendMessage,sendSms,sendEmail), new BaseObserver<Object>(getView()) {
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
        addSubscribe(create(MainApiService.class).getNextNode(procDefId, currActId), new BaseObserver<ProcessNodeVo>(getView()) {
            @Override
            protected void onSuccess(ProcessNodeVo data) {
                if (isViewAttached()) {
                    getView().onNextNode(data);
                }
            }
        });
    }
}
