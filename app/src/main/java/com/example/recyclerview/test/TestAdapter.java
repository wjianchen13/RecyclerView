package com.example.recyclerview.test;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.List;

/**
 * 测试
 */
public class TestAdapter extends RecyclerView.Adapter {

    public static final int TYPE_NORMAL = 0;//普通类型
    public static final int TYPE_SECTION = 1;//特殊类型

    private Context mContext;
    private List<TestBean> mData;

    public TestAdapter(Context context, List<TestBean> list) {
        this.mContext = context;
        this.mData = list;
    }


    @Override
    public int getItemViewType(int position) {
        TestBean bean = mData.get(position);
        return bean.getItemType();
    }

    /**
     * 
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        log("onBindViewHolder viewType： " + viewType);
        if (viewType == TYPE_SECTION) {
            return new ImageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_test1, parent, false));
        } else {
            return new NormalViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_test, parent, false));
        }
    }

    /**
     * 
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        log("onBindViewHolder position： " + holder.getAdapterPosition());
        if (holder instanceof NormalViewHolder) {//普通类型ViewHolder
            NormalViewHolder viewHolder = (NormalViewHolder) holder;
            viewHolder.mTv_name.setText(mData.get(position).getContent());
        } else {
            ImageViewHolder viewHolder = (ImageViewHolder) holder;
            viewHolder.mTv_name.setText(mData.get(position).getContent());
            startAnim(viewHolder.imgvTest);
        }
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
        log("================================================== onFailedToRecycleView： " + holder.isRecyclable());
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        log("onViewRecycled： " + holder.getAdapterPosition() + "    isRecycled: " + holder.isRecyclable());
    }

    /**
     * 一个item创建并附加到了界面上，就是显示到界面上
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        log("onViewAttachedToWindow： " + holder.getAdapterPosition());
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        log("onViewDetachedFromWindow： " + holder.getAdapterPosition());
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
    
    /**
     * 创建ViewHolder
     */
    public class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView mTv_name;

        NormalViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv_name = itemView.findViewById(R.id.tv_name);
            mTv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mTv_name.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 创建ViewHolder
     */
    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView mTv_name;
        ImageView imgvTest;

        ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv_name = itemView.findViewById(R.id.tv_name);
            imgvTest = itemView.findViewById(R.id.imgv_test);
            mTv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mTv_name.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public List<TestBean> getData() {
        return mData;
    }
    
    public static void log(String str) {
        System.out.println("=================================> " + str);
    }

    private void startAnim(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotation", 0f, -360f);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(2000);
        animator.start();
    }
}
