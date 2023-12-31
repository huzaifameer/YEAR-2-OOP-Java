package com.W1956114.SubClasses;

import com.W1956114.Super.Product;

public class Electronic extends Product {
    private String brand;//saves the product brand
    private int warrantyDays;//save the warranty period

    public Electronic() {
    }

    public Electronic(String productType,String productID, String productName, int availableQuantity, double productPrice, String brand, int warrantyDays) {
        super(productType,productID,productName,availableQuantity,productPrice);//passing the values to the super class constructor
        this.brand = brand;
        this.warrantyDays = warrantyDays;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyDays() {
        return warrantyDays;
    }

    public void setWarrantyDays(int warrantyDays) {
        this.warrantyDays = warrantyDays;
    }

    //method to display the products details
    @Override
    public void displayInfo() {
        System.out.printf("%-10s%-20s%-10s%-10s%-20s%n", getProductID(), getProductName() + " by " + brand, "$ "+getProductPrice(), getAvailableQuantity(),"Warranty - "+getWarrantyDays() + " months  |- Electronic");
    }
}
