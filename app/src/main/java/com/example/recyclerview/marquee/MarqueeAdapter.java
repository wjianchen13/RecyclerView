package com.example.recyclerview.marquee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.List;

/**
 * TextView 滚动
 */
public class MarqueeAdapter extends RecyclerView.Adapter<MarqueeAdapter.ViewHolder>{

    private List<String> mDataset;

    public MarqueeAdapter(List<String> dataset) {
        super();
        mDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_marquee, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(mDataset.get(i));
        viewHolder.mTextView.setSelected(true);
//        viewHolder.mTextView.requestFocus();
//        viewHolder.mTextView.setFocusable(true);
//        viewHolder.mTextView.setFocusableInTouchMode(true);
//        viewHolder.mTextView.setSelected(true);
//        viewHolder.mTextView.requestFocusFromTouch();
    }


    @Override
    public void onViewAttachedToWindow(@NonNull MarqueeAdapter.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
//        ViewHolder viewHolder = (ViewHolder) holder;
//
//        viewHolder.mTextView.setFocusable(true);
//        viewHolder.mTextView.setFocusableInTouchMode(true);
//        viewHolder.mTextView.setSelected(true);
//        viewHolder.mTextView.requestFocusFromTouch();

    }

//    @Override
//    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
//        super.onViewDetachedFromWindow(holder);
//        ViewHolder viewHolder = (ViewHolder) holder;
//        viewHolder.mTextView.setSelected(false);
//    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_test);
        }
    }
}
