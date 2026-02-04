package com.example.recyclerview.base;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private List<String> datas = new ArrayList<>();
    private MyAdapter adapter;
    private LinearLayoutManager layoutManager;

    private int i = 0;

    private String s10;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initVertical();
    }

    public void initVertical(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_vertical);

        // 创建一个线性布局管理器
        layoutManager = new LinearLayoutManager(this);
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        layoutManager.setStackFromEnd(true);
//        layoutManager.setReverseLayout(true);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        String s1 = "test1";
        datas.add(s1);
        String s2 = "test2";
        datas.add(s2);
        String s3 = "test3";
        datas.add(s3);

        String s4 = "test4";
        datas.add(s4);

        String s5 = "test5";
        datas.add(s5);

        String s6 = "test6";
        datas.add(s6);
        String s7 = "test7";
        datas.add(s7);
        String s8 = "test8";
        datas.add(s8);
        String s9 = "test9";
        datas.add(s9);
        s10 = "test10";
        datas.add(s10);
        s10 = "test10";
        datas.add(s10);
        s10 = "test10";
        datas.add(s10);
        s10 = "test10";
        datas.add(s10);
        s10 = "test10";
        datas.add(s10);
//        recyclerView.setItemAnimator(null);

        // 创建Adapter，并指定数据集
        adapter = new MyAdapter(datas);
        // 设置Adapter
        recyclerView.setAdapter(adapter);

    }

    /**
     * 添加数据
     * @param v
     */
    public void onTest1(View v) {
        List<String> datas2 = new ArrayList<>();
        String add1 = "add" + i;
        i ++;
        datas2.add(add1);
        datas.addAll(datas2);
        adapter.notifyItemChanged(datas.size() - 1);
        recyclerView.scrollToPosition(datas.size() - 1);
    }

    /**
     * 修改数据
     * @param v
     */
    public void onTest2(View v) {
        datas.set(9, "sdkfjlasdjkdfsakj");
//                s10 = "nihao";
        adapter.notifyDataSetChanged();
    }


}
