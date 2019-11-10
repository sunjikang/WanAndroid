package com.xing.module.quality.contract;

import com.xing.commonbase.mvp.IView;
import com.xing.module.quality.bean.QCRecord;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

public interface QualityContract {
    interface View extends IView {
        void onSubmitSuccess(List<QCRecord> list);
    }

    interface Presenter {
        void doSubmit(List<QCRecord> list);
    }
}
