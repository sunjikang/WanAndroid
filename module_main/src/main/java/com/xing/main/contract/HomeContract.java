package com.xing.main.contract;

import com.xing.commonbase.mvp.IView;
import com.xing.main.bean.Model;

import java.util.List;

public interface HomeContract {

    interface View extends IView {
        void getModels(List<Model> list);
        void  getModelsFail(String e);
    }

    interface Presenter {
       void getModels();

    }
}
