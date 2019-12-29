package com.example.habit;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ShowTabbarActivity extends FragmentActivity implements View.OnClickListener
{

    private FrameLayout flayout;

    private RelativeLayout ruiku_layout;
    private ImageView ruiku_iv;
    private TextView ruiku_tv;

    private RelativeLayout yuyue_layout;
    private ImageView yuyue_iv;
    private TextView yuyue_tv;

    private RelativeLayout xiaoxi_layout;
    private ImageView xiaoxi_iv;
    private TextView xiaoxi_tv;


    private int whirt = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int blue =0xFF0AB2FB;

    private Today testFragment1;
    private Total testFragment2;
    private Today testFragment3;
    Fragment nowFragment = new Today("今日习惯");

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tabbar);           // 绑定页面

        initView();

        fragmentManager = getSupportFragmentManager();          // 开启一个事物

        // 默认加载某一个tabbarItem（第一个按钮）
        //Today testFragment1 = new Today("今日习惯");

        // 启动Activity时使第一个按钮的图标为选中状态（投机取巧）
//        ruiku_iv.setImageResource(R.drawable.btn_ku_s);
        changeFragment(nowFragment,new Today("今日习惯"));
        setChioceItem(0);
    }

    /***
     * 初始化控件
     */
    public void initView()
    {
        ruiku_layout = (RelativeLayout) findViewById(R.id.ruiku_layout);
        ruiku_iv = (ImageView) findViewById(R.id.ruiku_iv);
        ruiku_tv = (TextView) findViewById(R.id.ruiku_tv);

        yuyue_layout = (RelativeLayout) findViewById(R.id.yuyue_layout);
        yuyue_iv = (ImageView) findViewById(R.id.yuyue_iv);
        yuyue_tv = (TextView) findViewById(R.id.yuyue_tv);

        xiaoxi_layout = (RelativeLayout) findViewById(R.id.xiaoxi_layout);
        xiaoxi_iv = (ImageView) findViewById(R.id.xiaoxi_iv);
        xiaoxi_tv = (TextView) findViewById(R.id.xiaoxi_tv);


        ruiku_layout.setOnClickListener(this);
        yuyue_layout.setOnClickListener(this);
        xiaoxi_layout.setOnClickListener(this);

        clearChioce();          // 清空选择
    }

    /***
     * 重写点击事件 -- 根据 implements View.OnClickListener 来的
     * @param v 当前视图
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ruiku_layout:
                setChioceItem(0);
                break;
            case R.id.yuyue_layout:
                setChioceItem(1);
                break;
            case R.id.xiaoxi_layout:
                setChioceItem(2);
                break;
            default:
                break;
        }
    }

    private void changeFragment(Fragment fromFragment, Fragment toFragment) {

        if (nowFragment != toFragment) {
            nowFragment = toFragment;
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (toFragment.isAdded() == false) {
            Log.i("showIt","进来");
            ft.hide(fromFragment).add(R.id.home_activity_frag_container, toFragment).commit();
        } else {
            Log.i("showIt","NO进来");
            ft.hide(fromFragment).show(toFragment).commit();
        }

    }
    /***
     * 定义选中后的控制器
     * @param index index
     */
    public void setChioceItem(int index)
    {
        clearChioce();      // 既然是点击选择，那么在点的时候就应该清除一下上一个索引

        // 重置选项+隐藏所有的Fragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        hideFragments(transaction);

        switch (index) {
            case 0:
                //ruiku_iv.setImageResource(R.drawable.tabbar_ruiku_selector);
                ruiku_tv.setTextColor(blue);
                changeFragment(nowFragment,new Today("今日习惯"));
                break;

            case 1:
                //yuyue_iv.setImageResource(R.drawable.tabbar_yuyue_selector);
                yuyue_tv.setTextColor(blue);
                changeFragment(nowFragment,new Total("习惯管理"));
                break;

            case 2:
                //xiaoxi_iv.setImageResource(R.drawable.tabbar_xiaoxi_selector);
                xiaoxi_tv.setTextColor(blue);
                changeFragment(nowFragment,new Today("今日习惯"));
                break;
        }
        transaction.commit();
    }

    /**
     * 定义一个重置所有选项的方法
     */
    public void clearChioce()
    {
        //ruiku_iv.setImageResource(R.drawable.btn_ku_n);
        ruiku_layout.setBackgroundColor(whirt);
        ruiku_tv.setTextColor(gray);

        //yuyue_iv.setImageResource(R.drawable.btn_book_n);
        yuyue_layout.setBackgroundColor(whirt);
        yuyue_tv.setTextColor(gray);

        //xiaoxi_iv.setImageResource(R.drawable.btn_ms_n);
        xiaoxi_layout.setBackgroundColor(whirt);
        xiaoxi_tv.setTextColor(gray);
    }

    /**
     * 将所有的Fragment都设置为隐藏状态
     * @param transaction 事物
     */
    private void hideFragments(FragmentTransaction transaction)
    {
        if (testFragment1 != null) {
            transaction.hide(testFragment1);
        }
        if (testFragment2 != null) {
            transaction.hide(testFragment2);
        }
        if (testFragment3 != null) {
            transaction.hide(testFragment3);
        }
    }
}


