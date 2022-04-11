package com.example.recyclerview.add;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/3/29.
 */

public class AddBean implements MultiItemEntity {

    /**
     * 主播昵称
     */
    private String name;

    private int uiType;


    public AddBean(String name) {
        this.name = name;
    }

    @Override
    public int getItemType() {
        return uiType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUiType() {
        return uiType;
    }

    public void setUiType(int uiType) {
        this.uiType = uiType;
    }
}
