package com.xing.main.presenter.handle;

import com.xing.commonbase.base.BaseObserver;
import com.xing.commonbase.mvp.BasePresenter;
import com.xing.main.apiservice.MainApiService;
import com.xing.main.bean.handle.ActPage;
import com.xing.main.bean.handle.TodoResult;
import com.xing.main.contract.MainContract;
import com.xing.main.contract.handle.TodoContract;

import java.util.List;

public class TodoPresenter extends BasePresenter<TodoContract.View> implements TodoContract.Presenter {
    /**
     * 获取 userInfo 数据
     */
    @Override
    public void getTodoList(int pageNumber, int pageSize) {
        addSubscribe(create(MainApiService.class).getTodoList(pageNumber,pageSize), new BaseObserver<ActPage<TodoResult>>(getView()) {
            @Override
            protected void onSuccess(ActPage<TodoResult> page) {
                if (isViewAttached()) {
                    List<TodoResult> list = page.getContent();
                    getView().onTodoList((List<TodoResult>)page.getContent());
                }
            }
        });
    }
}
