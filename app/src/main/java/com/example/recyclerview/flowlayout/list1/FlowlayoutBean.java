package com.example.recyclerview.flowlayout.list1;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */

public class FlowlayoutBean implements MultiItemEntity {

    private int uiType;

    private List<FlowlayoutItemBean> mList;

    public FlowlayoutBean() {

    }

    @Override
    public int getItemType() {
        return uiType;
    }

    public List<FlowlayoutItemBean> getList() {
        return mList;
    }

    public void setList(List<FlowlayoutItemBean> list) {
        this.mList = list;
    }

    public int getUiType() {
        return uiType;
    }

    public void setUiType(int uiType) {
        this.uiType = uiType;
    }
}
