package com.example.recyclerview.header;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.recyclerview.R;

import java.util.ArrayList;

public class HeaderActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_header);
//        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager = new GridLayoutManager(this, 2);
//        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(new AddDecoration(this, true));

        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addDatas(generateData());
        setHeader(mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(int position, String data) {
                Toast.makeText(HeaderActivity.this, position + "," + data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(this).inflate(R.layout.item_header_header, view, false);
        mAdapter.setHeaderView(header);
    }

    private ArrayList<String> generateData() {
        ArrayList<String> data = new ArrayList<String>() {
            {
                for(int i=0;i<50;i++) add("数据" + i);
            }
        };
        return data;
    }

}
