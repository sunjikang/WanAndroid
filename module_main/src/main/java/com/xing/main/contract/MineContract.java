package com.xing.main.contract;

import com.xing.commonbase.mvp.IView;
import com.xing.main.bean.xboot.User;

public interface MineContract {

    interface View extends IView {
        void onUserInfo(User user);
    }

    interface Presenter {
        void getUserInfo();
    }
}
