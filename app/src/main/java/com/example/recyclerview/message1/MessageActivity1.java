package com.example.recyclerview.message1;

import static android.view.View.OnClickListener;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.message.MessageAdapter;
import com.example.recyclerview.message.MessageLayoutManager;
import com.example.recyclerview.test.TestBean;
import com.example.recyclerview.utils.SoftInputUtils;
import com.example.recyclerview.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView 模拟消息相关功能
 * 有一条数据已经显示了，要把这一条数据滚动到最下面的位置，怎么实现
 * 这个项目改变Recycler的高度是通过Barrier来实现的，然后当打开软键盘RecyclerView变高之后，把数据移动到中间
 * 点击关闭，关闭软键盘，RecyclerView高度变小，会发现最上面一条数据变成空白不显示了，然后调用notifyDataSetChanged
 * 又可以显示出来
 */
public class MessageActivity1 extends AppCompatActivity implements OnClickListener{

    private MessageAdapter1 mAdapter1;
    private int index = 0;
    private RecyclerView rvScroll1;
    private List<TestBean> mData1;
    private MessageLayoutManager1 manager1;
    private View line3;
    private EditText edtvTest;
    private ImageView imgvChat;
    private ImageView imgvClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message1);
        line3 = findViewById(R.id.guideline3);
        edtvTest = findViewById(R.id.edtv_test);
        imgvChat = findViewById(R.id.imgv_chat);
        imgvChat.setOnClickListener(this);
        imgvClose = findViewById(R.id.imgv_close);
        imgvClose.setOnClickListener(this);
        initRecyclerView1();
    }

    private void initRecyclerView1() {
        rvScroll1 = findViewById(R.id.rv_scroll1);
//        rvScroll1.setItemAnimator(null);
        manager1 = new MessageLayoutManager1(this);
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

        mAdapter1 = new MessageAdapter1(this, mData1, rvScroll1);

        manager1.setDinoListener(mAdapter1);
//        adapter.notifyItemRemoved();
        //设置适配器到recyclerView
//        rvScroll1.setAdapter(mAdapter1);
        mAdapter1.bindToRecyclerView(rvScroll1);
    }

    /**
     * 修改高度
     * @param v
     */
    public void onTest1(View v) {
        change1();
    }

    /**
     * 恢复高度
     * @param v
     */
    public void onTest2(View v) {
        change2();
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
//        TestBean bean = new TestBean();
//        bean.setContent("第 " + index ++ + " 个item");
//        mAdapter1.addMsg(bean);
        mAdapter1.notifyDataSetChanged();
    }

    public void onTest6(View v) {
        mAdapter1.clearScroll2Bottom(true);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.imgv_chat) {
            showChat();
        } else if(view.getId() == R.id.imgv_close) {
            hideChat();
        }
    }

    private void showChat() {
        line3.setVisibility(View.GONE);
        edtvTest.setVisibility(View.VISIBLE);
        imgvChat.setVisibility(View.GONE);
        imgvClose.setVisibility(View.VISIBLE);
        rvScroll1.post(new Runnable() {
            @Override
            public void run() {
                SoftInputUtils.showSoftInput(edtvTest);
            }
        });
        change1();
//        SoftInputUtils.showSoftInput(edtvTest);
//        mAdapter1.notifyDataSetChanged();
    }


    private void hideChat() {
        line3.setVisibility(View.VISIBLE);
        edtvTest.setVisibility(View.GONE);
        imgvChat.setVisibility(View.VISIBLE);
        imgvClose.setVisibility(View.GONE);
//        rvScroll1.post(new Runnable() {
//            @Override
//            public void run() {
//                SoftInputUtils.hideSoftInput(edtvTest);
//            }
//        });
        SoftInputUtils.hideSoftInput(edtvTest);
//        mAdapter1.notifyDataSetChanged();
        change2();
    }

    private void change1() {
        ConstraintLayout.LayoutParams p = (ConstraintLayout.LayoutParams)rvScroll1.getLayoutParams();
        p.height = Utils.dip2px(this, 250);
        rvScroll1.setLayoutParams(p);
    }

    private void change2() {
        ConstraintLayout.LayoutParams p = (ConstraintLayout.LayoutParams)rvScroll1.getLayoutParams();
        p.height = Utils.dip2px(this, 120);
        rvScroll1.setLayoutParams(p);
    }

}
