package com.example.recyclerview.swipe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.swipe.base.ItemTouchHelperAdapter;

import java.util.List;

/**
 * Created by baoyz on 2014/6/29.
 */
public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private List<String> mDataset;

    public SwipeAdapter(List<String> dataset) {
        super();
        mDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = View.inflate(viewGroup.getContext(), R.layout.item_swipe, null);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_swipe, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(mDataset.get(i));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public List<String> getDataset() {
        return mDataset;
    }

    public void setDataset(List<String> mDataset) {
        this.mDataset = mDataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}
