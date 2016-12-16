package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {
    private SlidingMenu mSlidingMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.item);
        //设置旁边的菜单页面
        mSlidingMenu = getSlidingMenu();
        mSlidingMenu.setMode(SlidingMenu.RIGHT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setBehindOffsetRes(R.dimen.sm_width);

    }

    public void show() {
        Toast.makeText(MainActivity.this, "你好啊", Toast.LENGTH_SHORT).show();
    }
}
