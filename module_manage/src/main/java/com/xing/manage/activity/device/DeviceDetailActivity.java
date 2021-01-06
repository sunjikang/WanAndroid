package com.xing.manage.activity.device;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.Handler;
import android.os.Message;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
 import android.support.v7.widget.Toolbar;

import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;



import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.coorchice.library.SuperTextView;
import com.devil.library.media.MediaSelectorManager;
 import com.devil.library.media.config.DVCameraConfig;
import com.devil.library.media.config.DVListConfig;
import com.devil.library.media.enumtype.DVMediaType;
import com.devil.library.media.listener.OnSelectMediaListener;
import com.xing.commonbase.base.BaseActivity;
import com.xing.commonbase.util.StatusBarUtil;
import com.xing.commonbase.util.ToastUtil;
import com.xing.manage.R;
import com.xing.manage.bean.device.Area;
import com.xing.manage.bean.device.Facility;
import com.xing.manage.bean.device.Inspection;
import com.xing.manage.bean.device.Line;
import com.xing.manage.bean.device.Record;
import com.xing.manage.bean.device.Resource;
import com.xing.manage.db.DbManager;
import com.xing.manage.db.FacilityDao;
import com.xing.manage.db.InspectionDao;
import com.xing.manage.db.RecordDao;
import com.xing.manage.db.ResourceDao;
import com.xing.manage.util.TimeUtil;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Route(path = "/manage/device/DeviceDetailActivity")
public class DeviceDetailActivity extends BaseActivity implements View.OnClickListener{
    private ScrollView scrollView;
     private SuperTextView  supertv1;
    private SuperTextView  supertv2;
    private SuperTextView  supertv3;
    private SuperTextView  supertv4;

    private Button btn3;
    private Button btn6;
    private TextView tvResult;

    private ImageView iv_result1;
    private ImageView iv_result2;
    private ImageView iv_result3;
    private ImageView iv_result4;
    private ImageView iv_result5;
    private ImageView iv_result6;
    private ImageView iv_result7;
    private ImageView iv_result8;
    private ImageView iv_result9;
    List<ImageView> imageViewList=new ArrayList<ImageView>();

    private LinearLayout rg_layout;
    private SuperTextView text1;
    private SuperTextView text2;
    private  SuperTextView text3;
    /**备注*/
    private ConstraintLayout cl_have_value;
    private TextInputEditText tie_remark;//备注
    private TextInputEditText tie_check_value;//具体值

    /**刚进入的时间*/
    long oldTime;
    /**需要此刻用的时间*/
    long newTime;
    /**安全事件*/
    boolean isSafe=true;
    /**安全时间*/
    long safeTime =2*60*1000;
    /**警告时间*/
    long alertTime =1*60*1000;

