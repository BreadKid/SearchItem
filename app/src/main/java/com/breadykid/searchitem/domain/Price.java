package com.breadykid.searchitem.domain;

/**
 * Created by breadykid on 2016/12/11.
 * 商品价格实体类
 */

public class Price {

    private String website;
    private Item item;
    private Double price;

    public Price(String website, Item item, Double price) {
        this.website = website;
        this.item = item;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "website='" + website + '\'' +
                ", item=" + item +
                ", price=" + price +
                '}';
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
