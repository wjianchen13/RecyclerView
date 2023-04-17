package com.example.recyclerview.chat;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.chat.bean.Product;
import com.example.recyclerview.chat.flow.FlowLayoutManager;
import com.example.recyclerview.chat.flow.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xiangcheng on 17/9/26.
 */

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = ProductAdapter.class.getSimpleName();
    private List<Product.Classify> classifies;
    private Context context;

    public ProductAdapter(Context context, List<Product.Classify> classifies) {
        this.context = context;
        this.classifies = classifies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductHolder(View.inflate(context, R.layout.chat_product_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ProductHolder productHolder = (ProductHolder) holder;
        Product.Classify classify = classifies.get(position);
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        if(params != null) {
            params.width = 1440;
            holder.itemView.setLayoutParams(params);
        }

        productHolder.title.setText(classify.title);
//        if (productHolder.itemView.getTag() == null) {
//            productHolder.des.addItemDecoration(new SpaceItemDecoration(dp2px(1)));
//            productHolder.itemView.setTag("item");
//        }
//        productHolder.des.addItemDecoration(new SpaceItemDecoration(dp2px(10)));

//        productHolder.des.setAdapter(new FlowAdapter(classify.des));

        // 二级rv相关处理
        productHolder.list.clear();
        productHolder.list.addAll(classifies.get(position).des);
        if (productHolder.mFlowAdapter == null) {
            System.out.println("=================================> mFlowAdapter == null");
            productHolder.mFlowAdapter = new FlowAdapter(context, productHolder.list);
            final FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
            productHolder.des.setLayoutManager(flowLayoutManager);
//            productHolder.des.addItemDecoration(new GridSpacingItemDecoration(2, 20, false));
            productHolder.des.addItemDecoration(new SpaceItemDecoration(dp2px(1)));
            productHolder.des.setAdapter(productHolder.mFlowAdapter);
        } else {
            System.out.println("=================================> mFlowAdapter != null");
            productHolder.mFlowAdapter.notifyDataSetChanged();
        }
    }

    public String getTitle(int position) {
        return classifies.get(position).title;
    }

    @Override
    public int getItemCount() {
        return classifies.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView des;

        private FlowAdapter mFlowAdapter;
        private List<Product.Classify.Des> list = new ArrayList<>();

        public ProductHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            des = (RecyclerView) itemView.findViewById(R.id.des);
        }
    }



    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }
}
