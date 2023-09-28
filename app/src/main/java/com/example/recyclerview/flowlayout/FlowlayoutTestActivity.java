package com.example.recyclerview.flowlayout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recyclerview.R;

/**
 * Flowlayout 测试
 */
public class FlowlayoutTestActivity extends AppCompatActivity {

    private TextView tvTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowlayout_test);
        tvTest1 = findViewById(R.id.tv_test1);
    }

    /**
     * Flowlayout 测试
     * @param v
     */
    public void onTest1(View v) {
        ViewGroup.LayoutParams params = tvTest1.getLayoutParams();
        params.width = 480;
        tvTest1.setLayoutParams(params);
    }

    /**
     * Flowlayout 在RecyclerView的使用
     * @param v
     */
    public void onTest2(View v) {

    }


}
