package com.xing.main.adapter.handle;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xing.main.R;
import com.xing.main.bean.xboot.TodoResult;

import java.util.List;

public class TodoAdapter extends BaseQuickAdapter<TodoResult, BaseViewHolder> {

    public TodoAdapter(int layoutResId, @Nullable List<TodoResult> data) {
        super(layoutResId, data);
    }

    public TodoAdapter(@Nullable List<TodoResult> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TodoResult item) {
        helper.setText(R.id.tv_handle_name, item.getName())
                .setText(R.id.tv_handle_pname, "任务：" + item.getProcessName())
                .setText(R.id.tv_handle_applyer, "发起人：" + item.getApplyer())
                .setText(R.id.tv_handle_owner, "委托人：" + item.getOwner())
                .setGone(R.id.tv_handle_owner, !TextUtils.isEmpty(item.getOwner()))
                .setText(R.id.tv_handle_createtime,"创建时间："+item.getCreateTime())
                .addOnClickListener(R.id.btn_detail)
                .addOnClickListener(R.id.btn_pass)
                .addOnClickListener(R.id.btn_back)
                .addOnClickListener(R.id.btn_delegate)
                .addOnClickListener(R.id.btn_history);
        if (item.getPriority() == 0) {
            helper.setText(R.id.tv_handle_priority, "普通")
                    .setTextColor(R.id.tv_handle_priority, mContext.getResources().getColor(R.color.green))
                    .setBackgroundRes(R.id.tv_handle_priority, R.drawable.textview_border_green);
        } else if (item.getPriority() == 1) {
            helper.setText(R.id.tv_handle_priority, "重要")
                    .setTextColor(R.id.tv_handle_priority, mContext.getResources().getColor(R.color.orange))
                    .setBackgroundRes(R.id.tv_handle_priority, R.drawable.textview_border_orange);
        } else if (item.getPriority() == 2) {
            helper.setText(R.id.tv_handle_priority, "紧急")
                    .setTextColor(R.id.tv_handle_priority, mContext.getResources().getColor(R.color.red))
                    .setBackgroundRes(R.id.tv_handle_priority, R.drawable.textview_border_red);
        }
    }
}
