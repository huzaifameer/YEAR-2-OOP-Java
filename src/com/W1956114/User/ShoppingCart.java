package com.W1956114.User;

import com.W1956114.Super.Product;

import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> productsCartList = new ArrayList<>();

    public void addAProduct(Product product) {
        productsCartList.add(product);
        System.out.println(product.getProductName() + " added to the cart.");
    }
    /*this part should be edited later*/
    public void removeAProduct(String productID) {/*here the id should be an input value this is temp.*/
        for (Product product : productsCartList) {
            if (product.getProductName().equals(productID)) {
                productsCartList.remove(product);
                System.out.println(productID + " removed from the cart.");
                return;
            }
        }
        System.out.println(productID + " not found in the cart.");
    }

    public double calculateTheTotal() {
        double totalCost = 0;
        for (Product product : productsCartList) {
            totalCost += product.getProductPrice();
        }
        return totalCost;
    }
}
