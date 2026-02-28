package com.example.recyclerview.test11;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by baoyz on 2014/6/29.
 */
public class TestAdapter11 extends RecyclerView.Adapter<TestAdapter11.ViewHolder>{

    private List<User11> mDataset;

    public TestAdapter11(List<User11> dataset) {
        super();
        mDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText("id: " + mDataset.get(i).id + "   name: " + mDataset.get(i).name);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public List<User11> getDataset() {
        return mDataset;
    }

    public void setDataset(List<User11> mDataset) {
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
