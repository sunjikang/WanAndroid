package com.xing.main.presenter.handle;

import com.xing.commonbase.base.BaseObserver;
import com.xing.commonbase.mvp.BasePresenter;
import com.xing.main.apiservice.MainApiService;
import com.xing.main.bean.xboot.ActPage;
import com.xing.main.bean.xboot.ProcessNodeVo;
import com.xing.main.bean.xboot.TodoResult;
import com.xing.main.contract.handle.HistoryContract;
import com.xing.main.contract.handle.TodoContract;

import java.util.List;

public class HistoryPresenter extends BasePresenter<HistoryContract.View> implements HistoryContract.Presenter {

    @Override
    public void historicFlow(String procInstId) {
        addSubscribe(create(MainApiService.class).historicFlow(procInstId), new BaseObserver<List<Object>>(getView()) {
            @Override
            protected void onSuccess(List<Object> list) {
                if (isViewAttached()) {
                    getView().onHistoricFlow(list);
                }
            }
        });
    }
}
