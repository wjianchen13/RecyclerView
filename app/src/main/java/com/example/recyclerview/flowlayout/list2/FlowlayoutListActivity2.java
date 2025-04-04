package com.example.recyclerview.flowlayout.list2;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.flowlayout.list1.FlowlayoutBean;
import com.example.recyclerview.flowlayout.list1.FlowlayoutItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Flowlayout 在RecyclerView的使用
 */
public class FlowlayoutListActivity2 extends AppCompatActivity {

    private RecyclerView rvTest;
    private FlowlayoutAdapter2 adapter;
    private List<String> datas1 = new ArrayList<>();
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowlayout_list_2);
        rvTest = findViewById(R.id.rv_test);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTest.setLayoutManager(layoutManager);
        rvTest.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 20;
                outRect.top = 20;
                outRect.right = 20;
                outRect.bottom = 20;
            }
        });

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
        adapter = new FlowlayoutAdapter2(this, getData());
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
