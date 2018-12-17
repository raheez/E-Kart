package com.example.muhammedraheezrahman.e_commerceapp.Model;

public class ProductModel {

    private String productName;
    private float oldPrice,newPrice;

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
