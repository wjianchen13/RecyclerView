package com.example.recyclerview.flowlayout;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Flowlayout 在RecyclerView的使用
 */
public class FlowlayoutListActivity extends AppCompatActivity {

    private RecyclerView rvTest;
    private FlowlayoutAdapter adapter;
    private List<String> datas1 = new ArrayList<>();
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowlayout_list);
        rvTest = findViewById(R.id.rv_test);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTest.setLayoutManager(layoutManager);

    }

    /**
     * Flowlayout 测试
     * @param v
     */
    public void onTest1(View v) {
//        List<FlowlayoutBean> beans = new ArrayList<>();
//        for(int i = 0; i < 10; i ++) {
//            FlowlayoutBean bean = new FlowlayoutBean("" + i);
//            beans.add(bean);
//        }
        adapter = new FlowlayoutAdapter(this, getData());
        rvTest.setAdapter(adapter);
    }

    /**
     * Flowlayout 在RecyclerView的使用
     * @param v
     */
    public void onTest2(View v) {

    }

    private List<FlowlayoutBean> getData() {
        List<FlowlayoutBean> beans = new ArrayList<>();
        for(int i = 0; i < 15; i ++) {
            FlowlayoutBean bean = new FlowlayoutBean();
            List<FlowlayoutItemBean> items = new ArrayList<>();
            for(int j = 0; j < i; j ++) {
                FlowlayoutItemBean item = new FlowlayoutItemBean(j + 1, "测试" + (j + 1));
                items.add(item);
            }
            bean.setList(items);
            beans.add(bean);
        }
        return beans;
    }


}
