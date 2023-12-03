package com.W1956114.SubClasses;

import com.W1956114.Super.Product;

public class Electronic extends Product {
    private String brand;
    private int warrantyDays;

    public Electronic() {
    }

    public Electronic(String productID, String productName, int availableQuantity, double productPrice, String brand, int warrantyDays) {
        super(productID,productName,availableQuantity,productPrice);
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

    /*An extra method for no reason*/
    @Override
    public void displayInfo() {
        /*System.out.println("Electronics - " + getProductName() + " by " + brand);
        System.out.println("ID: " + getProductID());
        System.out.println("Price: $" + getProductPrice());
        System.out.println("Warranty Period: " + getWarrantyDays() + " years");
        System.out.println("Available Items: " + getAvailableQuantity());*/

        System.out.printf("%-10s%-15s%-10s%-10s%-10s%n", getProductID(), getProductName() + " by " + brand, "$ "+getProductPrice(), getAvailableQuantity(),getWarrantyDays() + " years - Electronic");
    }
    /*An extra method for no reason*/
}
