package com.xing.manage.fragment.manage;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xing.commonbase.base.BaseMVPFragment;
import com.xing.commonbase.util.ToastUtil;
import com.xing.manage.R;
import com.xing.manage.adapter.manage.FinishedAdapter;
import com.xing.manage.bean.manage.FinishedBean;
import com.xing.manage.bean.ProcessNodeVo;
import com.xing.manage.contract.FinishedContract;
import com.xing.manage.presenter.FinishedPresenter;

import java.util.ArrayList;
import java.util.List;

public class FinishedFragment extends BaseMVPFragment<FinishedPresenter> implements FinishedContract.View, View.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<FinishedBean> dataList = new ArrayList<FinishedBean>();
    private FinishedAdapter adapter;
    private RefreshLayout refreshLayout;
    private int pageSize = 10;
    private int pageNumber = 1;

    private Dialog chooseDialog;

    public FinishedFragment() {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_manage_done;
    }

    @Override
    protected void initView(View rootView) {
        refreshLayout = rootView.findViewById(R.id.srl_todo);
        recyclerView = rootView.findViewById(R.id.rv_todo);
    }

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        presenter.getList(pageNumber, pageSize);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getList(pageNumber, pageSize);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNumber = 1;
                presenter.getList(pageNumber, pageSize);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected FinishedPresenter createPresenter() {
        return new FinishedPresenter();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        refreshLayout.finishLoadMore();
    }





    @Override
    public void onList(final List<FinishedBean> doneBeanList) {
        {
            refreshLayout.finishRefresh();
            if (pageNumber == 1) {
                dataList.clear();
            }
            pageNumber++;
            dataList.addAll(doneBeanList);
            if (adapter == null) {
                adapter = new FinishedAdapter(R.layout.item_manage_finished, dataList);
                recyclerView.setAdapter(adapter);
            } else {
                adapter.setNewData(dataList);
            }
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });
            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    FinishedBean result = dataList.get(position);
                   if (view.getId() == R.id.btn_progress_distribution) {
                        //审核详情
                        ToastUtil.show(getContext(),"审核详情");
                        showDialog(result, 1);
                    } else if (view.getId() == R.id.btn_upload) {
                        ToastUtil.show(getContext(),"表单数据");

                        //表单详情
                       ARouter.getInstance()
                               .build("/manage/HistoryActivity")
                               .withString("procInstId", result.getDeployId())
                               .navigation();
                    } else if (view.getId() == R.id.btn_start_check) {

                       new MaterialDialog.Builder(getContext()).title("确认删除吗？")
                               .positiveText("确定") //肯定按键
                               .negativeText("取消") //否定按键
                               .onPositive(new MaterialDialog.SingleButtonCallback() {
                                   @Override
                                   public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                       ToastUtil.show(getContext(),"确定");
                                       //todo
                                   }
                               })

                               .positiveColor(Color.RED)
                               .negativeColor(Color.BLUE).show();
                   }
                }

                private void showDialog(FinishedBean bean,int type) {
                    MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                            .customView(R.layout.dialog_todo_pass, false).show();
                    View view = dialog.getCustomView();
                    view.findViewById(R.id.todo_submit).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtil.show(getContext(),"提交");
                        }
                    });

                }
            });
        }
    }



    @Override
    public void onPass() {
        chooseDialog = null;
        pageNumber = 1;
        presenter.getList(pageNumber, pageSize);
    }

    @Override
    public void onBack() {
        chooseDialog = null;
        pageNumber = 1;
        presenter.getList(pageNumber, pageSize);
    }

    @Override
    public void onDelegate() {

    }

    @Override
    public void onNextNode(ProcessNodeVo processNodeVo) {

    }



}
