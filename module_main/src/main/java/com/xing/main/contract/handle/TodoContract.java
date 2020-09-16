package com.xing.main.contract.handle;

import com.xing.commonbase.mvp.IView;
import com.xing.main.bean.UserResult;
import com.xing.main.bean.handle.TodoResult;

import java.util.List;

public interface TodoContract {

    interface View extends IView {
        void onTodoList(List<TodoResult> list);
    }

    interface Presenter {
        void getTodoList(int pageNumber, int pageSize);
    }
}
