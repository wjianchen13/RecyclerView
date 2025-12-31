package com.example.recyclerview.message;

import static android.view.View.*;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.scroll.ScrollAdapter;
import com.example.recyclerview.scroll.ScrollLayoutManager;
import com.example.recyclerview.test.TestBean;
import com.example.recyclerview.utils.SoftInputUtils;
import com.example.recyclerview.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView 模拟消息相关功能
 */
public class MessageActivity extends AppCompatActivity implements OnClickListener{

    private MessageAdapter mAdapter1;
    private int index = 0;
    private RecyclerView rvScroll1;
    private List<TestBean> mData1;
    private MessageLayoutManager manager1;
    private View line1;
    private View line2;
    private View line3;
    private EditText edtvTest;
    private ImageView imgvChat;
    private ImageView imgvClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        line1 = findViewById(R.id.guideline1);
        line2 = findViewById(R.id.guideline2);
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
        manager1 = new MessageLayoutManager(this);
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

        mAdapter1 = new MessageAdapter(this, mData1, rvScroll1);
        manager1.setDinoListener(mAdapter1);
//        adapter.notifyItemRemoved();
        //设置适配器到recyclerView
        rvScroll1.setAdapter(mAdapter1);
    }

    /**
     * 滚动到具体位置
     * 滚动到具体位置是有问题的，当滚动的位置已经显示，这时滚动最后实际显示出下一个item
     * @param v
     */
    public void onTest1(View v) {
        executeAfterAllAnimationsAreFinished(new CallBack() {
            @Override
            public void onSuccess() {
                TestBean bean = new TestBean();
                bean.setContent("第 " + index ++ + " 个item");
                mAdapter1.addMsg(bean);
            }
        });
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

    }


    public void onTest9(View v) {

    }

    private void executeAfterAllAnimationsAreFinished(CallBack cb) {
        if(rvScroll1 != null) {
            rvScroll1.post(new Runnable() {
                @Override
                public void run() {
                    if(rvScroll1 != null) {
                        Utils.log("rvScroll1.isAnimating(): " + rvScroll1.isAnimating());
                    } else {
                        Utils.log("rvScroll1 == null: ");
                    }
                    if(rvScroll1 != null && rvScroll1.isAnimating()) {
                        if(rvScroll1.getItemAnimator() != null) {
                            Utils.log("rvScroll1.getItemAnimator().isRunning(): " +  rvScroll1.getItemAnimator().isRunning());
                        } else {
                            Utils.log("rvScroll1.getItemAnimator() == null: ");
                        }
                        if(rvScroll1.getItemAnimator() != null && rvScroll1.getItemAnimator().isRunning()) {
                            rvScroll1.postDelayed(this, 100);
                        }
                    } else {
                        if(cb != null)
                            cb.onSuccess();
                    }
                }
            });
        }
    }

    private interface CallBack {
        void onSuccess();
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
        line1.setVisibility(View.GONE);
        line2.setVisibility(View.VISIBLE);
        line3.setVisibility(View.GONE);
        edtvTest.setVisibility(View.VISIBLE);
        imgvChat.setVisibility(View.GONE);
        imgvClose.setVisibility(View.VISIBLE);
        SoftInputUtils.showSoftInput(edtvTest);
    }


    private void hideChat() {
        line1.setVisibility(View.VISIBLE);
        line2.setVisibility(View.GONE);
        line3.setVisibility(View.VISIBLE);
        edtvTest.setVisibility(View.GONE);
        imgvChat.setVisibility(View.VISIBLE);
        imgvClose.setVisibility(View.GONE);
        SoftInputUtils.hideSoftInput(edtvTest);
    }

}
