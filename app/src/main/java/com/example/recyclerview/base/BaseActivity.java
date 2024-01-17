package com.example.recyclerview.base;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private TextView tvTest1;
    private TextView tvTest2;
    private List<String> datas = new ArrayList<>();
    private MyAdapter adapter;
    private List<String> datas1 = new ArrayList<>();

    private LinearLayoutManager layoutManager;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        tvTest1 = (TextView) findViewById(R.id.tv_test1);
        setData();
        tvTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.set(9, "sdkfjlasdjkdfsakj");
//                s10 = "nihao";
                adapter.notifyDataSetChanged();
            }
        });
        tvTest2 = (TextView) findViewById(R.id.tv_test2);
        tvTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                datas.clear();
//                datas.addAll(poiItemList);
//                adapter.notifyItemRangeChanged(0, poiItemList.size());
//                String add = "add";
//                datas.add(add);
//                adapter.notifyItemInserted(datas.size() - 1);
                List<String> datas2 = new ArrayList<>();
                String add1 = "add" + i;
                i ++;
                datas2.add(add1);
//                String add2 = "add2";
//                datas2.add(add2);
//                String add3 = "add3";
//                datas2.add(add3);
//                String add4 = "add4";
//                datas2.add(add4);
//                String add5 = "add5";
//                datas2.add(add5);
//                String add6 = "add6";
//                datas2.add(add6);
//                String add7 = "add7";
//                datas2.add(add7);
//                String add8 = "add8";
//                datas2.add(add8);
//                String add9 = "add9";
//                datas2.add(add9);
//                String add10 = "add10";
//                datas2.add(add10);
//                int index = adapter.getmDataset().size() - 1;
//                adapter.getmDataset().remove(adapter.getmDataset().size() - 1);
//                if(adapter != null) {
//                    adapter.notifyItemRemoved(adapter.getmDataset().size() - 1);
//                }

                datas.addAll(datas2);
//                adapter.notifyItemRangeChanged(0, 1);
                adapter.notifyItemChanged(datas.size() - 1);
//                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(datas.size() - 1);
//                adapter.notifyItemRangeChanged(0, 1);
//                datas.addAll(0, datas2);
//                adapter.notifyItemRangeChanged(0, datas2.size() * 2);
//                datas.remove(0);
//                adapter.notifyItemRemoved(0);
//                datas.addAll(0, datas2);
//                adapter.notifyItemInserted(0);

//                adapter.notifyItemRangeInserted(0, 5);
//                adapter.notifyDataSetChanged();
//                recyclerView.scrollToPosition(0);
//                adapter.notifyItemRangeChanged(0, 6);

            }
        });

        initVertical();
    }

    private String s10;

    private void setData() {
        String s1 = "add1";
        datas1.add(s1);
        String s2 = "add2";
        datas1.add(s2);
        String s3 = "add3";
        datas1.add(s3);
        String s4 = "add4";
        datas1.add(s4);
        String s5 = "add5";
        datas1.add(s5);
        String s6 = "add6";
        datas1.add(s6);
        String s7 = "add7";
        datas1.add(s7);
        String s8 = "add8";
        datas1.add(s8);
        String s9 = "add9";
        datas1.add(s9);
        String s10 = "add10";
        datas1.add(s10);
    }

    private RecyclerView recyclerView;

    public void initVertical(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_vertical);

        // 创建一个线性布局管理器
        layoutManager = new LinearLayoutManager(this);
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        layoutManager.setStackFromEnd(true);
//        layoutManager.setReverseLayout(true);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        String s1 = "test1";
        datas.add(s1);
        String s2 = "test2";
        datas.add(s2);
//        String s3 = "test3";
//        datas.add(s3);
//
//        String s4 = "test4";
//        datas.add(s4);
//
//        String s5 = "test5";
//        datas.add(s5);
//
//        String s6 = "test6";
//        datas.add(s6);
//        String s7 = "test7";
//        datas.add(s7);
//        String s8 = "test8";
//        datas.add(s8);
//        String s9 = "test9";
//        datas.add(s9);
//        s10 = "test10";
//        datas.add(s10);
//        s10 = "test10";
//        datas.add(s10);
//        s10 = "test10";
//        datas.add(s10);
//        s10 = "test10";
//        datas.add(s10);
//        s10 = "test10";
//        datas.add(s10);
//        recyclerView.setItemAnimator(null);

        // 创建Adapter，并指定数据集
        adapter = new MyAdapter(datas);
        // 设置Adapter
        recyclerView.setAdapter(adapter);

    }
}
