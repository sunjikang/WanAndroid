package com.xing.main.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.xing.commonbase.base.BaseMVPFragment;
import com.xing.commonbase.constants.Constants;
import com.xing.commonbase.util.BlurUtil;
import com.xing.commonbase.util.SharedPreferenceUtil;
import com.xing.commonbase.widget.CircleImageView;
import com.xing.commonbase.widget.ItemView;
import com.xing.commonbase.widget.ZoomScrollView;
import com.xing.main.R;
import com.xing.main.activity.MainActivity;
import com.xing.main.annotation.UserLoginTrace;
import com.xing.main.bean.xboot.User;
import com.xing.main.contract.MineContract;
import com.xing.main.presenter.MinePresenter;

public class MineFragment extends BaseMVPFragment<MinePresenter> implements MineContract.View, View.OnClickListener {

    private ZoomScrollView scrollView;
    private ImageView backImgView;
    private CircleImageView circleImageView;
    private TextView nicknameView;
    private TextView departmentView;

    private View avatarLayout;
    private TextView meiziView;
    private ItemView favoriteItemView;
    private ItemView meiziItemView;
    private ItemView aboutItemView;
    private ItemView logoutItemView;

    public MineFragment() {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View rootView) {
        backImgView = rootView.findViewById(R.id.iv_avatar_background);
        circleImageView = rootView.findViewById(R.id.civ_avatar);
        nicknameView = rootView.findViewById(R.id.tv_nickname);
        departmentView = rootView.findViewById(R.id.tv_department);


        scrollView = rootView.findViewById(R.id.sv_scroll);
//        avatarLayout = rootView.findViewById(R.id.rl_layout);
        favoriteItemView = rootView.findViewById(R.id.iv_mine_favorite);
        meiziItemView = rootView.findViewById(R.id.iv_mine_meizi);
        aboutItemView = rootView.findViewById(R.id.iv_mine_about);
        logoutItemView = rootView.findViewById(R.id.iv_mine_logout);
    }

    @Override
    protected void initData() {
        scrollView.setZoomView(backImgView);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        backImgView.setImageBitmap(BlurUtil.blur(mContext, bitmap, 18));


        favoriteItemView.setOnClickListener(this);
        meiziItemView.setOnClickListener(this);
        aboutItemView.setOnClickListener(this);
        logoutItemView.setOnClickListener(this);

        presenter.getUserInfo();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_mine_about) {
            gotoAboutActivity();
        } else if (v.getId() == R.id.iv_mine_favorite) {
            gotoFavoriteActivity();
        } else if (v.getId() == R.id.iv_mine_meizi) {
            gotoMeiziActivity();
        } else if (v.getId() == R.id.iv_mine_logout) {
            gotoLoginActivity();
        }
    }

    private void gotoMeiziActivity() {
        ARouter.getInstance()
                .build("/gank/ImageMeiziActivity")
                .navigation();
    }

    @UserLoginTrace(value = 0)
    private void gotoFavoriteActivity() {
        ARouter.getInstance()
                .build("/main/FavoriteActivity")
                .navigation();
    }

    private void gotoAboutActivity() {
        ARouter.getInstance()
                .build("/main/AboutActivity")
                .navigation();
    }

    /**
     * 跳转登录界面
     */
    private void gotoLoginActivity() {
        MainActivity mainActivity = (MainActivity) mContext;
        mainActivity.finish();
        SharedPreferenceUtil.write(Constants.File_TOKEN, Constants.ACCESS_TOKEN, "");
        ARouter.getInstance().build("/user/LoginActivity").navigation();
    }

    @Override
    public void onUserInfo(User user) {
        if (!TextUtils.isEmpty(user.getAvatar())) {
            Glide.with(mContext).load(user.getAvatar()).into(backImgView);
            Glide.with(mContext).load(user.getAvatar()).into(circleImageView);
        }
        nicknameView.setText(user.getNickname());
        departmentView.setText(user.getDepartmentTitle());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
