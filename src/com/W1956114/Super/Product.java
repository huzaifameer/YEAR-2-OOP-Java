package com.W1956114.Super;

public abstract class Product {
    protected String productID;
    protected String productName;
    protected int availableQuantity;
    protected double productPrice;

    public Product() {
    }

    public Product(String productID, String productName, int availableQuantity, double productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.availableQuantity = availableQuantity;
        this.productPrice = productPrice;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
