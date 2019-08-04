package com.chy.tabtest.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.chy.tabtest.R;
import com.chy.tabtest.adapter.ViewPagerAdapter;
import com.chy.tabtest.fragment.FragmentTab1;
import com.chy.tabtest.fragment.FragmentTab2;
import com.chy.tabtest.fragment.FragmentTab3;
import com.chy.tabtest.fragment.FragmentTab4;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private TextView tvTitle;
    private ImageView imgSearch;
    private ImageView imgAdd;
    private ViewPager vpContent;
    private TabLayout tayTab;

    //tab数据源
    private int[] tabIconsNor = new int[]{R.mipmap.ic_tab1_nor, R.mipmap.ic_tab2_nor, R.mipmap.ic_tab3_nor, R.mipmap.ic_tab4_nor};
    private int[] tabIconsSel = new int[]{R.mipmap.ic_tab1_sel, R.mipmap.ic_tab2_sel, R.mipmap.ic_tab3_sel, R.mipmap.ic_tab4_sel};
    private String[] tabTexts = new String[]{"微信", "通讯录", "发现", "我的"};

    //声明Viewpager用到的fragment页面
    private FragmentTab1 tab1;
    private FragmentTab2 tab2;
    private FragmentTab3 tab3;
    private FragmentTab4 tab4;

    private List<Fragment> fragments = new ArrayList<>();
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        initView();
        initEvent();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {

        //初始化Viewpager数据
        tab1 = new FragmentTab1();
        tab2 = new FragmentTab2();
        tab3 = new FragmentTab3();
        tab4 = new FragmentTab4();
        fragments.add(tab1);
        fragments.add(tab2);
        fragments.add(tab3);
        fragments.add(tab4);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpContent.setAdapter(adapter);

        //绑定tab与page
        tayTab.setupWithViewPager(vpContent);

        //添加每一个tab的图片和文字
        for (int i = 0; i < tayTab.getTabCount(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
            ImageView tabIcon = view.findViewById(R.id.img_tab);
            TextView tabText = view.findViewById(R.id.tv_tab);
            tabIcon.setImageResource(tabIconsNor[i]);
            tabText.setText(tabTexts[i]);
            tayTab.getTabAt(i).setCustomView(view);
        }
        //设置默认tab的图片和文字颜色
        //获取第0个索引位置的tab视图的图片和文字颜色为sel
        tabSelect(3);
        //设置默认选中的Viewpager
        vpContent.setCurrentItem(3);
    }

    /**
     * 设置选中的viewpager样式
     *
     * @param tabPosition 所点击的tab的位置
     */
    private void tabSelect(int tabPosition) {
        resetTab();
        LinearLayout view = (LinearLayout) tayTab.getTabAt(tabPosition).getCustomView();
        ImageView tabIcon = (ImageView) view.getChildAt(0);
        TextView tabText = (TextView) view.getChildAt(1);

        tabIcon.setImageResource(tabIconsSel[tabPosition]);
        tabText.setTextColor(Color.parseColor("#1296db"));
        tvTitle.setText(tabTexts[tabPosition]);
    }

    /**
     * 重置所有tab的为未选中状态（全部变为灰色）
     */
    private void resetTab() {
        for (int i = 0; i < tayTab.getTabCount(); i++) {

            LinearLayout view = (LinearLayout) tayTab.getTabAt(i).getCustomView();
            //==ImageView tabIcon = view.findViewById(R.id.img_tab);
            ImageView tabIcon = (ImageView) view.getChildAt(0);
            TextView tabText = (TextView) view.getChildAt(1);

            tabIcon.setImageResource(tabIconsNor[i]);
            tabText.setTextColor(Color.parseColor("#707070"));
        }
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        //搜索控件点击事件
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //添加控件点击事件
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //ViewPager滑动切换事件
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            //ViewPager滑动完成之后执行
            @Override
            public void onPageSelected(int position) {
                //设置选中的viewpager样式
                tabSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化视图控件
     */
    private void initView() {
        tvTitle = findViewById(R.id.tv_title);
        imgSearch = findViewById(R.id.img_search);
        imgAdd = findViewById(R.id.img_add);
        imgAdd = findViewById(R.id.img_add);
        vpContent = findViewById(R.id.vp_content);
        tayTab = findViewById(R.id.tay_tab);
    }
}
