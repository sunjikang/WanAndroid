package com.xing.main.contract.handle;

import com.xing.commonbase.mvp.IView;
import com.xing.main.bean.xboot.ProcessNodeVo;
import com.xing.main.bean.xboot.TodoResult;

import java.util.List;

public interface TodoContract {

    interface View extends IView {
        void onTodoList(List<TodoResult> list);

        void  onNextNode(ProcessNodeVo processNodeVo);
    }

    interface Presenter {
        void getTodoList(int pageNumber, int pageSize);

        void getNextNode(String procDefId,String currActId);
    }
}
