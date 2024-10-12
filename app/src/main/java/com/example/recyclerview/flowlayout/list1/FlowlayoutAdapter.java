package com.example.recyclerview.flowlayout.list1;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.recyclerview.R;

import java.util.List;

/**
 * Created by baoyz on 2014/6/29.
 */
public class FlowlayoutAdapter extends BaseMultiItemQuickAdapter<FlowlayoutBean, BaseViewHolder> {

    private Context mContext;

    public FlowlayoutAdapter(Context context, List<FlowlayoutBean> data) {
        super(data);
        this.mContext = context;
        addItemType(0, R.layout.item_flowlayout_1);
//        addItemType(0, R.layout.item_refresh);
//        addItemType(2, R.layout.item_add_bottom);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, FlowlayoutBean bean) {
        switch (bean.getUiType()) {
            case 0:
//                resetView(baseViewHolder);
//                List<FlowlayoutItemBean> itemBeans = bean.getList();
//                for(int i = 0; i <itemBeans.size(); i ++) {
//                    setView(baseViewHolder, itemBeans.get(i));
//                }
                ((UserBadgeView)baseViewHolder.getView(R.id.ubv)).setView(bean);

//                baseViewHolder.setText(R.id.tv_name, bean.getName());
                break;
//            case 1:
//                View vHeader = baseViewHolder.getView(R.id.llyt_content);
//                ViewGroup.LayoutParams vp1 = vHeader.getLayoutParams();
//                if (vp1 != null) {
//                    vp1.width = getScreenWidth(mContext);
//                    vHeader.setLayoutParams(vp1);
//                }
//                break;
//            case 2: // bottom
//                View vBottom = baseViewHolder.getView(R.id.tv_bottom);
//                ViewGroup.LayoutParams vp2 = vBottom.getLayoutParams();
//                if (vp2 != null) {
//                    vp2.width = getScreenWidth(mContext) / 2 - 2;
//                    vBottom.setLayoutParams(vp2);
//                }
//                break;
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
                /**
                 * 我们的spanCount为2，每个item的span size为1，因此一个header需要的span size则为2
                 */
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == 1 || getItemViewType(position) == 2
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

}
