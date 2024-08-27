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

    private TextView tvTest11;
    private TextView tvTest21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowlayout_test);
        tvTest11 = findViewById(R.id.tv_test11);
        tvTest21 = findViewById(R.id.tv_test21);
    }

    /**
     * Flowlayout 测试
     * @param v
     */
    public void onTest1(View v) {
        ViewGroup.LayoutParams params = tvTest11.getLayoutParams();
        params.width = 480;
        tvTest11.setLayoutParams(params);
    }

    /**
     * Flowlayout 在RecyclerView的使用
     * @param v
     */
    public void onTest2(View v) {
//        ViewGroup.LayoutParams params = tvTest21.getLayoutParams();
//        params.width = 480;
//        tvTest21.setLayoutParams(params);
        tvTest21.setText("Flowlayout 在RecyclerView的使用");
    }


    /**
     * Flowlayout 在RecyclerView的使用
     * @param v
     */
    public void onTest3(View v) {
        tvTest21.setText("abc");
    }


}
