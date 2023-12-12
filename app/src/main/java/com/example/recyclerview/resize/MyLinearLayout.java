package com.example.recyclerview.resize;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.recyclerview.utils.Utils;

public class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Utils.log("MyLinearLayout onSizeChanged w: " + w + "  h: " + h + "  oldw: " + oldw + "  oldh: " + oldh);
    }
}
