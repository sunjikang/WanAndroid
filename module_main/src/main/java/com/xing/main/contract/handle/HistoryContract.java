package com.xing.main.contract.handle;

import com.xing.commonbase.mvp.IView;
import com.xing.main.bean.xboot.ProcessNodeVo;
import com.xing.main.bean.xboot.TodoResult;

import java.util.List;

public interface HistoryContract {

    interface View extends IView {

        void onHistoricFlow(List<Object> list);

    }

    interface Presenter {
        void historicFlow(String procInstId);
    }
}
