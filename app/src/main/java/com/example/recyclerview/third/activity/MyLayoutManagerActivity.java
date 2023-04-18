package com.example.recyclerview.third.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.third.adapter.LinearVerticalAdapter;
import com.example.recyclerview.third.base.MySelfLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义LayoutManager
 */
public class MyLayoutManagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //3.设置数据
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("第 " + i + " 个item");
        }

        recyclerView.setLayoutManager(new MySelfLayoutManager());
        recyclerView.setAdapter(new LinearVerticalAdapter(this, stringList));
    }

}
