package com.example.recyclerview.test;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 */
public class TestActivity extends AppCompatActivity {

    public static int scrollDis = 0;
    public static int pos = 0;

    private RecyclerView mRv;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        //1.查找控件
        mRv = findViewById(R.id.recyclerView);

        //2.创建布局管理器-线性布局
        mManager = new LinearLayoutManager(this);
        //设置管理器的水平方向，RecyclerView.VERTICAL垂直方向，RecyclerView.HORIZONTAL水平方向
        mManager.setOrientation(RecyclerView.VERTICAL);//RecyclerView.HORIZONTAL
        //设置布局布局管理器到recyclerView
        mRv.setLayoutManager(mManager);

        //3.设置数据
        List<TestBean> stringList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            TestBean bean = new TestBean();
            bean.setContent("第 " + i + " 个item");
            if(i == 2)
                bean.setItemType(1);
            stringList.add(bean);
        }

        //4.数据适配器
        TestAdapter adapter = new TestAdapter(this, stringList);

//        adapter.notifyItemRemoved();
        //设置适配器到recyclerView
        mRv.setAdapter(adapter);
//        mRv.post(new Runnable() {
//            @Override
//            public void run() {
//                mRv.scrollBy(0, scrollDis);
//            }
//        });
    }

    public void onTest1(View v) {
//        if (mRv != null) {
//            int scrollDistance = mRv.computeVerticalScrollOffset();
//            scrollDis = scrollDistance;
//            Utils.log("scrollDistance: " + scrollDistance);
//        }
        if (mManager != null) {
            pos = mManager.findLastVisibleItemPosition();
        }

    }

    public void onTest2(View v) {
//        if (mRv != null) {
//            mRv.scrollBy(0, scrollDis);
//        }
        if(mManager != null)
            mManager.scrollToPosition(pos);
    }

}
