package com.W1956114.Super;

public abstract class Product {
    protected String productID;
    protected String productName;
    protected int availableQuantity;

    public Product() {
    }

    public Product(String productID, String productName, int availableQuantity) {
        this.productID = productID;
        this.productName = productName;
        this.availableQuantity = availableQuantity;
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
}
