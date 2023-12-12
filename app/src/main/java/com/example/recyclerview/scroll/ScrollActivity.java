package com.example.recyclerview.scroll;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.test.TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 滚动到某个位置
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
        for (int i = 0; i < 20; i++) {
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
        for (int i = 0; i < 20; i++) {
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
     * @param v
     */
    public void onTest1(View v) {
        manager1.scrollToPosition(0);
//        manager1.scrollToPosition(13);
//        rvScroll1.scrollToPosition(13);
//        rvScroll2.rese
        rvScroll2.postDelayed(new Runnable() {
            @Override
            public void run() {
                rvScroll1.scrollToPosition(13);
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

}
