package com.example.recyclerview.cache;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.test.TestAdapter;
import com.example.recyclerview.test.TestAdapter1;
import com.example.recyclerview.test.TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存RecyclerView
 */
public class CacheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);

        RecyclerView rvCache = findViewById(R.id.rv_cache);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvCache.setLayoutManager(manager);

        List<TestBean> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestBean bean = new TestBean();
            bean.setContent("第 " + i + " 个item");
            if(i == 2)
                bean.setItemType(1);
            stringList.add(bean);
        }



        //4.数据适配器
        CacheAdapter adapter = new CacheAdapter(this, stringList);

//        adapter.notifyItemRemoved();
        //设置适配器到recyclerView
        rvCache.setAdapter(adapter);
    }
}
