package com.xing.main.fragment.handle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xing.commonbase.base.BaseMVPFragment;
import com.xing.commonbase.util.ToastUtil;
import com.xing.main.R;
import com.xing.main.adapter.handle.TodoAdapter;
import com.xing.main.bean.handle.TodoResult;
import com.xing.main.contract.handle.TodoContract;
import com.xing.main.presenter.handle.TodoPresenter;

import java.util.ArrayList;
import java.util.List;

public class TodoFragment extends BaseMVPFragment<TodoPresenter> implements TodoContract.View, View.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<TodoResult> dataList = new ArrayList<>();
    private TodoAdapter adapter;
    private RefreshLayout refreshLayout;
    private int pageSize = 10;
    private int pageNumber = 1;

    public TodoFragment() {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragmentl_todo;
    }

    @Override
    protected void initView(View rootView) {
        refreshLayout = rootView.findViewById(R.id.srl_todo);
        recyclerView = rootView.findViewById(R.id.rv_todo);
    }

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        presenter.getTodoList(pageNumber, pageSize);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getTodoList(pageNumber, pageSize);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNumber = 1;
                presenter.getTodoList(pageNumber, pageSize);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected TodoPresenter createPresenter() {
        return new TodoPresenter();
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
    public void onTodoList(List<TodoResult> todoResult) {
        refreshLayout.finishRefresh();
        if (pageNumber == 1) {
            dataList.clear();
        }
        pageNumber++;
        dataList.addAll(todoResult);
        if (adapter == null) {
            adapter = new TodoAdapter(R.layout.item_handle_todo, dataList);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ToastUtil.show(mContext, "onItemClick-----:" + position + "--" + view.getId());
                }
            });
            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    ToastUtil.show(mContext, "onItemChildClick-----:" + position + "--" + view.getId());
                    if (view.getId() == R.id.test_btn1) {
                        ToastUtil.show(mContext, "test_btn1-----:" + position);
                    } else if (view.getId() == R.id.test_btn2) {
                        ToastUtil.show(mContext, "test_btn2-----:" + position);

                    }
                }
            });
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setNewData(dataList);
        }
    }
}
