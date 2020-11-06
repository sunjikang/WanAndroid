package com.xing.main.activity.handle;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.xing.commonbase.base.BaseMVPActivity;
import com.xing.main.R;
import com.xing.main.contract.handle.HistoryContract;
import com.xing.main.presenter.handle.HistoryPresenter;

import java.util.List;

@Route(path = "/handle/HistoryActivity")
public class HistoryActivity extends BaseMVPActivity<HistoryPresenter> implements HistoryContract.View, View.OnClickListener {


    @Autowired(name = "procInstId")
    String procInstId;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_history;
    }

    @Override
    protected HistoryPresenter createPresenter() {
        return new HistoryPresenter();
    }

    @Override
    protected void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.handle_history_title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Toast.makeText(this, procInstId, Toast.LENGTH_SHORT).show();
        if(!TextUtils.isEmpty(procInstId)){
            presenter.historicFlow(procInstId);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onHistoricFlow(List<Object> list) {
        Log.e("TAG",list.toString());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
