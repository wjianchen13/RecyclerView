package com.example.recyclerview.scroll;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.test.TestBean;
import com.example.recyclerview.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 滚动到某个位置
 *
 * RecyclerView - 实现目标Item滚动到指定位置（SmoothScroller）
 * https://blog.csdn.net/qq_20613731/article/details/112854243
 */
public class ScrollActivity extends AppCompatActivity {

    private ScrollAdapter mAdapter1;
    private int index = 0;
    private RecyclerView rvScroll1;
    private List<TestBean> mData1;
    private ScrollLayoutManager manager1;

    private ScrollAdapter mAdapter2;
    private RecyclerView rvScroll2;
    private List<TestBean> mData2;
    private ScrollLayoutManager manager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        initRecyclerView1();
        initRecyclerView2();
    }

    private void initRecyclerView1() {
        rvScroll1 = findViewById(R.id.rv_scroll1);

        manager1 = new ScrollLayoutManager(this);
        manager1.setOrientation(RecyclerView.VERTICAL);

        rvScroll1.setLayoutManager(manager1);
        rvScroll1.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = 20;
                outRect.top = 10;
                outRect.right = 20;
                outRect.bottom = 10;
            }
        });
        mData1 = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            TestBean bean = new TestBean();
            bean.setContent("第 " + i + " 个item1");
            mData1.add(bean);
        }

        mAdapter1 = new ScrollAdapter(this, mData1, rvScroll1);
        manager1.setDinoListener(mAdapter1);
//        adapter.notifyItemRemoved();
        //设置适配器到recyclerView
        rvScroll1.setAdapter(mAdapter1);
    }

    private void initRecyclerView2() {
        rvScroll2 = findViewById(R.id.rv_scroll2);

        manager2 = new ScrollLayoutManager(this);
        manager2.setOrientation(RecyclerView.VERTICAL);

        rvScroll2.setLayoutManager(manager2);
        rvScroll2.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = 20;
                outRect.top = 10;
                outRect.right = 20;
                outRect.bottom = 10;
            }
        });
        mData2 = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            TestBean bean = new TestBean();
            bean.setContent("第 " + i  + " 个item2");
            mData2.add(bean);
        }

        mAdapter2 = new ScrollAdapter(this, mData2, rvScroll2);
        manager2.setDinoListener(mAdapter2);
//        adapter.notifyItemRemoved();
        //设置适配器到recyclerView
        rvScroll2.setAdapter(mAdapter2);
    }

    /**
     * 滚动到具体位置
     * 滚动到具体位置是有问题的，当滚动的位置已经显示，这时滚动最后实际显示出下一个item
     * @param v
     */
    public void onTest1(View v) {
//        manager1.scrollToPosition(0);
//        manager1.scrollToPosition(13);
//        rvScroll1.scrollToPosition(13);
//        rvScroll2.rese
        rvScroll2.postDelayed(new Runnable() {
            @Override
            public void run() {
                rvScroll1.scrollToPosition(15);
            }
        }, 1000);
    }

    public void onTest2(View v) {
        rvScroll1.smoothScrollToPosition(13);
    }

    public void onTest3(View v) {
        mData1.remove(0);
        mAdapter1.notifyItemRemoved(0);
    }

    public void onTest4(View v) {
        mData1.remove(2);
        mData1.remove(1);
        mData1.remove(0);
        mAdapter1.notifyItemRangeRemoved(0, 3);
    }

    public void onTest5(View v) {
        TestBean bean = new TestBean();
        bean.setContent("第 " + index ++ + " 个item");
        mAdapter1.addMsg(bean);
    }

    public void onTest6(View v) {

    }

    /**
     * 滚动到具体位置
     * @param v
     */
    public void onTest7(View v) {


//        rvScroll2.scrollBy(0, 140);
        smoothMoveToPosition(8);
    }

    /**
     * 滑动到指定位置
     */
    private void smoothMoveToPosition(final int position) {
        int firstItem = rvScroll2.getChildLayoutPosition(rvScroll2.getChildAt(0)); // 第一个显示item的位置
        int lastItem = rvScroll2.getChildLayoutPosition(rvScroll2.getChildAt(rvScroll2.getChildCount() - 1)); // 最后一个显示item的位置
        Utils.log("firstItem: " + firstItem + "  lastItem: " + lastItem);
        if (position < firstItem) {//往上定位
            // 第一种可能:跳转位置在第一个可见位置之前，使用smoothScrollToPosition
            rvScroll2.smoothScrollToPosition(position);
        } else if (position <= lastItem) {//往下定位
            // 第二种可能:跳转位置在第一个可见位置之后，最后一个可见项之前
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < rvScroll2.getChildCount()) {
                int top = rvScroll2.getChildAt(movePosition).getTop(); // 拿到需要滚动的item距离RecyclerView 顶部的距离
                Utils.log("top: " + top);
                // smoothScrollToPosition 不会有效果，此时调用smoothScrollBy来滑动到指定位置
                rvScroll2.smoothScrollBy(0, top);
            }
        }else{//未读item是最后一个的时候
            rvScroll2.smoothScrollToPosition(position);
        }
    }

    public void onTest8(View v) {
        rvScroll2.scrollToPosition(16);
        rvScroll2.postDelayed(new Runnable() {
            @Override
            public void run() {

//                rvScroll2.smoothScrollBy(0, -200);
                smoothMoveToPositionBottom(16);
            }
        }, 2000);
    }

    /**
     * 滑动指定item到底部显示
     */
    private void smoothMoveToPositionBottom(final int position) {
        int firstItem = rvScroll2.getChildLayoutPosition(rvScroll2.getChildAt(0)); // 第一个显示item的位置
        int lastItem = rvScroll2.getChildLayoutPosition(rvScroll2.getChildAt(rvScroll2.getChildCount() - 1)); // 最后一个显示item的位置
        Utils.log("firstItem: " + firstItem + "  lastItem: " + lastItem);
        if (position < firstItem) {//往上定位
            // 第一种可能:跳转位置在第一个可见位置之前，使用smoothScrollToPosition
            rvScroll2.smoothScrollToPosition(position);
        } else if (position <= lastItem) {//往下定位
            // 第二种可能:跳转位置在第一个可见位置之后，最后一个可见项之前
            int movePosition = lastItem - firstItem;
            if (movePosition >= 0 && movePosition < rvScroll2.getChildCount()) {
                Utils.log("firstItem: " + firstItem + "   lastItem: " + lastItem);
                int bottomFirst = rvScroll2.getChildAt(0).getBottom(); // 拿到需要滚动的item距离RecyclerView 顶部的距离
                int bottom = rvScroll2.getChildAt(movePosition).getBottom(); // 拿到需要滚动的item距离RecyclerView 顶部的距离
                Utils.log("bottomFirst - bottom: " + (bottomFirst - bottom));
                // smoothScrollToPosition 不会有效果，此时调用smoothScrollBy来滑动到指定位置
                rvScroll2.smoothScrollBy(0, bottomFirst - bottom);
            }
        }else{//未读item是最后一个的时候
            rvScroll2.smoothScrollToPosition(position);
        }
    }

    public void onTest9(View v) {

    }

}
