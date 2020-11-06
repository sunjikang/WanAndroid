package com.xing.main.fragment.handle;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xing.commonbase.base.BaseMVPFragment;
import com.xing.main.R;
import com.xing.main.adapter.handle.TodoAdapter;
import com.xing.main.bean.xboot.ProcessNodeVo;
import com.xing.main.bean.xboot.TodoResult;
import com.xing.main.contract.handle.TodoContract;
import com.xing.main.presenter.handle.TodoPresenter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TodoFragment extends BaseMVPFragment<TodoPresenter> implements TodoContract.View, View.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<TodoResult> dataList = new ArrayList<>();
    private TodoAdapter adapter;
    private RefreshLayout refreshLayout;
    private int pageSize = 10;
    private int pageNumber = 1;

    private Dialog chooseDialog;

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
    public void onTodoList(final List<TodoResult> todoResult) {
        refreshLayout.finishRefresh();
        if (pageNumber == 1) {
            dataList.clear();
        }
        pageNumber++;
        dataList.addAll(todoResult);
        if (adapter == null) {
            adapter = new TodoAdapter(R.layout.item_handle_todo, dataList);
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
                TodoResult result = todoResult.get(position);
                if (view.getId() == R.id.btn_detail) {

                } else if (view.getId() == R.id.btn_pass) {
                    showDialog(result, 1);
                } else if (view.getId() == R.id.btn_back) {
                    showDialog(result, 2);
                } else if (view.getId() == R.id.btn_delegate) {
                    showDialog(result, 3);
                } else if (view.getId() == R.id.btn_history) {
                    gotoHistoryActivity(result.getProcInstId());
                }
            }
        });
    }

    private void gotoHistoryActivity(String procInstId) {
        ARouter.getInstance()
                .build("/handle/HistoryActivity")
                .withString("procInstId", procInstId)
                .navigation();
    }

    @Override
    public void onPass() {
        chooseDialog = null;
        pageNumber = 1;
        presenter.getTodoList(pageNumber, pageSize);
    }

    @Override
    public void onBack() {
        chooseDialog = null;
        pageNumber = 1;
        presenter.getTodoList(pageNumber, pageSize);
    }

    @Override
    public void onDelegate() {

    }

    @Override
    public void onNextNode(ProcessNodeVo processNodeVo) {

    }

    private void showDialog(final TodoResult result, final int type) {
        View view = getLayoutInflater().inflate(R.layout.dialog_todo_pass, null);
        chooseDialog = new Dialog(mContext, R.style.transparentFrameWindowStyle);
        chooseDialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = chooseDialog.getWindow();
        // 设置显示动画
        if (window != null) {
            window.setWindowAnimations(R.style.main_menu_animstyle);
            WindowManager.LayoutParams wl = window.getAttributes();
            wl.x = 0;
            wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
            // 以下这两句是为了保证按钮可以水平满屏
            wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
            wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            // 设置显示位置
            chooseDialog.onWindowAttributesChanged(wl);
        }

        final TextView title = chooseDialog.findViewById(R.id.dialog_title);

        if (type == 1) {
            title.setText(result.getName() + "通过");
        } else if (type == 2) {
            title.setText(result.getName() + "驳回至发起人");
        } else if (type == 3) {
            title.setText(result.getName() + "委托");
        }

        // 设置点击外围解散
        chooseDialog.setCanceledOnTouchOutside(true);
        chooseDialog.findViewById(R.id.todo_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    presenter.getNextNode(result.getProcDefId(), result.getKey());

                //审批意见
                EditText comment = chooseDialog.findViewById(R.id.dialog_message);
                //发送消息类型
                CheckBox sendMessage = chooseDialog.findViewById(R.id.dialog_cb1);
                CheckBox sendSms = chooseDialog.findViewById(R.id.dialog_cb2);
                CheckBox sendEmail = chooseDialog.findViewById(R.id.dialog_cb3);
                String[] assignees = null;
                if (!TextUtils.isEmpty(result.getAssignee())) {
                    assignees = result.getAssignee().split(",");
                }
                if (type == 1) {
                    presenter.pass(result.getId(),
                            result.getProcInstId(),
                            assignees,
                            result.getPriority(),
                            comment.getText().toString(),
                            sendMessage.isChecked(),
                            sendSms.isChecked(),
                            sendEmail.isChecked());
                } else if (type == 2) {
                    presenter.back(result.getId(),
                            result.getProcInstId(),
                            comment.getText().toString(),
                            sendMessage.isChecked(),
                            sendSms.isChecked(),
                            sendEmail.isChecked());
                } else if (type == 3) {
                    // todo
                }

                chooseDialog.dismiss();
            }
        });
        chooseDialog.show();
    }
}
