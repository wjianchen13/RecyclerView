package com.example.recyclerview.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试（使用BaseRecyclerViewAdapterHelper）
 */
public class TestActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_test1);
        //1.查找控件
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //2.创建布局管理器-线性布局
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置管理器的水平方向，RecyclerView.VERTICAL垂直方向，RecyclerView.HORIZONTAL水平方向
        manager.setOrientation(RecyclerView.VERTICAL);//RecyclerView.HORIZONTAL
        //设置布局布局管理器到recyclerView
        recyclerView.setLayoutManager(manager);

        //3.设置数据
        List<TestBean> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestBean bean = new TestBean();
            bean.setContent("第 " + i + " 个item");
            if(i == 2)
                bean.setItemType(1);
            stringList.add(bean);
        }



        //4.数据适配器
        TestAdapter1 adapter = new TestAdapter1(this, stringList);

//        adapter.notifyItemRemoved();
        //设置适配器到recyclerView
        recyclerView.setAdapter(adapter);
    }
}
