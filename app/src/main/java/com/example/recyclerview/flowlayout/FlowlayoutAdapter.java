package com.example.recyclerview.flowlayout;

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
                resetView(baseViewHolder);
                List<FlowlayoutItemBean> itemBeans = bean.getList();
                for(int i = 0; i <itemBeans.size(); i ++) {
                    setView(baseViewHolder, itemBeans.get(i));
                }



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

    private void resetView(BaseViewHolder baseViewHolder) {
        baseViewHolder.setGone(R.id.tv_test1, false);
        baseViewHolder.setGone(R.id.tv_test2, false);
        baseViewHolder.setGone(R.id.tv_test3, false);
        baseViewHolder.setGone(R.id.tv_test4, false);
        baseViewHolder.setGone(R.id.tv_test5, false);
        baseViewHolder.setGone(R.id.tv_test6, false);
        baseViewHolder.setGone(R.id.tv_test7, false);
        baseViewHolder.setGone(R.id.tv_test8, false);
        baseViewHolder.setGone(R.id.tv_test9, false);
        baseViewHolder.setGone(R.id.tv_test10, false);
        baseViewHolder.setGone(R.id.tv_test11, false);
        baseViewHolder.setGone(R.id.tv_test12, false);
        baseViewHolder.setGone(R.id.tv_test13, false);
        baseViewHolder.setGone(R.id.tv_test14, false);
        baseViewHolder.setGone(R.id.tv_test15, false);
    }

    private void setView(BaseViewHolder baseViewHolder, FlowlayoutItemBean itemBean) {
        if(itemBean != null && baseViewHolder != null) {
            int id = itemBean.getId();
            switch (id) {
                case FlowlayoutItemBean.TYPE_1:
                    baseViewHolder.setText(R.id.tv_test1, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test1, true);
                    break;
                case FlowlayoutItemBean.TYPE_2:
                    baseViewHolder.setText(R.id.tv_test2, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test2, true);
                    break;
                case FlowlayoutItemBean.TYPE_3:
                    baseViewHolder.setText(R.id.tv_test3, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test3, true);
                    break;
                case FlowlayoutItemBean.TYPE_4:
//                    baseViewHolder.setText(R.id.tv_test4, itemBean.getContent());
//                    baseViewHolder.setGone(R.id.tv_test4, true);
                    break;
                case FlowlayoutItemBean.TYPE_5:
                    baseViewHolder.setText(R.id.tv_test5, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test5, true);
                    break;
                case FlowlayoutItemBean.TYPE_6:
                    baseViewHolder.setText(R.id.tv_test6, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test6, true);
                    break;
                case FlowlayoutItemBean.TYPE_7:
                    baseViewHolder.setText(R.id.tv_test7, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test7, true);
                    break;
                case FlowlayoutItemBean.TYPE_8:
                    baseViewHolder.setText(R.id.tv_test8, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test8, true);
                    break;
                case FlowlayoutItemBean.TYPE_9:
                    baseViewHolder.setText(R.id.tv_test9, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test9, true);
                    break;
                case FlowlayoutItemBean.TYPE_10:
                    baseViewHolder.setText(R.id.tv_test10, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test10, true);
                    break;
                case FlowlayoutItemBean.TYPE_11:
                    baseViewHolder.setText(R.id.tv_test11, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test11, true);
                    break;
                case FlowlayoutItemBean.TYPE_12:
                    baseViewHolder.setText(R.id.tv_test12, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test12, true);
                    break;
                case FlowlayoutItemBean.TYPE_13:
                    baseViewHolder.setText(R.id.tv_test13, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test13, true);
                    break;
                case FlowlayoutItemBean.TYPE_14:
                    baseViewHolder.setText(R.id.tv_test14, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test14, true);
                    break;
                case FlowlayoutItemBean.TYPE_15:
                    baseViewHolder.setText(R.id.tv_test15, itemBean.getContent());
                    baseViewHolder.setGone(R.id.tv_test15, true);
                    break;
            }
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
