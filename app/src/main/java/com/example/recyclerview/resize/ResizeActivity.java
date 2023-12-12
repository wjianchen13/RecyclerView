package com.example.recyclerview.resize;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ResizeActivity extends AppCompatActivity {

    private List<String> datas = new ArrayList<>();
    private ResizeAdapter adapter;
    private LinearLayoutManager layoutManager;
    private LinearLayout llytList;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resize);
        llytList = findViewById(R.id.llyt_list);
        initVertical();
    }

    public void onTest1(View v) {
        RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams)llytList.getLayoutParams();
        p.height = 500;
        llytList.setLayoutParams(p);
    }


    public void onTest2(View v) {
        RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams)llytList.getLayoutParams();
        p.height = 700;
        llytList.setLayoutParams(p);
    }

    /**
     * 获取屏幕坐标
     * @param v
     */
    public void onTest3(View v) {
        Utils.log("llytList x: " + getLocationX(llytList) + "  y: " + getLocationY(llytList));
        Utils.log("recyclerView x: " + getLocationX(recyclerView) + "  y: " + getLocationY(recyclerView));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Utils.log("hasFocus: " + hasFocus + "  llytList width: " + llytList.getWidth() + "  height: " + llytList.getHeight());
        Utils.log("hasFocus: " + hasFocus + "  recyclerView width: " + recyclerView.getWidth() + "  height: " + recyclerView.getHeight());
        Utils.log("hasFocus: " + hasFocus + "  llytList x: " + getLocationX(llytList) + "  y: " + getLocationY(llytList));
        Utils.log("hasFocus: " + hasFocus + "  recyclerView x: " + getLocationX(recyclerView) + "  y: " + getLocationY(recyclerView));
    }

    public void initVertical(){
        recyclerView = findViewById(R.id.recyclerview_vertical);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        for(int i = 0; i < 20; i ++) {
            String s = "test" + i;
            datas.add(s);
        }
        adapter = new ResizeAdapter(datas);
        recyclerView.setAdapter(adapter);
    }

    private int getLocationX(View v) {
        if(v != null) {
            int[] location = new int[2];
            v.getLocationOnScreen(location);
            return location[0]; // 获取当前位置的横坐标
        }
        return 0;
    }

    private int getLocationY(View v) {
        if(v != null) {
            int[] location = new int[2];
            v.getLocationOnScreen(location);
            return location[1]; // 获取当前位置的纵坐标
        }
        return 0;
    }
}
