package com.xing.main.fragment;

import android.content.Context;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xing.commonbase.base.BaseMVPFragment;

import com.xing.commonbase.constants.Constants;
import com.xing.commonbase.util.NetworkUtil;
import com.xing.commonbase.util.SharedPreferenceUtil;
import com.xing.main.R;
import com.xing.main.adapter.FullyGridLayoutManager;
import com.xing.main.adapter.ItemGridDecoration;
import com.xing.main.adapter.ModelListAdapter;
import com.xing.main.bean.Model;
import com.xing.main.contract.HomeContract;
import com.xing.main.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseMVPFragment<HomePresenter> implements HomeContract.View, View.OnClickListener {

    private static final String TAG = "HomeFragment";
    private RecyclerView recyclerViewH5;
    private RecyclerView recyclerViewNative;
    private TextView textView1;
    private TextView textView2;
    private List<Model> modelList;
    private List<Model> modelListH5;
    private List<Model> modelListNative;
    private ModelListAdapter adapterH5;
    private ModelListAdapter adapterNative;
    private Context mContext;

    public HomeFragment() {

    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView(View rootView) {
        recyclerViewH5 = rootView.findViewById(R.id.re_h5);
        textView1 = rootView.findViewById(R.id.title1);
        recyclerViewNative = rootView.findViewById(R.id.re_Native);
        textView2 = rootView.findViewById(R.id.title2);
    }

    @Override
    protected void initData() {
        mContext = getActivity();
        modelListH5 = new ArrayList<Model>();
        modelListNative = new ArrayList<Model>();


        if (NetworkUtil.isNetworkAvailable(mContext)){
            presenter.getModels();
        }else{
            fromSharedPreference();
        }

    }

    private void showView() {
        if (modelListH5 != null && modelListH5.size() > 0) {
            String typeName = modelListH5.get(0).typeName;
            textView1.setText(typeName==null?"网络应用":typeName);
        }

        if (modelListNative != null && modelListNative.size() > 0) {
            String typename = modelListNative.get(0).typeName;
            textView2.setText(typename==null?"本地应用":typename);
        }


        ItemGridDecoration itemGridDecoration = new ItemGridDecoration(mContext, 1, R.color.gray_ea);
        recyclerViewH5.addItemDecoration(itemGridDecoration);
        adapterH5 = new ModelListAdapter(R.layout.item_model, modelListH5, mContext);
        FullyGridLayoutManager layoutManager = new FullyGridLayoutManager(mContext, 3, LinearLayoutManager.VERTICAL, false);
        recyclerViewH5.setLayoutManager(layoutManager);
        recyclerViewH5.setAdapter(adapterH5);


        recyclerViewNative.addItemDecoration(itemGridDecoration);
        adapterNative = new ModelListAdapter(R.layout.item_model, modelListNative, mContext);
        FullyGridLayoutManager layoutManager1 = new FullyGridLayoutManager(mContext, 3, LinearLayoutManager.VERTICAL, false);

        recyclerViewNative.setLayoutManager(layoutManager1);
        recyclerViewNative.setAdapter(adapterNative);

        adapterH5.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int viewId = view.getId();
                if (viewId == R.id.model_ll) {
                    gotoWebActivity(modelListH5.get(position).url);
                }
            }
        });

        adapterNative.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int viewId = view.getId();
                Model model = modelListNative.get(position);
                if (viewId == R.id.model_ll) {
                    if (model.name.equals("设备管理")) {
                        gotoLineListActivity();
                    } else if (model.name.equals("工作流程")) {
                        gotoManageActivity();
                    }
                }

            }
        });


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();


    }

    private void gotoDeviceActivity() {
        ARouter.getInstance()
                .build("/manage/device/DeviceActivity")
                .navigation();

    }

    private void gotoLineListActivity() {
        ARouter.getInstance()
                .build("/manage/device/LineListActivity")
                .navigation();

    }

    private void gotoManageActivity() {
        ARouter.getInstance()
                .build("/manage/ManageActivity")
                .navigation();
    }

    private void gotoWebActivity(String url) {
        ARouter.getInstance()
                .build("/web/WebViewActivity")
                .withString(Constants.URL, url)
                .navigation();
    }

    @Override
    public void getModels(List<Model> list) {
        Log.e("sjk","");
        String s = (String) JSONObject.toJSONString(list);
        SharedPreferenceUtil.write(Constants.MODLE_NAME, Constants.MODLE_NAME, s);
        splitData(list);

    }

    @Override
    public void getModelsFail(String e) {
        Log.e("sjk",e);
        fromSharedPreference();

    }

    private void splitData(List<Model> list) {
        modelList = list;
        for (int i = 0; i < modelList.size(); i++) {
            Model model = modelList.get(i);
            if (model.isHtml) {
                modelListH5.add(model);
            } else {
                modelListNative.add(model);
            }
        }
        showView();

    }


    public void fromSharedPreference() {
        try {
            String read = SharedPreferenceUtil.read(Constants.MODLE_NAME, Constants.MODLE_NAME, "");
            modelList = (List<Model>) JSON.parseArray(read, Model.class);
            splitData(modelList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String json = " [\n" +
            "        {\n" +
            "            \"id\": \"1345913137135751168\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:01:12\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:37:39\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"设备管理\",\n" +
            "            \"code\": \"1001\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sECEGQ.png\",\n" +
            "            \"url\": \"http://www.taobao.com\",\n" +
            "            \"isHtml\": false,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"本地应用\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345914493896626176\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:06:35\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:37:25\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"设备运转\",\n" +
            "            \"code\": \"1002\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sESSK0.png\",\n" +
            "            \"url\": \"http://www.jd.com\",\n" +
            "            \"isHtml\": true,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"能源管理\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345917520896462849\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:18:37\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:37:08\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"设备维护\",\n" +
            "            \"code\": \"1003\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sE9XPe.png\",\n" +
            "            \"url\": \"\",\n" +
            "            \"isHtml\": true,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"能源管理\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345917906076176385\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:20:09\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:36:58\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"工作流程\",\n" +
            "            \"code\": \"1004\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sECPVf.png\",\n" +
            "            \"url\": \"\",\n" +
            "            \"isHtml\": false,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"本地应用\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345918214470766592\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:21:22\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:36:45\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"状态加载\",\n" +
            "            \"code\": \"1005\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sE9xxA.png\",\n" +
            "            \"url\": \"http://http://www.baidu.comwww.baidu.com\",\n" +
            "            \"isHtml\": true,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"能源管理\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345919466839609345\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:26:21\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:36:17\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"业务信息\",\n" +
            "            \"code\": \"1006\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sE9L5D.png\",\n" +
            "            \"url\": \"http://www.baidu.comhttp://www.baidu.com\",\n" +
            "            \"isHtml\": true,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"能源管理\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345920432372584448\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:30:11\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:36:00\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"手机管理\",\n" +
            "            \"code\": \"1007\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sECMZV.png\",\n" +
            "            \"url\": \"\",\n" +
            "            \"isHtml\": false,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"本地应用\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345920620814274561\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:30:56\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:35:45\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"业务管理\",\n" +
            "            \"code\": \"1008\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sECSKI.png\",\n" +
            "            \"url\": \"\",\n" +
            "            \"isHtml\": false,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"本地应用\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345921774633750528\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:35:31\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:35:22\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"系统管理\",\n" +
            "            \"code\": \"1009\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sECnrq.png\",\n" +
            "            \"url\": \"\",\n" +
            "            \"isHtml\": false,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"本地应用\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345922112178753537\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:36:52\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:35:05\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"用户管理\",\n" +
            "            \"code\": \"1010\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sECJz9.png\",\n" +
            "            \"url\": \"\",\n" +
            "            \"isHtml\": false,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"本地应用\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345922425132552193\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:38:06\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:34:46\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"模块加载\",\n" +
            "            \"code\": \"1011\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sECAPg.png\",\n" +
            "            \"url\": \"http://www.jd.comhttp://www.jd.com\",\n" +
            "            \"isHtml\": true,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"能源管理\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345922715105759233\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:39:16\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:34:12\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"紧急信息\",\n" +
            "            \"code\": \"1012\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sEC9qP.png\",\n" +
            "            \"url\": \"\",\n" +
            "            \"isHtml\": false,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"本地应用\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345923112230850560\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:40:50\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:33:39\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"远端服务\",\n" +
            "            \"code\": \"1013\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sE9v2d.png\",\n" +
            "            \"url\": \"\",\n" +
            "            \"isHtml\": true,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"能源管理\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345923664029290497\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:43:02\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:31:53\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"应用管理\",\n" +
            "            \"code\": \"1014\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sECGRJ.png\",\n" +
            "            \"url\": \"\",\n" +
            "            \"isHtml\": false,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"本地应用\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345924379841794049\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:45:52\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:31:28\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"开放信息\",\n" +
            "            \"code\": \"1015\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sEC8G4.png\",\n" +
            "            \"url\": \"http://www.taobao.comhttp://www.taobao.com\",\n" +
            "            \"isHtml\": true,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"能源管理\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345924806859689984\",\n" +
            "            \"createBy\": \"222222\",\n" +
            "            \"createTime\": \"2021-01-04 10:47:34\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:31:10\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"版本加载\",\n" +
            "            \"code\": \"1016\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sECZxs.png\",\n" +
            "            \"url\": \"http://www.jd.comhttp://www.jd.com\",\n" +
            "            \"isHtml\": true,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"能源管理\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345938155618045953\",\n" +
            "            \"createBy\": \"15536988730\",\n" +
            "            \"createTime\": \"2021-01-04 11:40:37\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 09:30:50\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"事件更新\",\n" +
            "            \"code\": \"1017\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sECQaT.png\",\n" +
            "            \"url\": \"http://www.baidu.comhttp://www.baidu.com\",\n" +
            "            \"isHtml\": true,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"能源管理\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"1345938424519069696\",\n" +
            "            \"createBy\": \"15536988730\",\n" +
            "            \"createTime\": \"2021-01-04 11:41:41\",\n" +
            "            \"updateBy\": \"15536988730\",\n" +
            "            \"updateTime\": \"2021-01-06 12:47:06\",\n" +
            "            \"delFlag\": 0,\n" +
            "            \"openStatus\": 0,\n" +
            "            \"name\": \"权限管理\",\n" +
            "            \"code\": \"1018\",\n" +
            "            \"isEnable\": true,\n" +
            "            \"urlImage\": \"https://s3.ax1x.com/2021/01/06/sECmMn.png\",\n" +
            "            \"url\": \"\",\n" +
            "            \"isHtml\": false,\n" +
            "            \"backgroundColor\": \"ffffff\",\n" +
            "            \"textColor\": \"000000\",\n" +
            "            \"typeName\": \"本地应用\"\n" +
            "        }\n" +
            "    ]";

}
