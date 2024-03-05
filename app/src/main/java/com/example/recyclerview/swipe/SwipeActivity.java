package com.example.recyclerview.swipe;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView 侧滑回复功能
 * 参考文档
 * ItemTouchHelper(二)源码简析
 * https://www.jianshu.com/p/b8e45aa3a6ff?nomobile=yes
 * ItemTouchHelper.Callback
 * https://www.xfqiao.com/api/android-zh/android/support/v7/widget/helper/ItemTouchHelper.Callback.html#convertToAbsoluteDirection(int,%20int)
 * RecyclerView 滑动选中
 * https://www.jianshu.com/p/27bb126f21c3
 * Android HapticFeedback触感反馈
 * https://blog.csdn.net/qq_35619409/article/details/85627479
 * https://stackoverflow.com/questions/67280468/how-to-make-swipe-left-to-reply-animation-in-chat-app
 * https://stackoverflow.com/questions/67998827/swipe-to-reply-in-chat-message-like-whats-app-programmatically-in-android
 * github 搜索 swipe reply
 *
 */
public class SwipeActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvTest1;
    private TextView tvTest2;
    private List<String> datas = new ArrayList<>();
    private SwipeAdapter adapter;
    private ItemTouchHelper mItemTouchHelper;

    private LinearLayoutManager layoutManager;

    private int i = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == 1) {
                Toast.makeText(SwipeActivity.this, "add data", Toast.LENGTH_SHORT).show();
                addData();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swpie);
        tvTest1 = findViewById(R.id.tv_test1);
        tvTest1.setOnClickListener(this);
        tvTest2 = findViewById(R.id.tv_test2);
        tvTest2.setOnClickListener(this);
        setData();
        initRecyclerView();
    }

    private void setData() {
        for(int i = 0; i < 20; i ++) {
            String s1 = "test" + i;
            datas.add(s1);
        }
    }

    private RecyclerView recyclerView;

    public void initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerview_vertical);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.left = 0;
                outRect.top = 10;
                outRect.right = 0;
                outRect.bottom = 10;
            }
        });
        adapter = new SwipeAdapter(datas);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(this, adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_test2) {
            Message msg = mHandler.obtainMessage();
            msg.what = 1;
            mHandler.sendMessageDelayed(msg, 5000);
        } else if(v.getId() == R.id.tv_test1) {
            addData();
        }
    }

    private void addData() {
        List<String> datas2 = new ArrayList<>();
        String add1 = "add" + i;
        i ++;
        datas2.add(add1);
        datas.addAll(datas2);
        adapter.notifyItemChanged(datas.size() - 1);
        recyclerView.scrollToPosition(datas.size() - 1);
    }
}
