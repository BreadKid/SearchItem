package com.breadykid.searchitem.domain;

/**
 * Created by breadykid on 2016/12/11.
 * 商品实体类
 */

public class Item {

    private String name;
    private String type;
    private String size;

    public Item(String name, String type, String size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
