package com.W1956114.Manager;

import com.W1956114.Super.Product;

public interface ShoppingManager{
    void addAProduct();

    void deleteAProduct(String productId);

    void displayProducts();

    void saveToAFile(String fileName);

    void readFromAFile(String fileName);
}
