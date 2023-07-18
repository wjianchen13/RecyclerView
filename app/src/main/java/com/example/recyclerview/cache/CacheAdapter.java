package com.example.recyclerview.cache;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.recyclerview.R;
import com.example.recyclerview.test.TestBean;

import java.util.List;

/**
 * 测试
 */
public class CacheAdapter extends BaseMultiItemQuickAdapter<TestBean, BaseViewHolder> {

    public static final int TYPE_NORMAL = 0;//普通类型
    public static final int TYPE_SECTION = 1;//特殊类型

    private Context mContext;

    public CacheAdapter(Context context, List<TestBean> list) {
        super(list);
        this.mContext = context;
        addItemType(TYPE_NORMAL, R.layout.item_test);
        addItemType(TYPE_SECTION, R.layout.item_test1);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        if (item == null) {
            return;
        }
        log("=============================> item: " + item.getContent());
        if (item.getItemType() == TYPE_NORMAL) {
            helper.setText(R.id.tv_name, item.getContent());
        } else if (item.getItemType() == TYPE_SECTION) {
            helper.setText(R.id.tv_name, item.getContent());
            startAnim1(helper.getView(R.id.imgv_test));
        }
    }

    /**
     * recycleView附加到了界面上。只会调用一次。
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        log("onAttachedToRecyclerView： ");
    }


    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        log("onDetachedFromRecyclerView： ");
    }

    /**
     * 指定RecyclerView有多少个Item
     * @return
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<TestBean> getData() {
        return mData;
    }
    
    public static void log(String str) {
        System.out.println("=================================> " + str);
    }

    private void startAnim1(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotation", 0f, -360f);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(2000);
        animator.start();
    }
}
