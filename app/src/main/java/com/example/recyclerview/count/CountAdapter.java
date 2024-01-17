package com.example.recyclerview.count;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by baoyz on 2014/6/29.
 */
public class CountAdapter extends RecyclerView.Adapter<CountAdapter.ViewHolder>{

    private List<String> mDataset;

    public CountAdapter(List<String> dataset) {
        super();
        mDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null);
        ViewHolder holder = new ViewHolder(view);
        System.out.println("-----------------------> onCreateViewHolder");
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(mDataset.get(i));
        System.out.println("-----------------------> onBindViewHolder");
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
            mTextView = (TextView) itemView;
        }
    }
}
