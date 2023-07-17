package com.example.recyclerview.test;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class TestBean implements MultiItemEntity {

    private String content;
    private int itemType;

    public TestBean() {

    }

    public TestBean(String content, int itemType) {
        this.content = content;
        this.itemType = itemType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }


}
