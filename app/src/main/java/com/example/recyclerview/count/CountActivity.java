package com.example.recyclerview.count;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class CountActivity extends AppCompatActivity {

    private CountAdapter adapter;
    private List<String> datas = new ArrayList<>();
    private RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setData();
        initVertical();
    }

    private void setData() {
        for(int i = 0; i < 50; i ++) {
            datas.add("add" + i);
        }
    }

    public void initVertical(){
        recyclerView = findViewById(R.id.recyclerview_vertical);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 1 ? 3 : 1;
            }
        });
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CountAdapter(datas);
        recyclerView.setAdapter(adapter);
    }
}
