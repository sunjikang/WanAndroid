package com.xing.manage.activity.device;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
 import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
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
import com.xing.manage.db.AreaDao;
import com.xing.manage.db.DbManager;
import com.xing.manage.db.FacilityDao;
 import com.xing.manage.db.LineDao;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Route(path = "/manage/device/DeviceDetailActivity")
public class DeviceDetailActivity extends BaseActivity implements View.OnClickListener{

    private TextInputEditText tie_0;
    private TextInputEditText tie_1;
    private TextInputEditText tie_2;
    private TextInputEditText tie_3;
    private TextInputEditText tie_4;
    private TextInputEditText tie_5;
    private TextInputEditText tie_6;
    private TextInputEditText tie_7;
    private TextInputEditText tie_8;
    private TextInputEditText tie_9;



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

     private ConstraintLayout constraintLayout;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_check_now;
    }

    @Override
    protected void initView() {
        //键盘顶起输入框
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
        Toolbar toolbar=findViewById(R.id.toolbar_title);
        toolbar.setTitle("检查项详情");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tie_0=findViewById(R.id.textInputedittext0);
        tie_1=findViewById(R.id.textInputedittext1);
        tie_2=findViewById(R.id.textInputedittext2);
        tie_3=findViewById(R.id.textInputedittext3);
        tie_4=findViewById(R.id.textInputedittext4);
        tie_5=findViewById(R.id.textInputedittext5);
        tie_6=findViewById(R.id.textInputedittext6);
        tie_7=findViewById(R.id.textInputedittext7);
        tie_8=findViewById(R.id.textInputedittext8);
        tie_9=findViewById(R.id.textInputedittext9);


        btn3=   findViewById(R.id.button3);

        btn6=   findViewById(R.id.button6);
         btn3.setOnClickListener(this);

        btn6.setOnClickListener(this);
        tvResult=findViewById(R.id.textView7);

        constraintLayout=findViewById(R.id.constraintLayout);
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
     }

    @Override
    protected void initData() {
        super.initData();
         Bundle bean = getIntent().getExtras();
        Inspection inspection=  bean.getParcelable("bean");
        Log.e("sjk",inspection.toString());

//        Inspection inspection1 = DbManager.getInstance().getInspectionDao().queryBuilder().where(InspectionDao.Properties.Id.eq(inspection.id)).list().get(0);
//        Log.e("sjk",inspection1.toString());

        Facility facility = DbManager.getInstance().getFacilityDao().queryBuilder().where(FacilityDao.Properties.Id.eq(inspection.facilityId)).list().get(0);
        Area area = DbManager.getInstance().getAreaDao().queryBuilder().where(AreaDao.Properties.Id.eq(facility.areaId)).list().get(0);

        Line line = DbManager.getInstance().getLineDao().queryBuilder().where(LineDao.Properties.Id.eq(area.lineId)).list().get(0);

        tie_0.setText(line.lineName);
        tie_1.setText(area.title);
        tie_2.setText(facility.name);
        tie_3.setText(inspection.inspectionItemName);
        tie_4.setText(inspection.createBy);
        tie_5.setText(inspection.createTime);
        tie_6.setText(inspection.updateTime);
        tie_7.setText(inspection.pollingType);
        tie_8.setText(inspection.defaultSpeed);
        tie_9.setText(inspection.remark);

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



        ToastUtil.show(this,"保存成功");

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
                .setDimView(constraintLayout)
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

            Glide
                    .with( DeviceDetailActivity.this )
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
