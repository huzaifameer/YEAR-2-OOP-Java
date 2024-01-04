package com.W1956114.CustomerHistory;

import java.util.ArrayList;
import java.util.List;

public class CustomerHistory {
    private final List<String> purchaseHistory;
    private boolean firstPurchase;

    public CustomerHistory() {
        this.purchaseHistory = new ArrayList<>();
        this.firstPurchase = true;
    }

    public List<String> getPurchaseHistory() {
        return purchaseHistory;
    }

    public boolean isTheFirstPurchase() {
        return firstPurchase;
    }

    public void setFirstPurchase(boolean firstPurchase) {
        this.firstPurchase = firstPurchase;
    }

    public void addToPurchasedHistory(String productDetails) {
        purchaseHistory.add(productDetails);
    }
}

