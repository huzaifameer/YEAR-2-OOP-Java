package com.W1956114.SubClasses;

import com.W1956114.Super.Product;

public class Clothing extends Product {
    private String clothSize;//saves the size of the cloth
    private String clothColor;//saves the color of the product

    public Clothing() {
    }
    //parameterized constructor
    public Clothing(String productType,String productID, String productName, int availableQuantity, double productPrice, String clothSize, String clothColor) {
        super(productType,productID,productName,availableQuantity,productPrice);//passing the common details to the super class
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

    //method to display the clothing products details
    @Override
    public void displayInfo() {
        System.out.printf("%-10s%-20s%-10s%-10s%-20s%-20s%n", getProductID(),getProductName(), "$ "+getProductPrice(), getAvailableQuantity(),"Size - " +getClothSize(), "Color - "+getClothColor()+" |-Clothing");
    }

}
