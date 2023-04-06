package com.example.recyclerview.marquee;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 修复TextView 跑马灯不滚动的问题
 * https://blog.csdn.net/onlySound/article/details/119897857
 * 
 * RecyclerView item 中 textView 跑马灯效果无效的
 * https://blog.csdn.net/tiantaiaiqing/article/details/79200299?spm=1001.2101.3001.6650.2&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-2-79200299-blog-80576528.235%5Ev28%5Epc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-2-79200299-blog-80576528.235%5Ev28%5Epc_relevant_default&utm_relevant_index=3
 */
public class MarqueeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTest1;
    private TextView tvTest2;
    private RecyclerView recyclerView;
    private List<String> datas = new ArrayList<>();
    private MarqueeAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);
        tvTest1 = (TextView) findViewById(R.id.tv_test1);
        tvTest2 = (TextView) findViewById(R.id.tv_test2);
        tvTest1.setOnClickListener(this);
        tvTest2.setOnClickListener(this);
        initData();
        initRecyclerView();
    }

    private void initData() {
        String s1 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s1);
        String s2 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s2);
        String s3 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s3);
        String s4 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s4);
        String s5 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s5);
        String s6 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s6);
        String s7 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s7);
        String s8 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s8);
        String s9 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s9);
        String s10 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s10);
        String s11 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s11);
        String s12 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s12);
        String s13 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s13);
        String s14 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s14);
        String s15 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s15);

        String s16 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s16);
        String s17 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s17);
        String s18 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s18);
        String s19 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s19);
        String s20 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s20);
        String s21 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s21);
        String s22 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s22);
        String s23 = "属性11111111111111111的点点滴滴多多多多多多多多多多多多多多多多多多的点点滴滴多多多多多多多多多1";
        datas.add(s23);
    }

    public void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_vertical);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MarqueeAdapter(datas);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_test1) {

        } else if(v.getId() == R.id.tv_test2) {

        }
    }
}
