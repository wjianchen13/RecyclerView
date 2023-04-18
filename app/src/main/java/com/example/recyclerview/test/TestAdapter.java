package com.example.recyclerview.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private Context mContext;
    private List<String> mData;

    public TestAdapter(Context context, List<String> stringList) {
        this.mContext = context;
        this.mData = stringList;
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
        if (mContext != null) {
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_test, parent, false));
        }
        return null;
    }

    /**
     * 
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        log("onBindViewHolder position： " + holder.getAdapterPosition());
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mTv_name.setText(mData.get(position));
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
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
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTv_name;

        ViewHolder(@NonNull View itemView) {
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

    public List<String> getData() {
        return mData;
    }
    
    public static void log(String str) {
        System.out.println("=================================> " + str);
    }
}
