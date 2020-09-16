package com.xing.main.fragment;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xing.commonbase.base.BaseFragment;
import com.xing.commonbase.base.BaseMVPFragment;
import com.xing.main.R;
import com.xing.main.bean.ProjectPageItem;
import com.xing.main.contract.ProjectContract;
import com.xing.main.fragment.handle.DoneFragment;
import com.xing.main.fragment.handle.ProcessFinishFragment;
import com.xing.main.fragment.handle.ProcessInsFragment;
import com.xing.main.fragment.handle.TodoFragment;
import com.xing.main.presenter.ProjectPresenter;

import java.util.ArrayList;
import java.util.List;


public class HandleFragment extends BaseFragment {

    private View mFakeStatusBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    List<String> mTitle;
    List<Fragment> mFragment;

    public HandleFragment() {
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView(View rootView) {
        mFakeStatusBar = rootView.findViewById(R.id.fake_status_bar);
        mTabLayout = rootView.findViewById(R.id.tl_project);
        mViewPager = rootView.findViewById(R.id.vp_project_page);
        mFakeStatusBar.post(new Runnable() {
            @Override
            public void run() {
                int height = mFakeStatusBar.getHeight();
                Log.e("debufgdbug", "run: " + height);
            }
        });
    }

    @Override
    protected void initData() {
        mTitle = new ArrayList<>();
        mTitle.add("我的待办");
        mTitle.add("我的已办");
        mTitle.add("流转中流程");
        mTitle.add("已结束流程");

        mFragment = new ArrayList<>();
        mFragment.add(new TodoFragment());
        mFragment.add(new DoneFragment());
        mFragment.add(new ProcessInsFragment());
        mFragment.add(new ProcessFinishFragment());

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        mViewPager.setOffscreenPageLimit(4);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setStatusBarColor(int color) {
        mFakeStatusBar.setBackgroundColor(color);
    }

}
