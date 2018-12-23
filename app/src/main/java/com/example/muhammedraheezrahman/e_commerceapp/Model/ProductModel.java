package com.example.muhammedraheezrahman.e_commerceapp.Model;

public class ProductModel {

    public ProductModel(String productName, float oldPrice, float newPrice,int thumbNail) {
        this.productName = productName;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.thumbNail = thumbNail;
    }

    private String productName;
    private float oldPrice,newPrice;
    private int thumbNail;

    public int getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(int thumbNail) {
        this.thumbNail = thumbNail;
    }

    public float getNewPrice() {
        return newPrice;
    }

    public float getOldPrice() {
        return oldPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setNewPrice(float newPrice) {
        this.newPrice = newPrice;
    }

    public void setOldPrice(float oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
