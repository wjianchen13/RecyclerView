package com.example.recyclerview.refresh;

import android.content.Context;
import android.content.res.Resources;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.recyclerview.R;

import java.util.List;

/**
 * Created by baoyz on 2014/6/29.
 */
public class RecycleViewAdapter extends BaseMultiItemQuickAdapter<MutilBean, BaseViewHolder> {

    private Context mContext;

    public RecycleViewAdapter(Context context, List<MutilBean> data) {
        super(data);
        this.mContext = context;
        addItemType(1, R.layout.item_refresh_holder);
        addItemType(0, R.layout.item_refresh);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, MutilBean bean) {
        switch (bean.getUiType()) {
            case 0:
                baseViewHolder.setText(R.id.tv_name, bean.getName());
                break;
            case 1:
                View vHeader = baseViewHolder.getView(R.id.tv_holder);
                ViewGroup.LayoutParams vp1 = vHeader.getLayoutParams();
                if (vp1 != null) {
                    vp1.width = getScreenWidth(mContext) / 2 - 2;
                    vHeader.setLayoutParams(vp1);
                }
                break;
        }

    }

    /**
     * 返回屏幕宽(px)
     */
    public int getScreenWidth(Context context) {
        if (context == null)
            return 0;

        Resources resources = context.getResources();
        if (resources == null)
            return 0;

        DisplayMetrics metrics = resources.getDisplayMetrics();
        if (metrics == null)
            return 0;

        return metrics.widthPixels;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == 1
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

}
