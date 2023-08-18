package com.example.recyclerview.cache;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.test.TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存RecyclerView
 * 实现
 * 1 在onViewAttachedToWindow 里面判断小于5条数据就添加
 * 2 在onBindViewHolder里面判断是否是最后一条数据，读取缓存加载数据
 * 3 移动到最后一个item的时候加载缓存 带动画
 * 4 移动到最后一个item的时候加载缓存 不带动画
 * 5 重新解析的时候，先把缓存的数据读取到列表显示，实际上是清除缓存数据，然后把列表显示的数据重新生成一个对应的风格
 * 6 重新解析的时候也包括软键盘弹出的时候，处理和上述步骤5一致
 *
 * notifyItemRangeInserted
 * notifyItemInserted
 * notifyItemChanged()
 *
 */
public class CacheActivity extends AppCompatActivity {

    private CacheAdapter mAdapter;
    private int index = 0;
    private List<TestBean> mData;
    private CustomLinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView rvCache = findViewById(R.id.rv_cache);

        manager = new CustomLinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);

        rvCache.setLayoutManager(manager);
        rvCache.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = 20;
                outRect.top = 10;
                outRect.right = 20;
                outRect.bottom = 10;
            }
        });
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestBean bean = new TestBean();
            bean.setContent("第 " + index ++ + " 个item");
            mData.add(bean);
        }

        mAdapter = new CacheAdapter(this, mData, rvCache);
        manager.setDinoListener(mAdapter);
//        adapter.notifyItemRemoved();
        //设置适配器到recyclerView
        rvCache.setAdapter(mAdapter);
    }

    public void onTest1(View v) {
        int position = mData.size();
        TestBean bean = new TestBean();
        bean.setContent("第 " + index ++ + " 个item");
        mData.add(bean);
        mAdapter.notifyItemInserted(position);
        manager.scrollToPosition(mData.size() - 1);
    }

    public void onTest2(View v) {
        int start = mData.size();
        for (int i = 0; i < 5; i++) {
            TestBean bean = new TestBean();
                bean.setContent("第 " + index ++ + " 个item");
                mData.add(bean);
        }
        mAdapter.notifyItemRangeInserted(start, mData.size() - 1);
        manager.scrollToPosition(mData.size() - 1);
    }

    public void onTest3(View v) {
        mData.remove(0);
        mAdapter.notifyItemRemoved(0);
    }

    public void onTest4(View v) {
        mData.remove(2);
        mData.remove(1);
        mData.remove(0);
        mAdapter.notifyItemRangeRemoved(0, 3);
    }

    public void onTest5(View v) {
        TestBean bean = new TestBean();
        bean.setContent("第 " + index ++ + " 个item");
        mAdapter.addMsg(bean);
    }

    public void onTest6(View v) {

    }

}
