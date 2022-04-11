package com.example.recyclerview.visiblestate;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class StateActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvTest1;
    private TextView tvTest2;
    private List<String> datas = new ArrayList<>();
    private StateAdapter adapter;
    private LinearLayoutManager layoutManager;
    private String s10;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        tvTest1 = (TextView) findViewById(R.id.tv_test1);
        tvTest1.setOnClickListener(this);
        tvTest2 = (TextView) findViewById(R.id.tv_test2);
        tvTest2.setOnClickListener(this);
        initVertical();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tv_test1:
                layoutManager.scrollToPositionWithOffset(0, -200);
                int c = getScrollY();
                int d = 2;
                break;
            case R.id.tv_test2:
                int c2 = getScrollY();
                int d2 = 2;
                break;
        }
    }

    private int getScrollY() {
        View child = layoutManager.getChildAt(0);
        if (child == null) {
            return 0;
        }
        int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
        int top = child.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = 750;
        }
        int height =  -top + firstVisiblePosition * child.getHeight() + headerHeight;
        int b = 2;
        return height;
    }

    public void initVertical(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_vertical);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
        String s1 = "test1";
        datas.add(s1);
        String s2 = "test2";
        datas.add(s2);
        String s3 = "test3";
        datas.add(s3);
        String s4 = "test4";
        datas.add(s4);
        String s5 = "test5";
        datas.add(s5);
        String s6 = "test6";
        datas.add(s6);
        String s7 = "test7";
        datas.add(s7);
        String s8 = "test8";
        datas.add(s8);
        String s9 = "test9";
        datas.add(s9);
        s10 = "test10";
        datas.add(s10);
        String s11 = "test11";
        datas.add(s11);
        String s12 = "test12";
        datas.add(s12);
        String s13 = "test13";
        datas.add(s13);
        String s14 = "test14";
        datas.add(s14);
        String s15 = "test15";
        datas.add(s15);

        adapter = new StateAdapter(datas);
        recyclerView.setAdapter(adapter);
    }
}
