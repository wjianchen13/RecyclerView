package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.recyclerview.add.AddActivity;
import com.example.recyclerview.base.BaseActivity;
import com.example.recyclerview.cache.CacheActivity;
import com.example.recyclerview.chat.ChatActivity;
import com.example.recyclerview.chat.ChatActivity1;
import com.example.recyclerview.fall.FallActivity;
import com.example.recyclerview.flowlayout.FlowlayoutActivity;
import com.example.recyclerview.header.HeaderActivity;
import com.example.recyclerview.marquee.MarqueeActivity;
import com.example.recyclerview.movie.MovieActivity;
import com.example.recyclerview.refresh.RefreshActivity;
import com.example.recyclerview.resize.ResizeActivity;
import com.example.recyclerview.scroll.ScrollActivity;
import com.example.recyclerview.span.SpanActivity;
import com.example.recyclerview.test.TestActivity;
import com.example.recyclerview.test.TestActivity1;
import com.example.recyclerview.third.activity.ThirdActivity;
import com.example.recyclerview.visiblestate.StateActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 基础使用
     * @param
     * @return
     */
    public void onBase(View v) {
        Intent it1 = new Intent();
        it1.setClass(MainActivity.this, BaseActivity.class);
        startActivity(it1);
    }

    /**
     * 富文本
     * @param
     * @return
     */
    public void onSpan(View v) {
        Intent it1 = new Intent();
        it1.setClass(MainActivity.this, SpanActivity.class);
        startActivity(it1);
    }

    /**
     * 添加头部
     * @param
     * @return
     */
    public void onHeader(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, HeaderActivity.class);
        startActivity(it);
    }

    /**
     * 显示状态
     * @param
     * @return
     */
    public void onState(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, StateActivity.class);
        startActivity(it);
    }

    /**
     * 滑动到底部自动更新
     * @param
     * @return
     */
    public void onFresh(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, RefreshActivity.class);
        startActivity(it);
    }

    /**
     * 添加头部底部
     * @param
     * @return
     */
    public void onAdd(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, AddActivity.class);
        startActivity(it);
    }

    /**
     * 添加头部底部
     * @param
     * @return
     */
    public void onFall(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, FallActivity.class);
        startActivity(it);
    }

    /**
     * 添加头部底部
     * @param
     * @return
     */
    public void onProjectFall(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, FallActivity.class);
        startActivity(it);
    }

    /**
     * TextView paomad
     * @param
     * @return
     */
    public void onMarquee(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, MarqueeActivity.class);
        startActivity(it);
    }

    /**
     * 直播间聊天室
     * @param
     * @return
     */
    public void onChat(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, ChatActivity.class);
        startActivity(it);
    }


    /**
     * 直播间聊天室
     * @param
     * @return
     */
    public void onChat1(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, ChatActivity1.class);
        startActivity(it);
    }

    /**
     * 第三方例子
     * @param
     * @return
     */
    public void onThird(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, ThirdActivity.class);
        startActivity(it);
    }

    /**
     * 测试（RecyclerView Adapter原始写法）
     * 
     * @param
     * @return
     */
    public void onTest(View v) {
        startActivity(new Intent(MainActivity.this, TestActivity.class));
    }

    /**
     * 测试（使用BaseRecyclerViewAdapterHelper）
     *
     * @param
     * @return
     */
    public void onTest1(View v) {
        startActivity(new Intent(MainActivity.this, TestActivity1.class));
    }

    /**
     * 项目缓存测试
     *
     * @param
     * @return
     */
    public void onCache(View v) {
        startActivity(new Intent(MainActivity.this, CacheActivity.class));
    }

    /**
     * 流式布局测试
     *
     * @param
     * @return
     */
    public void onFlowlayout(View v) {
        startActivity(new Intent(MainActivity.this, FlowlayoutActivity.class));
    }

    /**
     * 电影座位
     *
     * @param
     * @return
     */
    public void onTest2(View v) {
        startActivity(new Intent(MainActivity.this, MovieActivity.class));
    }

    /**
     * 改变RecyclerView的高度
     *
     * @param
     * @return
     */
    public void onTest3(View v) {
        startActivity(new Intent(MainActivity.this, ResizeActivity.class));
    }

    /**
     * RecyclerView 滚动到指定位置
     *
     * @param
     * @return
     */
    public void onTest4(View v) {
        startActivity(new Intent(MainActivity.this, ScrollActivity.class));
    }

}



