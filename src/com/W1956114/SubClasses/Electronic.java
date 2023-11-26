package com.W1956114.SubClasses;

public class Electronic {
    private String brand;
    private int warrantyDays;

    public Electronic() {
    }

    public Electronic(String brand, int warrantyDays) {
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
}
