package com.example.recyclerview.scroll;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.recyclerview.R;
import com.example.recyclerview.cache.CustomLinearLayoutManager;
import com.example.recyclerview.test.TestBean;

import java.util.LinkedList;
import java.util.List;

/**
 * 测试
 */
public class ScrollAdapter extends BaseMultiItemQuickAdapter<TestBean, BaseViewHolder> implements ScrollLayoutManager.OnScrollStateChangedDinoListener {

    public static final int TYPE_NORMAL = 0;//普通类型
    public static final int TYPE_SECTION = 1;//特殊类型

    private Context mContext;
    private RecyclerView mRv;
    /**
     * 滑动到底部时从缓存读取数量
     */
    private static final int DINO_MESSAGE_APPEND_SIZE = 10;

    /**
     * 公聊消息列表缓存的最大值
     */
    private static final int DINO_MESSAGE_MAX_CACHE_SIZE = 50;

    /**
     * 公聊消息列表显示的最大值
     */
    private static final int DINO_MESSAGE_MAX_SIZE = 50;

    /**
     * 缓存超过时一次性移除数量
     */
    private static final int DINO_MESSAGE_REMOVE_CACHE_SIZE = DINO_MESSAGE_MAX_CACHE_SIZE / 3;

    /**
     * 是否滚动到地部
     */
    private boolean isScrollDinoBottom = true;

    private final LinearLayoutManager layoutDinoManager;

    private final List<TestBean> dinoCacheResults = new LinkedList<>();

    public ScrollAdapter(Context context, List<TestBean> list, RecyclerView rv) {
        super(list);
        this.mContext = context;
        this.mRv = rv;
        this.layoutDinoManager = (LinearLayoutManager) mRv.getLayoutManager();
        addItemType(TYPE_NORMAL, R.layout.item_cache);
        addItemType(TYPE_SECTION, R.layout.item_cache1);
    }

    /**
     * 添加一条数据，不移动
     * @param bean
     */
    public void addMsg(TestBean bean) {
        if (have2Scroll()) {
            if (bean != null && mData != null && mRv != null) {
                mData.add(bean);
                notifyItemInserted(mData.size() - 1);
                mRv.scrollToPosition(mData.size() - 1);
            }
        } else {
            if (dinoCacheResults.size() >= DINO_MESSAGE_MAX_CACHE_SIZE) {
                int removeCount = 0;
                while (removeCount < DINO_MESSAGE_REMOVE_CACHE_SIZE && dinoCacheResults.size() > 0) {
                    dinoCacheResults.remove(0);
                    removeCount ++;
                }
                log("移除公屏缓存");
            }
            log("加入公屏消息缓存:" + dinoCacheResults.size());
            dinoCacheResults.add(bean);
//            setStatusUnread(DINO_PLUS_UNREAD_UPDATE);
//            if (DinoDebugDispatcher.getInstance().isLiveMessageDebugInfo()) {
//                chatListDinoHolder.rvDinoRecyclerview.invalidateItemDecorations();
//            }
        }
    }

    /**
     * 添加一条数据，移动到底部
     * @param bean
     */
    public void addMsgBottom(TestBean bean) {
        if(bean != null && mData != null) {
            mData.add(bean);
            notifyItemInserted(mData.size() - 1);
        }
    }

    /**
     * 是否滑动到底部
     * 如果没有贴近到底部，那么不滑动
     */
    public boolean have2Scroll() {
        return isScrollDinoBottom;
    }

    /**
     * 监听recyclerview滚动，用来更新未读消息数据
     * @param state 0:停止滚动 1:正在滚动 2:正在滑动
     */
    @Override
    public void onScrollStateChanged(int state) {
        log("onScrollStateChanged: " + getState(state));
        if (state == 0) {    //停止滚动
            isScrollDinoBottom = isOnBottom(1);
            if (isScrollDinoBottom) {    //滚到了最底
                clearScroll2Bottom(false);
            } else {

            }
        } else if (state == 1 || state == 2) {    //1=正在滚动,2表示正在滑动
            isScrollDinoBottom = false;
        }
    }

    private String getState(int state) {
        String s = "停止滚动";
        if(state == 0) {
            s = "停止滚动";
        } else if(state == 1) {
            s = "正在滚动";
        } else if(state == 2) {
            s = "正在滑动";
        }
        return s;
    }

    /**
     * 清除记录并滚到最底
     */
    public void clearScroll2Bottom(boolean scroll) {
        readCache(true);
        isScrollDinoBottom = true;
        if (scroll) {
            layoutDinoManager.scrollToPosition(getItemCount() - 1);
        }
    }

    /**
     * 是否在底部
     */
    private boolean isOnBottom(int distance) {
        return layoutDinoManager.findLastVisibleItemPosition() >= (layoutDinoManager.getItemCount() - distance);
    }

    /**
     * onBindViewHolder
     * @param helper
     * @param item
     */
    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        if (item == null) {
            return;
        }
        helper.getAdapterPosition();
        log("convert item: " + item.getContent());
        if (item.getItemType() == TYPE_NORMAL) {
            helper.setText(R.id.tv_content, item.getContent());
        } else if (item.getItemType() == TYPE_SECTION) {
            helper.setText(R.id.tv_name, item.getContent());
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        // 滑动到底部时加载缓存，防止因RecyclerView预加载到最后一项后，
        // 又有item加入缓存时，没有调用到onBindViewHolder而无法平滑滚动
        if(mData != null && mRv != null) {
            if (holder.getAdapterPosition() >= mData.size() - 5) {
                mRv.post(() -> readCache(DINO_MESSAGE_APPEND_SIZE, true));
            }
        }
    }

    public void readCache(boolean notify) {
        readCache(DINO_MESSAGE_MAX_CACHE_SIZE, notify);
    }

    private void readCache(int limit, boolean notify) {
        if (dinoCacheResults.size() > 0 && mData != null) {
            int beforeSize = mData.size();
            int insertCount = 0;
            while (insertCount < limit && dinoCacheResults.size() > 0) {
                mData.add(dinoCacheResults.remove(0));
                insertCount ++;
            }
            log("加入公屏消息: " + beforeSize + "-" + insertCount);
            if (notify) notifyItemRangeInserted(beforeSize, insertCount);

            int removeCount = 0;
            while (mData.size() > DINO_MESSAGE_MAX_SIZE) {
                mData.remove(0);
                removeCount ++;
            }
            if (removeCount > 0) {
                log("移除公屏消息：0-" + removeCount);
                if (notify) notifyItemRangeRemoved(0, removeCount);
            }
        }
    }

    /**
     * recycleView附加到了界面上。只会调用一次。
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        log("onAttachedToRecyclerView： ");
    }


    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        log("onDetachedFromRecyclerView： ");
    }

    /**
     * 指定RecyclerView有多少个Item
     * @return
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }
    
    public static void log(String str) {
        System.out.println("=================================> " + str);
    }

}
