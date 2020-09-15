package com.xing.main.contract;

import com.xing.commonbase.mvp.IView;
import com.xing.main.bean.BannerResult;
import com.xing.main.bean.HomeArticleResult;
import com.xing.main.bean.UserResult;
import com.xing.main.bean.WeChatAuthorResult;

import java.util.List;

public interface MineContract {

    interface View extends IView {
        void onUserInfo(UserResult userResult);
    }

    interface Presenter {
        void getUserInfo();
    }
}
