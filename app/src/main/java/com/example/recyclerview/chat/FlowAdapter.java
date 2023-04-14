package com.example.recyclerview.chat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.chat.bean.Product;

import java.util.List;

public     class FlowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Product.Classify.Des> list;
    private Product.Classify.Des selectDes;
    private Context context;

    public FlowAdapter(Context context, List<Product.Classify.Des> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(View.inflate(context, R.layout.chat_flow_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TextView textView = ((MyHolder) holder).text;

        final Product.Classify.Des des = list.get(position);
        if (des.isSelect) {
            textView.setBackground(context.getResources().getDrawable(R.drawable.product_item_select_back));
        } else {
            textView.setBackground(context.getResources().getDrawable(R.drawable.product_item_back));
        }
        textView.setText(des.des);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (des != selectDes) {
                    if (selectDes != null) {
                        selectDes.isSelect = false;
                    }
                }
                des.isSelect = true;
                selectDes = des;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private TextView text;

        public MyHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.flow_text);
        }
    }
}
