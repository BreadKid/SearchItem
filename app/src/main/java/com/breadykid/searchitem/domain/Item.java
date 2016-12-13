package com.breadykid.searchitem.domain;

/**
 * Created by breadykid on 2016/12/11.
 * 商品实体类
 */

public class Item {

    private String code;//条码
    private String name;//品名
    private String type;//分类
    private String size;//规格
    private String company;//公司
//    private String controy;//国家

    public Item(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Item(String name, String type, String size, String company) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.company = company;
    }

    public Item(String code, String name, String type, String size, String company) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.size = size;
        this.company = company;
//        this.controy = controy;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", company='" + company + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

//    public String getControy() {
//        return controy;
//    }

//    public void setControy(String controy) {
//        this.controy = controy;
//    }

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
