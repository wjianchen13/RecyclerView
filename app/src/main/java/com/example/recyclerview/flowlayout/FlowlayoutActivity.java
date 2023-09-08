package com.example.recyclerview.flowlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recyclerview.MainActivity;
import com.example.recyclerview.R;

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


}
