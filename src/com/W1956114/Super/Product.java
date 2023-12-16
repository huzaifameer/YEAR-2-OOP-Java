package com.W1956114.Super;

public abstract class Product {
    protected String productID;
    protected String productName;
    protected int availableQuantity;
    protected double productPrice;
    protected String productType;

    public Product() {
    }

    public Product(String productType,String productID, String productName, int availableQuantity, double productPrice) {
        this.productType=productType;
        this.productID = productID;
        this.productName = productName;
        this.availableQuantity = availableQuantity;
        this.productPrice = productPrice;
    }

    public String getProductType(){
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
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

    public abstract void displayInfo();

}
