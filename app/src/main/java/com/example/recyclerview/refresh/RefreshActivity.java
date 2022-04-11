package com.example.recyclerview.refresh;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.recyclerview.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

public class RefreshActivity extends AppCompatActivity implements View.OnClickListener, OnLoadmoreListener {

    private MySmartRefreshLayout mSmartRefreshLayout;
    private RecyclerView mSmartRecyclerview;
    private List<MutilBean> mDatas;
    private RecycleViewAdapter mAdapter;
    private Button btnTest;
    protected LinearLayoutManager layoutManager;

    private int page = 0;
    private int index = 0;
    private int pageCount = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        mDatas = new ArrayList<>();
        btnTest = (Button)findViewById(R.id.btn_test);
        btnTest.setOnClickListener(this);
        mSmartRefreshLayout = (MySmartRefreshLayout) findViewById(R.id.smart_refreshlayout);
        mSmartRefreshLayout.setEnableLoadmore(false);
        mSmartRefreshLayout.setEnableRefresh(false);
        mSmartRefreshLayout.setEnableOverScrollDrag(false);
//        mSmartRefreshLayout.setEnableAutoLoadmore(true); // 是否启用列表惯性滑动到底部时自动加载更多
        mSmartRefreshLayout.setEnableOverScrollBounce(false);//是否启用越界回弹
//        mSmartRefreshLayout.setEnablePureScrollMode(true);

        mSmartRefreshLayout.setOnLoadmoreListener(this);
        loadData();
        mSmartRecyclerview = (RecyclerView) findViewById(R.id.smart_recyclerview);
        mSmartRecyclerview.addItemDecoration(new ItemSpace(9, 9, false));
        mSmartRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {

            /**
             * 空闲状态： SCROLL_STATE_IDLE
             * 滑动状态： SCROLL_STATE_TOUCH_SCROLL
             * 惯性滑动状态： SCROLL_STATE_FLING
             * @param
             * @return
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                System.out.println("===========================> onScrollStateChanged state: " + getState(newState) );
                if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                    System.out.println("===========================> onScrollStateChanged 滑动到底部: " + (isSlideToBottom(mSmartRecyclerview) ? "是" : "否"));
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        layoutManager = new GridLayoutManager(RefreshActivity.this, 2, GridLayoutManager.VERTICAL, false);
        mSmartRecyclerview.setLayoutManager(layoutManager);

        mAdapter = new RecycleViewAdapter(this, mDatas);
        mSmartRecyclerview.setAdapter(mAdapter);
    }

    private String getState(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            return "空闲状态";
        } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            return "滑动状态";
        } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
            return "惯性滑动状态";
        }
        return "没匹配";
    }

    /**
     * computeVerticalScrollExtent()是当前屏幕显示的区域高度，
     * computeVerticalScrollOffset() 是当前屏幕之前滑过的距离，
     * 而computeVerticalScrollRange()是整个View控件的高度
     * @param
     * @return
     */
    public boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange()) {
            mSmartRefreshLayout.autoLoadmore(0, 250, 1.0f);
            Toast.makeText(this, "滑动到底部了", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        mSmartRefreshLayout.autoLoadmore(0, 250, 1.0f);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        loadData();
    }

    private void loadData() {
//        AddBean bean = new AddBean("item holder");
//        bean.setUiType(1);
//        mDatas.add(bean);
        for (; index < pageCount; index ++) {
            mDatas.add(new MutilBean("item " + page * pageCount + index));
        }
        if(mAdapter != null)
           mAdapter.notifyDataSetChanged();
        page ++;
        index = 0;
        mSmartRefreshLayout.finishLoadmore();
    }
}
