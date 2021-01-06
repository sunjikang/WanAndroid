package com.xing.manage.activity.device;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xing.commonbase.base.BaseActivity;
import com.xing.commonbase.util.ToastUtil;
import com.xing.manage.R;
import com.xing.manage.adapter.device.AreaListAdapter;

import com.xing.manage.bean.device.Area;
import com.xing.manage.bean.device.Facility;
import com.xing.manage.bean.device.Inspection;
import com.xing.manage.bean.device.Level1Item;
import com.xing.manage.bean.device.Level2Item;
import com.xing.manage.bean.device.Level3Item;
import com.xing.manage.bean.device.Line;
import com.xing.manage.db.AreaDao;
import com.xing.manage.db.DbManager;
import com.xing.manage.db.FacilityDao;
import com.xing.manage.db.InspectionDao;
import com.xing.manage.db.LineDao;


import java.util.ArrayList;
import java.util.List;

@Route(path = "/manage/device/AreaListActivity")
public class AreaListActivity  extends BaseActivity {
    Line line ;//传递过来的bean
    private RecyclerView recyclerView;
    private ArrayList<Area> dataList = new ArrayList<Area>();
    private AreaListAdapter adapter;
    private ArrayList<MultiItemEntity> arrayList=new ArrayList<MultiItemEntity>();

    public  static final  int CHECK_RESULT = 123;
    private int position;//line列表页面的position

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_arealist;
    }

    @Override
    protected void initView() {
        Toolbar toolbar =findViewById(R.id.toolbar_title);
        toolbar.setTitle("巡检列表");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
        recyclerView = findViewById(R.id.rv_todo);
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle;
        long  lineId = this.getIntent().getExtras().getLong("lineId");
        position = this.getIntent().getExtras().getInt("position");
        line = DbManager.getInstance().getLineDao().queryBuilder().where(LineDao.Properties.Mmid.eq(lineId)).list().get(0);
        dataList = (ArrayList<Area>) line.dmList;

        for (Area area:line.dmList) {
            Level1Item level1Item = new Level1Item();
            level1Item.area = area;
            arrayList.add(level1Item) ;
                for (Facility facility: area.facilityList){
                    Level2Item level2Item = new Level2Item();
                    level2Item.facility = facility;
                    level1Item.addSubItem(level2Item);
                    for (Inspection inspection :facility.inspectionItemList) {
                        Level3Item level3Item = new Level3Item();
                        level3Item.inspection = inspection;
                        level2Item.addSubItem(level3Item);
                    }
                }
        }
        setAdapte();

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId()==R.id.tv_detail){
                    switch (adapter.getItemViewType(position)){

                    case AreaListAdapter.TYPE_LEVEL_1:
                        Level1Item item1 = (Level1Item) adapter.getItem(position);
                        showDialog(item1.area.title,item1.area.toString());
                        break;
                    case AreaListAdapter.TYPE_LEVEL_2:
                        Level2Item item2 = (Level2Item) adapter.getItem(position);
                        showDialog(item2.facility.name,item2.facility.toString());
                        break;
                    case AreaListAdapter.TYPE_LEVEL_3:
                        Level3Item item3 = (Level3Item) adapter.getItem(position);
                        showDialog(item3.inspection.inspectionItemName,item3.inspection.toString());
                        break;

                }
                }
                else if(view.getId()==R.id.ll_layout){
                    switch (adapter.getItemViewType(position)){

                        case AreaListAdapter.TYPE_LEVEL_1:
                            break;
                        case AreaListAdapter.TYPE_LEVEL_2:
                            break;
                        case AreaListAdapter.TYPE_LEVEL_3:
                            Level3Item item3 = (Level3Item) adapter.getItem(position);
                            Facility facility = DbManager.getInstance().getFacilityDao().queryBuilder().where(FacilityDao.Properties.Mmid.eq(item3.inspection.facilityId)).list().get(0);
                            Area area = DbManager.getInstance().getAreaDao().queryBuilder().where(AreaDao.Properties.Mmid.eq(facility.areaId)).list().get(0);
                            Bundle bundle = new Bundle();
                            bundle.putLong("lineId",line.mmid);
                            bundle.putLong("areaId",area.mmid);
                            bundle.putLong("facilityId",facility.mmid);
                            bundle.putLong("inspectionId",item3.inspection.mmid);
                            bundle.putInt("position",position);

                            ARouter.getInstance().build("/manage/device/DeviceDetailActivity").with(bundle).navigation(AreaListActivity.this,CHECK_RESULT);
                            break;
                    }
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case CHECK_RESULT:
                //todo  保存成功得到当前巡检项
                long lineId = (long) data.getExtras().get("lineId");
                List<Inspection> list = DbManager.getInstance().getInspectionDao().queryBuilder().where(InspectionDao.Properties.IsCheckOver.eq(false)).where(InspectionDao.Properties.LineId.eq(lineId)).list();

                //todo 根据当前巡检项数量  判断当前线路是否全部完成
                //还有任务没有完成呢
                if (list.size()>0){
                        Log.e("sjk",list.size()+"");

                    //todo  如果没有全部完成，把已完成的检测项置灰 不可点击
                    int position = (int) data.getExtras().get("position");
                    Level3Item level3Item = (Level3Item) arrayList.get(position);
                    level3Item.inspection.isCheckOver = true;
                    adapter.notifyItemChanged(position);
                }else {
                    //todo  如果已完成则提示已完成，并关闭当前页面 关闭前把结束时间存储在巡检记录中
                    ToastUtil.show(this,"当前任务都已经完成");
                    //返回给线路页面的数据
                    Intent intent  = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putLong("lineId", lineId);
                    bundle.putInt("position", position);
                    intent.putExtras(bundle);
                    setResult(LineListActivity.RESULT_OK,intent);

                     finish();

                }


                 break;
        }
    }

    private void showDialog(String title, String strDetail) {
        new MaterialDialog.Builder(AreaListActivity.this).title(title+"详情").content(strDetail)
                .negativeText("关闭")
                .negativeColor(Color.RED)
                .autoDismiss(true)
                .show();
    }


    private void setAdapte() {
        if (adapter == null) {
            adapter = new AreaListAdapter(arrayList);
            final LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setNewData(arrayList);
        }
    }



}