    private   Handler myhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            newTime=System.currentTimeMillis();
            String timeStr=  TimeUtil.formatDateTime((safeTime-(newTime-oldTime))/1000);
             text3.setText("检测倒计时："+timeStr);
            if (newTime-oldTime>safeTime){
                isSafe=false;
                text3.setTextColor(Color.RED);
                text3.setText("检测倒计时：已经超时");
            }else if(newTime-oldTime>alertTime){
                 text3.setTextColor(Color.parseColor("#ff9900"));
             }
        }
    };
    private RadioGroup radioGroup;
    private Inspection inspection;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        isSafe=false;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_check_common;
    }

    @Override
    protected void initView() {
        //键盘顶起输入框
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        scrollView=findViewById(R.id.scrollView);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);

        tie_check_value=findViewById(R.id.tie_check_value);
        supertv1=findViewById(R.id.supertv1);
        supertv2=findViewById(R.id.supertv2);
        supertv3=findViewById(R.id.supertv3);
        supertv4=findViewById(R.id.supertv4);
        tie_remark=findViewById(R.id.TextInputEditText);

        

        btn3=   findViewById(R.id.button3);

        btn6=   findViewById(R.id.button6);
        btn3.setOnClickListener(this);

        btn6.setOnClickListener(this);
        tvResult=findViewById(R.id.textView7);
        rg_layout=findViewById(R.id.rg_layout);
         cl_have_value=findViewById(R.id.cl_have_value);
         iv_result1=findViewById(R.id.iv_result1);
        iv_result2=findViewById(R.id.iv_result2);
        iv_result3=findViewById(R.id.iv_result3);
        iv_result4=findViewById(R.id.iv_result4);
        iv_result5=findViewById(R.id.iv_result5);
        iv_result6=findViewById(R.id.iv_result6);
        iv_result7=findViewById(R.id.iv_result7);
        iv_result8=findViewById(R.id.iv_result8);
        iv_result9=findViewById(R.id.iv_result9);
        imageViewList.add(iv_result1);
        imageViewList.add(iv_result2);
        imageViewList.add(iv_result3);
        imageViewList.add(iv_result4);
        imageViewList.add(iv_result5);
        imageViewList.add(iv_result6);
        imageViewList.add(iv_result7);
        imageViewList.add(iv_result8);
        imageViewList.add(iv_result9);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.state_nomal) {
                    checkState="正常";
                } else if (checkedId == R.id.state_exception) {
                    checkState="异常";
                }
                else if (checkedId == R.id.state_danger){
                    checkState="严重";
                }
            }
        });
     }



    private int position;
    /**检测是否正常*/
    private String checkState="正常";
    private long lineId;
    private long areaId;
    private long facilityId;
    private long inspectionId;
    @Override
    protected void initData() {
         super.initData();
         Bundle bean = getIntent().getExtras();
        lineId=  bean.getLong("lineId");
        areaId=  bean.getLong("areaId");
        facilityId=  bean.getLong("facilityId");
        inspectionId=  bean.getLong("inspectionId");
        position=  bean.getInt("position");
        inspection = DbManager.getInstance().getInspectionDao().queryBuilder().where(InspectionDao.Properties.Mmid.eq(inspectionId)).list().get(0);
        //todo  传感器回调值

         Toolbar toolbar=findViewById(R.id.toolbar_title);
        toolbar.setTitle(inspection.inspectionItemName);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeviceDetailActivity.this.finish();
            }
        });

        text1.setText("巡检项："+inspection.inspectionItemName);
        text2.setText("检测类型："+inspection.pollingType);


        /*
            温度 速度 加速度 位移 抄表 转速 观察 录入 预设状况
        * */
        if (inspection.pollingType.equals("温度")){
            showBackValue(true,inspection);
        }else if (inspection.pollingType.equals("速度")){
            showBackValue(true,inspection);
        }else if (inspection.pollingType.equals("加速度")){
            showBackValue(true,inspection);
        }else if (inspection.pollingType.equals("位移")){
            showBackValue(true,inspection);
        }else if (inspection.pollingType.equals("抄表")){
            showBackValue(false,inspection);
        }else if (inspection.pollingType.equals("转速")){
            showBackValue(true,inspection);
        }else if (inspection.pollingType.equals("观察")){
            showBackValue(false,inspection);
        }else if (inspection.pollingType.equals("录入")){
            showBackValue(false,inspection);
        }else if (inspection.pollingType.equals("预设情况")){
            showBackValue(false,inspection);
        }

        oldTime=System.currentTimeMillis();
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (isSafe){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    myhandler.sendEmptyMessage(0);
                }
            }
        }.start();
    }

    /***
     *
     * @param visible false 为观察类      true 为传感器检测
     * @param inspection
     */
    private void showBackValue(boolean visible,Inspection inspection) {
        if (visible){
            cl_have_value.setVisibility(View.VISIBLE);
            rg_layout.setVisibility(View.GONE);
            supertv1.setText("最高危险值："+inspection.upperUp);
            supertv2.setText("偏高警告值："+inspection.upper);
            supertv3.setText("偏低警告值："+inspection.floor);
            supertv4.setText("最小危险值："+inspection.floorFl);
        }else{
            cl_have_value.setVisibility(View.GONE);
            rg_layout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button3){
            showPupToSelectPhoto();
        }else if(v.getId()==R.id.button6){
            //todo 保存巡检任务
            savaData();
        }
    }

    private void savaData() {
        InspectionDao inspectionDao = DbManager.getInstance().getInspectionDao();
        inspection.isCheckOver = true;  //检测完成
        inspectionDao.update(inspection);//保存更新
        String strRemark =  tie_remark.getText().toString();
        String strCheckValue = tie_check_value.getText().toString().trim();
        RecordDao recordDao = DbManager.getInstance().getRecordDao();

        Record   record = new Record();
        record.lineId= lineId;
        record.areaId= areaId;
        record.facilityId=facilityId;
        record.inspectionId= inspection.mmid;
        record.type = inspection.pollingType;
        record.value = strCheckValue;
        record.remark = strRemark;
         if (checkState.equals("正常")){
            record.checkState= "正常";
        }else if (checkState.equals("异常")){
            record.checkState="异常";
        }else if(checkState.equals("严重")){
            record.checkState="严重";
        }

        recordDao.insert(record);
        ResourceDao resourceDao= DbManager.getInstance().getResourceDao();
        //生成资源数据
        for (int i = 0; i <stringList.size() ; i++){
             Resource resource = new Resource();
            resource.recordId = record.mmid;
            resource.path = stringList.get(i);
            resourceDao.insert(resource);
        }
        ToastUtil.show(this,"保存成功");
        //返回给区域页面的数据
         Intent intent  = new Intent();
         Bundle bundle = new Bundle();
         bundle.putLong("lineId", lineId);
         bundle.putInt("position", position);
         intent.putExtras(bundle);
         setResult(AreaListActivity.CHECK_RESULT,intent);
         finish();
    }

    private EasyPopup mCirclePop;

    private void showPupToSelectPhoto() {
        mCirclePop = EasyPopup.create()
                .setContentView(DeviceDetailActivity.this, R.layout.popup_select_photo)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.4f)
                //变暗的背景颜色
                .setDimColor(Color.GRAY)
                //指定任意 ViewGroup 背景变暗
                .setDimView(scrollView)
                .apply();
        mCirclePop.showAtAnchorView(btn3, YGravity.CENTER, XGravity.RIGHT, 0, 0);
        mCirclePop.findViewById(R.id.btn_camara).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCirclePop.dismiss();
                openCamera();
            }
        });
        mCirclePop.findViewById(R.id.btn_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCirclePop.dismiss();
                selectPhotos();
            }
        });
        mCirclePop.findViewById(R.id.btn_vedio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCirclePop.dismiss();

                selectVideos();
            }
        });
    }


    /**
     * 多选视频快速加载测试
     */
    public void selectVideos(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //这里才是开始调用
                        DVListConfig config = MediaSelectorManager.getDefaultListConfigBuilder()
                                .mediaType(DVMediaType.VIDEO)
                                .quickLoadVideoThumb(true)
                                .hasPreview(true).build();
                        //打开界面
                        MediaSelectorManager.openSelectMediaWithConfig(DeviceDetailActivity.this,config, new OnSelectMediaListener() {
                            @Override
                            public void onSelectMedia(List<String> li_path) {
                                for (String path : li_path) {
                                    tvResult.append(path + "\n");
                                    showRes(path);
                                }
                            }
                        });
                    }
                });
            }
        }).start();

    }



    /**
     * 选择图片
     */
    public void selectPhotos() {
        DVListConfig config = MediaSelectorManager.getDefaultListConfigBuilder()
                // 是否多选
                .multiSelect(true)
                //第一个菜单是否显示照相机
                .needCamera(true)
                //第一个菜单显示照相机的图标
                .cameraIconResource(R.mipmap.take_photo)
                //每行显示的数量
                .listSpanCount(4)
                // 确定按钮文字颜色
                .sureBtnTextColor(Color.WHITE)
                // 使用沉浸式状态栏
                .statusBarColor(Color.parseColor("#3F51B5"))
                // 返回图标ResId
                .backResourceId(R.mipmap.icon_dv_arrow_left_white_back)
                //标题背景
                .titleBgColor(Color.parseColor("#3F51B5"))
                //是否需要裁剪
                .needCrop(false)
                //裁剪大小
                .cropSize(1, 1, 200, 200)
                .build();

        MediaSelectorManager.openSelectMediaWithConfig(DeviceDetailActivity.this, config, new OnSelectMediaListener() {
            @Override
            public void onSelectMedia(List<String> li_path) {
                for (String path : li_path) {
                    showRes(path);
                }
            }
        });
    }
    List<String> stringList=new ArrayList<String>();

    /***
     * 显示到layout中
     * @param path 资源路径地址
     */
    private void showRes(String path) {
        tvResult.append(path + "\n");
        stringList.add(path);
        for (int i = 0; i <stringList.size() ; i++) {
            imageViewList.get(i).setVisibility(View.VISIBLE);

            Glide .with( DeviceDetailActivity.this )
                    .load( Uri.fromFile( new File( stringList.get(i) ) ) )
                    .into(  imageViewList.get(i));
        }

    }

    /**
     * 打开照相机
     */
    public void openCamera() {
        tvResult.setText("");
        DVCameraConfig config = MediaSelectorManager.getDefaultCameraConfigBuilder()
                //是否使用系统照相机（默认使用仿微信照相机）
                .isUseSystemCamera(false)
                //是否需要裁剪
                .needCrop(false)
                //裁剪大小
                .cropSize(1, 1, 200, 200)
                //媒体类型（如果是使用系统照相机，必须指定DVMediaType.PHOTO或DVMediaType.VIDEO）
                .mediaType(DVMediaType.ALL)
                //设置录制时长
                .maxDuration(10)
                .build();

        MediaSelectorManager.openCameraWithConfig(DeviceDetailActivity.this, config, new OnSelectMediaListener() {
            @Override
            public void onSelectMedia(List<String> li_path) {
                for (String path : li_path) {
                    showRes(path);
                }
            }
        });
    }

}
