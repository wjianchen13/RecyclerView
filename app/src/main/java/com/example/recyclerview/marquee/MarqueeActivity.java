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
        String s1 = "test1123456789123456789123456789";
        datas.add(s1);
        String s2 = "test1123456789123456789123456789";
        datas.add(s2);
        String s3 = "test1123456789123456789123456789";
        datas.add(s3);
        String s4 = "test1123456789123456789123456789";
        datas.add(s4);
        String s5 = "test1123456789123456789123456789";
        datas.add(s5);
        String s6 = "test1123456789123456789123456789";
        datas.add(s6);
        String s7 = "test1123456789123456789123456789";
        datas.add(s7);
        String s8 = "test1123456789123456789123456789";
        datas.add(s8);
        String s9 = "test1123456789123456789123456789";
        datas.add(s9);
        String s10 = "test1123456789123456789123456789";
        datas.add(s10);
        String s11 = "test1123456789123456789123456789";
        datas.add(s11);
        String s12 = "test1123456789123456789123456789";
        datas.add(s12);
        String s13 = "test1123456789123456789123456789";
        datas.add(s13);
        String s14 = "test1123456789123456789123456789";
        datas.add(s14);
        String s15 = "test1123456789123456789123456789";
        datas.add(s15);
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
