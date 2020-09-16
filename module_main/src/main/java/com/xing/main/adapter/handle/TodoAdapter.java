package com.xing.main.adapter.handle;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xing.main.R;
import com.xing.main.bean.handle.TodoResult;

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
        helper.setText(R.id.tv_handle_name,item.getName())
                .setText(R.id.tv_handle_applyer,item.getApplyer())
                .setText(R.id.tv_handle_pname,item.getProcessName())
                .addOnClickListener(R.id.test_btn1)
                .addOnClickListener(R.id.test_btn2);
    }
}
