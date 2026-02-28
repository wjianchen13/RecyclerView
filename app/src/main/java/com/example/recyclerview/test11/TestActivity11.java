package com.example.recyclerview.test11;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试删除数据，恢复数据情况
 */
public class TestActivity11 extends AppCompatActivity {

    private List<String> datas = new ArrayList<>();
    private TestAdapter11 adapter;
    private LinearLayoutManager layoutManager;

    private int i = 0;

    private String s10;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test11);
        initData();
        initRecyclerView();
    }

    private void initData() {
        for(int i = 0; i < 20; i ++) {
            String str = "test" + i;
            datas.add(str);
        }
    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerview_vertical);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(null);

        // 创建Adapter，并指定数据集
        adapter = new TestAdapter11(datas);
        // 设置Adapter
        recyclerView.setAdapter(adapter);

    }

    /**
     * 移除数据
     * @param v
     */
    public void onTest1(View v) {

    }

    /**
     * 添加数据
     * @param v
     */
    public void onTest2(View v) {

    }


}
