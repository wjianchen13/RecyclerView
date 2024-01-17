package com.example.recyclerview.flexbox;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.test.TestAdapter;
import com.example.recyclerview.test.TestBean;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 */
public class FlexboxActivity extends AppCompatActivity {

    public static int scrollDis = 0;
    public static int pos = 0;

    private RecyclerView mRv;
    private FlexboxLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox);
        //1.查找控件
        mRv = findViewById(R.id.recyclerView);

        //2.创建布局管理器-线性布局
        mManager = new FlexboxLayoutManager(this);
        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
        mManager.setFlexDirection(FlexDirection.ROW);//主轴为水平方向，起点在左端。
        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
        mManager.setFlexWrap(FlexWrap.WRAP);//按正常方向换行
        //justifyContent 属性定义了项目在主轴上的对齐方式。
        mManager.setJustifyContent(JustifyContent.FLEX_START);//交叉轴的起点对齐。
        mRv.setLayoutManager(mManager);

        //3.设置数据
        List<FlexboxBean> stringList = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            FlexboxBean bean = new FlexboxBean();
            bean.setContent("第 " + i + " 个item");
//            if(i == 2)
//                bean.setItemType(1);
            stringList.add(bean);
        }

        //4.数据适配器
        FlexboxAdapter adapter = new FlexboxAdapter(this, stringList);

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
