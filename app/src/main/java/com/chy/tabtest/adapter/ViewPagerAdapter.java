package com.chy.tabtest.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<? extends Fragment> fragments;
    private FragmentManager fm;

    /**
     * 重构
     *
     * @param fm        fragment页面管理者
     * @param fragments 需要切换页面的数据源
     */
    public ViewPagerAdapter(FragmentManager fm, List<? extends Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //显示fragment视图
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // 将实例化的fragment进行显示即可。
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        //beginTransaction fragment事件处理器
        fm.beginTransaction().show(fragment).commit();
        return fragment;
    }
}
