package com.W1956114.CustomerHistory;

import java.util.ArrayList;
import java.util.List;

public class CustomerHistory {
    //creating this class to handle and save the customer's purchase history
    private final List<String> purchaseHistory;//adding all the details to this list
    private boolean firstPurchase;//first purchasing status

    public CustomerHistory() {
        this.purchaseHistory = new ArrayList<>();
        this.firstPurchase = true;
    }

    public boolean isTheFirstPurchase() {
        return firstPurchase;
    }

    //method to set the first purchase status
    public void settingFirstPurchase(boolean firstPurchase) {
        this.firstPurchase = firstPurchase;
    }

    //method to add the product to the purchase history
    public void addToPurchasedHistory(String productDetails) {
        purchaseHistory.add(productDetails);
    }
}

