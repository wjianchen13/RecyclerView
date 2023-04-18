package com.example.recyclerview.third.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recyclerview.R;

/**
 * CoordinatorLayout与Behavior中的依赖View关系
 */
public class DependencyViewActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTv_nested_scroll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependency_view);
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
//            ViewCompat.setNestedScrollingEnabled(mTv_nested_scroll, true);
//            ViewCompat.startNestedScroll(mTv_nested_scroll, ViewCompat.SCROLL_AXIS_HORIZONTAL);
//        }
        mTv_nested_scroll = findViewById(R.id.tv_nested_scroll);
        mTv_nested_scroll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nested_scroll:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mTv_nested_scroll.setNestedScrollingEnabled(true);
                    mTv_nested_scroll.startNestedScroll(View.SCROLL_AXIS_HORIZONTAL);
                }
                Log.e("t","tv_nested_scroll");
                break;
        }
    }

}
