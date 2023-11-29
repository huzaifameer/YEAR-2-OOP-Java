package com.W1956114.SubClasses;

import com.W1956114.Super.Product;

public class Clothing extends Product {
    private String clothSize;
    private String clothColor;

    public Clothing() {
    }

    public Clothing(String productID, String productName, int availableQuantity, double productPrice, String clothSize, String clothColor) {
        super(productID,productName,availableQuantity,productPrice);
        this.clothSize = clothSize;
        this.clothColor = clothColor;
    }

    public String getClothSize() {
        return clothSize;
    }

    public void setClothSize(String clothSize) {
        this.clothSize = clothSize;
    }

    public String getClothColor() {
        return clothColor;
    }

    public void setClothColor(String clothColor) {
        this.clothColor = clothColor;
    }
    @Override
    public void displayInfo() {
        System.out.println("Clothing - " + getProductName());
        System.out.println("ID: " + getProductID());
        System.out.println("Price: $" + getProductPrice());
        System.out.println("Size: " + getClothSize());
        System.out.println("Color: " + getClothColor());
        System.out.println("Available Items: " + getAvailableQuantity());
    }
    /*An extra method for no reason*/
}
