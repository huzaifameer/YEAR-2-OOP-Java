package com.W1956114.SubClasses;

public class Clothing{
    private String clothSize;
    private String clothColor;

    public Clothing() {
    }

    public Clothing(String clothSize, String clothColor) {
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
}
