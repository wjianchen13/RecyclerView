package com.example.recyclerview.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.chat.bean.Product;
import com.example.recyclerview.chat.flow.FlowLayoutManager;
import com.example.recyclerview.chat.flow.NestedRecyclerView;
import com.example.recyclerview.chat.flow.SpaceItemDecoration;
import com.example.recyclerview.marquee.MarqueeAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatActivity1 extends AppCompatActivity implements View.OnClickListener {

    private NestedRecyclerView rvTest;
    private FlowAdapter mFlowAdapter;
    public List<Product.Classify.Des> des = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat1);
        rvTest = findViewById(R.id.rv_test);
        initData();
        initRecyclerView();

    }

    private void initData() {
        des.addAll(Arrays.asList(new Product.Classify.Des("红色"),
                new Product.Classify.Des("白色"),
                new Product.Classify.Des("蓝色"),
                new Product.Classify.Des("橘黄色"),
                new Product.Classify.Des("格调灰"),
                new Product.Classify.Des("深色"),
                new Product.Classify.Des("咖啡色")));
    }

    public void initRecyclerView(){
        mFlowAdapter = new FlowAdapter(this, des);
        final FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        rvTest.setLayoutManager(flowLayoutManager);
//            rvTest.addItemDecoration(new GridSpacingItemDecoration(2, 20, false));
        rvTest.addItemDecoration(new SpaceItemDecoration(3));
        rvTest.setAdapter(mFlowAdapter);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_test1) {

        } else if(v.getId() == R.id.tv_test2) {

        }
    }
}
