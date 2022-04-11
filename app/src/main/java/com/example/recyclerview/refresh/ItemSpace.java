package com.example.recyclerview.refresh;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * name: AddSpace
 * desc: 视频列表间距
 * author:
 * date: 2017-07-22 16:00
 * remark:
 */
public class ItemSpace extends RecyclerView.ItemDecoration{

    private int center;
    private int bottom;
    private boolean header;

    public ItemSpace(int center, int bottom, boolean header) {
        this.center = center;
        this.bottom = bottom;
        this.header = header;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int count = parent.getAdapter().getItemCount();
        if(header) {
            if(parent.getChildAdapterPosition(view) > 0 && parent.getChildAdapterPosition(view) != 0) {
                if (parent.getChildAdapterPosition(view) % 2 != 0) { // 左边
                    outRect.right = center / 2;
                    outRect.bottom = bottom;
                } else { // 右侧
                    outRect.left = center / 2;
                    outRect.bottom = bottom;
                }
            }
        } else {
            if (parent.getChildAdapterPosition(view) % 2 == 0) { // 左边
                outRect.right = center / 2;
//            if(parent.getChildAdapterPosition(view) != count - 2)
                outRect.bottom = bottom;
            } else { // 右侧
                outRect.left = center / 2;
//            if(parent.getChildAdapterPosition(view) != count - 1)
                outRect.bottom = bottom;
            }
        }

    }
}