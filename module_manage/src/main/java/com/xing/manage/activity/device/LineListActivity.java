package com.xing.manage.activity.device;




import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xing.commonbase.base.BaseMVPActivity;
import com.xing.commonbase.mvp.IPresenter;
import com.xing.commonbase.util.NetworkUtil;
import com.xing.commonbase.util.ToastUtil;
import com.xing.manage.R;
import com.xing.manage.adapter.device.LineListAdapter;
import com.xing.manage.bean.device.Area;
import com.xing.manage.bean.device.Facility;
import com.xing.manage.bean.device.Inspection;

import com.xing.manage.bean.device.Level3Item;
import com.xing.manage.bean.device.Line;
import com.xing.manage.contract.device.LineContract;
import com.xing.manage.db.AreaDao;
import com.xing.manage.db.DbManager;
import com.xing.manage.db.FacilityDao;
import com.xing.manage.db.InspectionDao;
import com.xing.manage.db.LineDao;
import com.xing.manage.presenter.device.LinePresenter;
import com.xing.manage.util.TimeUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.internal.operators.single.SingleDelayWithCompletable;

@Route(path = "/manage/device/LineListActivity")
public class LineListActivity extends BaseMVPActivity<LinePresenter> implements LineContract.View, View.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<Line> dataList = new ArrayList<Line>();
    private LineListAdapter adapter;
    public static final int RESULT_OK=111;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_linelist;
    }

    @Override
    protected LinePresenter createPresenter() {

        return new LinePresenter();
    }

    @Override
    protected void initView() {

        Toolbar toolbar =findViewById(R.id.toolbar_title);
        toolbar.setTitle("任务列表");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineListActivity.this.finish();
            }
        });

        recyclerView = findViewById(R.id.rv_todo);

    }

    @Override
    protected void initData() {
        super.initData();

        if (NetworkUtil.isNetworkAvailable(this)){
            presenter.getAllLine();
        }else {
            fromSql();
        }



    }

    private void fromSql() {
        LineDao lineDao = DbManager.getInstance().getLineDao();
        lineDao.queryBuilder().list();
        setAdapte();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void getAllLine(List<Line> list) {
        dataList.addAll(list);
       saveToSql();
      // fromSql();
        setAdapte();
    }

    private void setAdapte() {
        if (adapter == null) {
            adapter = new LineListAdapter(R.layout.item_line,dataList);
            final LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setNewData(dataList);
        }

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int viewId = view.getId();
                if ( viewId== R.id.btn_start_check) {
                    Line line = dataList.get(position);
                    //todo 根据巡检周期  是否已巡检进行状态改变和提示
                    //todo 获得巡检周期
                     Log.e("sjk","巡检周期"+line.inspectionPeriod);
                    //根据当前系统时间获得巡检周期的时间上限和下限和次数
                        //开始时间
                      Long startPeriod=  TimeUtil.figureStartTime(line.inspectionPeriod);
                        //结束时间
                      Long endPeriod=  TimeUtil.figureEndTime(line.inspectionPeriod);
                        //todo 巡检次数
                      int periodCount = TimeUtil.figurePeriodCount(line.inspectionPeriod);
                    long currentTimeMillis = System.currentTimeMillis();
                    //再巡检周期内
                    if (currentTimeMillis>startPeriod&&currentTimeMillis<endPeriod){

                        if (line.taskType.equals("定期巡检")){
                            //获得上次巡检结束时间
                            if (line.startCheckTime==null){
                                gotoAreaListActivity(line,position);
                            }else{
                                //todo 是否还有巡检次数
                                //如果还有巡检次数  则继续巡检，否则提示已巡检过
                                ToastUtil.show(LineListActivity.this, "巡检已完成");
                            }
                        }else if (line.taskType.equals("临时巡检")){
                            //是否没有巡检过
                            if (line.startCheckTime==null){
                                gotoAreaListActivity(line,position);
                            }else {
                             ToastUtil.show(LineListActivity.this, "临时巡检已完成");
                            }

                        }




                        //查询此次周期上下限内的巡检次数。
//                        LineDao lineDao = DbManager.getInstance().getLineDao();
//                        List<Line> list = lineDao.queryBuilder().where(LineDao.Properties.StartCheckTime.gt(startPeriod)).where(LineDao.Properties.EndCheckTime.lt(endPeriod))
//                                .list();
//                        //获得这次周期内的巡检次数
//                        //还有可巡检次数
//                        if (periodCount > list.size()) {
//                            gotoAreaListActivity(line, position);//跳转到区域列表页面
//                        } else {
//                            ToastUtil.show(LineListActivity.this, "不可巡检");
//                        }


                    }else{
                    ToastUtil.show(LineListActivity.this,"不在巡检周期内");
                    }






                }else if(viewId == R.id.btn_history){
                    gotoRecordListActivity(dataList.get(position));
                }
            }

            private void gotoRecordListActivity(Line line) {
                Bundle bundle = new Bundle();
                bundle.putLong("lineId",line.mmid);
                ARouter.getInstance().build("/manage/device/RecordListActivity").with(bundle).navigation();
            }

            private void gotoAreaListActivity(Line line,int pos) {

                line.startCheckTime = System.currentTimeMillis();

                LineDao lineDao = DbManager.getInstance().getLineDao();
                 lineDao.update(line);
                Bundle bundle = new Bundle();
                bundle.putLong("lineId",line.mmid);
                bundle.putInt("position",pos);
                ARouter.getInstance().build("/manage/device/AreaListActivity").with(bundle).navigation(LineListActivity.this,RESULT_OK);
            }
        });

     }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case RESULT_OK:
                int position = (int) data.getExtras().get("position");
                long lineId = (long) data.getExtras().get("lineId");
                //保存巡检结束时间
                LineDao lineDao = DbManager.getInstance().getLineDao();
                Line line1 = dataList.get(position);
                if (line1!=null){
                    line1.endCheckTime =  System.currentTimeMillis();
                    lineDao.update(line1);
                }else{
                    Line  line = lineDao.queryBuilder().where(LineDao.Properties.Mmid.eq(lineId)).list().get(0);
                    line.endCheckTime =  System.currentTimeMillis();
                    lineDao.update(line);
                }
                    //刷新页面
                    adapter.notifyItemChanged(position);
                break;

        }
    }

    private void saveToSql() {
        LineDao lineDao = DbManager.getInstance().getLineDao();
        AreaDao areaDao = DbManager.getInstance().getAreaDao();
        FacilityDao facilityDao = DbManager.getInstance().getFacilityDao();
        InspectionDao inspectionDao = DbManager.getInstance().getInspectionDao();
        lineDao.deleteAll();
        areaDao.deleteAll();
        facilityDao.deleteAll();
        inspectionDao.deleteAll();
        for (Line line:dataList){
            lineDao.insert(line);
            if (line.dmList!=null&& line.dmList.size()>0){
                for (Area area : line.dmList){
                    area.lineId=line.mmid;
                    areaDao.insert(area);
                    if(area.facilityList!=null&&area.facilityList.size()>0){
                        for (Facility facility: area.facilityList){
                            facility.areaId=area.mmid;
                            facility.lineId = line.mmid;
                            facilityDao.insert(facility);
                            if(facility.inspectionItemList!=null&&facility.inspectionItemList.size()>0){
                                for (Inspection inspection: facility.inspectionItemList){
                                    inspection.facilityId=facility.mmid;
                                    inspection.areaId = area.mmid;
                                    inspection.lineId= line.mmid;
                                    inspection.isCheckOver=false;//初始化为未检测
                                    inspectionDao.insert(inspection);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
