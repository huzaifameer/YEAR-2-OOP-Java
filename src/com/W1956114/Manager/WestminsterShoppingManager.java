package com.W1956114.Manager;

import com.W1956114.SubClasses.Clothing;
import com.W1956114.Super.Product;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class WestminsterShoppingManager implements ShoppingManager{

    private final ArrayList<Product> productsMainList = new ArrayList<>();
    @Override
    public void addAProduct() {
        Scanner input=new Scanner(System.in);
        String productType;
        /*to add a product the valid product ID and the name should be given as input datas*/
        do{
            while (true){
                System.out.print("Type of the product (E/C) ? ");
                productType=input.next();
                break;
            }
            switch (productType){
                case "e": case "E":
                    System.out.println("Input details for Electronic products");
                    getElectronicItem();
                    displayProducts();
                    break;
                case "c": case "C":
                    System.out.println("Input details for Clothing products");
                    getClothingItem();
                    displayProducts();
                    break;
                default:
                    System.out.println("Wrong input please enter a correct option !");
                    continue;
            }

            break;
        }while (true);

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
    public void displayProducts() {
        for (Product product : productsMainList) {
            System.out.println("------------------------------");
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


    private void getElectronicItem(){
        do {
            getProductDetails();
            break;
        }while (true);

    }
    private void getClothingItem(){
        Scanner input=new Scanner(System.in);
        do {
            ArrayList<Object> productDetailsList= (ArrayList<Object>) getProductDetails();
            System.out.print("Enter the size (S,M,L,XL,XXL,XXXl) : ");
            String clothSize=input.next();
            System.out.print("Enter the color of the cloth       :");
            String clothColor=input.next();
            Product clothingProduct=new Clothing((String) productDetailsList.get(0), (String) productDetailsList.get(1), (Integer) productDetailsList.get(2), (Double) productDetailsList.get(3),clothSize,clothColor);
            productsMainList.add(clothingProduct);
            break;
        }while (true);
    }
    private Object getProductDetails(){
        Scanner input =new Scanner(System.in);

        do {
            String validProductID=getValidID();

            System.out.print("Enter product name       : ");
            String productName=input.next();

            System.out.print("Enter available quantity : ");
            int availableQuantity= input.nextInt();

            System.out.print("Enter unit price         : ");
            double unitPrice= input.nextDouble();
            ArrayList<Object> tempProductList=new ArrayList<>();
            tempProductList.add(validProductID);
            tempProductList.add(productName);
            tempProductList.add(availableQuantity);
            tempProductList.add(unitPrice);
            /*List<Object> tempProductList = new ArrayList<>(List.of(validProductID, productName, availableQuantity, unitPrice));*/
            return tempProductList;
        }while (true);

    }
    private String getValidID(){
        Scanner inputID=new Scanner(System.in);
        do {
            System.out.print("Enter an ID: ");
            String productID = inputID.nextLine();

            // Validate the entered ID
            if (!validateID(productID)) {
                System.out.println("Invalid ID! Please try again !");
                continue;
            } else if (isDuplicateID(productID)) {
                System.out.println("**. Product ID already exists.Try another ID number.");
                continue;
            }else {
                System.out.println("** Product ID has been validated successfully. **");
            }
            return productID;
        }while (true);
    }
    private boolean validateID(String id){
        // Two uppercase letters "PC" followed by 3 digits
        return id.matches("^[P,C]{2}\\d{3}$");
    }
    private boolean isDuplicateID(String id) {
        for (Product productDetail :productsMainList) {
            if (productDetail.getProductID() != null && productDetail.getProductID().equals(id)) {
                return true;
            }
        }
        return false;
    }

}
