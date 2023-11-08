package com.example.recyclerview.movie;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.List;

/**
 * 电影位
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private List<String> mDataset;

    public MovieAdapter(List<String> dataset) {
        super();
        mDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.item_movie, null);
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

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
