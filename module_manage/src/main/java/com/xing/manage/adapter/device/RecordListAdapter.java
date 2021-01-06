package com.xing.manage.adapter.device;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xing.manage.R;
import com.xing.manage.bean.device.Record;

import java.util.List;

public class RecordListAdapter extends BaseQuickAdapter<Record, BaseViewHolder> {

    public RecordListAdapter(int layoutResId, @Nullable List<Record> data) {
        super(layoutResId, data);
    }

    public RecordListAdapter(@Nullable List<Record> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Record item) {
        helper.setText(R.id.tv_record_name, item.inspectionId.toString())
                .setText(R.id.tv_line, "线路：" + item.lineId)
                .setText(R.id.tv_area, "区域：" + item.areaId)
                .setText(R.id.tv_facility, "设备：" + item.facilityId)
                .setText(R.id.tv_start,"开始时间：" +item.startTime)
                .setText(R.id.tv_end,"结束时间：" +item.endTime)
                .setText(R.id.tv_type,"类型：" +item.type)
                .setText(R.id.tv_check_value,"检测结果：" +item.value)
                .setText(R.id.tv_remark,"备注：" +item.remark)

                .addOnClickListener(R.id.btn_upload)
                .addOnClickListener(R.id.btn_history)
                .addOnClickListener(R.id.btn_start_check);
        if (item.checkState.trim().equals("正常")) {
            helper.setText(R.id.tv_state, "正常")
                    .setTextColor(R.id.tv_state, mContext.getResources().getColor(R.color.green))
                    .setBackgroundRes(R.id.tv_state, R.drawable.textview_border_green);
        } else if (item.checkState.trim().equals("异常")) {
            helper.setText(R.id.tv_state, "异常")
                    .setTextColor(R.id.tv_state, mContext.getResources().getColor(R.color.orange))
                    .setBackgroundRes(R.id.tv_state, R.drawable.textview_border_orange);
        } else if (item.checkState.trim().equals("严重")) {
            helper.setText(R.id.tv_state, "严重")
                    .setTextColor(R.id.tv_state, mContext.getResources().getColor(R.color.red))
                    .setBackgroundRes(R.id.tv_state, R.drawable.textview_border_red);
        }
    }
}
