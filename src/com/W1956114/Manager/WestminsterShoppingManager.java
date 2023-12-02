package com.W1956114.Manager;

import com.W1956114.Super.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class WestminsterShoppingManager extends JFrame implements ShoppingManager{
    private JLabel labelText;
    public WestminsterShoppingManager(){
        setSize(600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        labelText=new JLabel("Westminster Shopping System");
        labelText.setFont(new Font("",2,30));
        labelText.setHorizontalAlignment(SwingConstants.CENTER);
        add("North",labelText);
    }
    private final ArrayList<Product> productsMainList = new ArrayList<>();
    @Override
    public void addAProduct(Product product) {
        Scanner input=new Scanner(System.in);
        /*to add a product the valid product ID and the name should be given as input datas*/
        while (true){
            try{
                System.out.println("Type of the product (E/C) ? ");
                String productType=input.nextLine();
            }catch (InputMismatchException e){
                e.printStackTrace();
            }
            break;
        }
        /*if (productsMainList.size() < 50) {
            productsMainList.add(product);
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Cannot add more products. Maximum limit reached.");
        }*/
    }

    @Override
    public void deleteAProduct(String productId) {
        for (Product product : productsMainList) {
            if (product.getProductID().equals(productId)) {
                productsMainList.remove(product);
                System.out.println("Product deleted successfully.");
                return;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
    }

    @Override
    public void displayAProducts() {
        for (Product product : productsMainList) {
            product.displayInfo();
            System.out.println("------------------------------");
        }
    }

    @Override
    public void saveToAFile(String fileName) {
        /*try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(productList);
            System.out.println("Products saved to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void readFromAFile(String fileName) {
        /*try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            productList = (List<Product>) inputStream.readObject();
            System.out.println("Products read from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}
