package com.example.recyclerview.flowlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recyclerview.R;
import com.example.recyclerview.flowlayout.list1.FlowlayoutListActivity;
import com.example.recyclerview.flowlayout.list2.FlowlayoutListActivity2;

public class FlowlayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowlayout);
    }

    /**
     * Flowlayout 测试
     * @param v
     */
    public void onTest1(View v) {
        startActivity(new Intent(FlowlayoutActivity.this, FlowlayoutTestActivity.class));
    }

    /**
     * Flowlayout 在RecyclerView的使用
     * @param v
     */
    public void onTest2(View v) {
        startActivity(new Intent(FlowlayoutActivity.this, FlowlayoutListActivity.class));
    }

    /**
     * Flowlayout 在RecyclerView的使用
     * @param v
     */
    public void onTest3(View v) {
        startActivity(new Intent(FlowlayoutActivity.this, FlowlayoutListActivity2.class));
    }

}
