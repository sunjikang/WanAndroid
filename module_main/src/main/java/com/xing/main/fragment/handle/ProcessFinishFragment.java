package com.xing.main.fragment.handle;

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
import com.xing.commonbase.util.BlurUtil;
import com.xing.commonbase.widget.CircleImageView;
import com.xing.commonbase.widget.ItemView;
import com.xing.commonbase.widget.ZoomScrollView;
import com.xing.main.R;
import com.xing.main.annotation.UserLoginTrace;
import com.xing.main.bean.xboot.User;
import com.xing.main.contract.MineContract;
import com.xing.main.presenter.MinePresenter;

public class ProcessFinishFragment extends BaseMVPFragment<MinePresenter> implements MineContract.View,  View.OnClickListener {

    private ZoomScrollView scrollView;
    private CircleImageView circleImageView;
    private TextView nicknameView;
    private TextView departmentView;

    private View avatarLayout;
    private TextView meiziView;
    private ItemView favoriteItemView;
    private ItemView meiziItemView;
    private ItemView aboutItemView;

    public ProcessFinishFragment() {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View rootView) {
        circleImageView = rootView.findViewById(R.id.civ_avatar);
        nicknameView = rootView.findViewById(R.id.tv_nickname);
        departmentView = rootView.findViewById(R.id.tv_department);


        scrollView = rootView.findViewById(R.id.sv_scroll);
//        avatarLayout = rootView.findViewById(R.id.rl_layout);
        favoriteItemView = rootView.findViewById(R.id.iv_mine_favorite);
        meiziItemView = rootView.findViewById(R.id.iv_mine_meizi);
        aboutItemView = rootView.findViewById(R.id.iv_mine_about);
    }

    @Override
    protected void initData() {

        favoriteItemView.setOnClickListener(this);
        meiziItemView.setOnClickListener(this);
        aboutItemView.setOnClickListener(this);

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

    @Override
    public void onUserInfo(User user) {
        if(!TextUtils.isEmpty(user.getAvatar())){
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
