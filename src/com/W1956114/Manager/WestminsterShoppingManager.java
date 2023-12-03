package com.W1956114.Manager;

import com.W1956114.Main;
import com.W1956114.SubClasses.Clothing;
import com.W1956114.SubClasses.Electronic;
import com.W1956114.Super.Product;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
            if (productsMainList.size()<50){
                while (true){
                    System.out.print("Type of the product (E/C) ? ");
                    productType=input.next();
                    break;
                }
                switch (productType){
                    case "e": case "E":
                        System.out.println("Input details for Electronic products");
                        getElectronicItem();
                        break;
                    case "c": case "C":
                        System.out.println("Input details for Clothing products");
                        getClothingItem();
                        break;
                    default:
                        System.out.println("Wrong input please enter a correct option !");
                        continue;
                }
            }else{
                System.out.println("Cannot add more products. Maximum limit reached.");
            }
            break;
        }while (true);
    }

    @Override
    public void deleteAProduct() {
        /*for (Product product : productsMainList) {
            if (product.getProductID().equals(productId)) {
                productsMainList.remove(product);
                System.out.println("Product deleted successfully.");
                return;
            }
        }*/
        System.out.println("Product with ID not found.");
    }

    @Override
    public void displayProducts() {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.printf("%-10s%-15s%-10s%-10s%n", "ID", "Name", "Price", "Available");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        for (Product product : productsMainList) {
            product.displayInfo();
        }
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    }

    @Override
    public void saveToAFile() {
        /*try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(productList);
            System.out.println("Products saved to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void readFromAFile() {
        /*try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            productList = (List<Product>) inputStream.readObject();
            System.out.println("Products read from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }
    public int getSelectedOption(){
        Scanner inputOption=new Scanner(System.in);
        while (true){
            System.out.println("+-----------------------------------------+");
            System.out.println("|          Manager Control Panel          |");
            System.out.println("+-----------------------------------------");
            System.out.println("[ 1 ] - Add a Product");
            System.out.println("[ 2 ] - Remove a Product");
            System.out.println("[ 3 ] - Display Products");
            System.out.println("[ 4 ] - Save To a File");
            System.out.println("[ 5 ] - Read From a File");
            System.out.println("[ 0 ] - Back to Home");
            System.out.println("+-----------------------------------------");
            System.out.print("Enter an Option to proceed : ");
            int selectedOption;
            while (true) {
                try {
                    selectedOption = Integer.parseInt(inputOption.nextLine());//this line will get an integer as an option
                    break;//if the input is an integer it'll break the loop from here
                } catch (NumberFormatException e) {
                    System.out.println("**** It's not an option! Please try again..");
                    System.out.println();
                    System.out.print("Enter an Option to proceed : ");
                }
            }
            if (selectedOption > 6 | selectedOption < 0) {
                System.out.println("Wrong option selected! Please try again..");
                System.out.println();
                continue;
            }
            return selectedOption;
        }
    }
    public void selectOption(){
        int passedOption = getSelectedOption();//This statement will assign the returning value from the getOption()
        //Calling the switch-case
        switch (passedOption) {
            case 0:
                Main.setOption();//Exit from the program
            case 1:
                addAProduct();
                break;
            case 2:
                deleteAProduct();
                break;
            case 3:
                displayProducts();
                break;
            case 4:
                saveToAFile();
                break;
            case 5:
                readFromAFile();
        }
    }


    private void getElectronicItem(){
        Scanner input=new Scanner(System.in);
        do {
            System.out.println("+----------Enter Valid Electronic Product Details----------+");
            ArrayList<Object> productDetailsList= (ArrayList<Object>) getProductDetails();
            System.out.print("Enter the brand of the product       : ");
            String elecronicProductBrand=input.next();

            System.out.print("Enter the warranty period [in years] : ");
            int warrantyPeriod;
            while (true){
                try{
                    warrantyPeriod= Integer.parseInt(input.next());
                    if (warrantyPeriod<=0){
                        System.out.println("Enter a positive value.");
                        System.out.print("Enter the warranty period [in years] : ");
                        continue;
                    }
                    break;
                }catch (NumberFormatException | InputMismatchException e){
                    System.out.println("*** -> Input an Integer type value !");
                    System.out.print("Enter the warranty period [in years] : ");
                }
            }
            Product electronicProduct=new Electronic((String) productDetailsList.get(0), (String) productDetailsList.get(1), (Integer) productDetailsList.get(2), (Double) productDetailsList.get(3),elecronicProductBrand,warrantyPeriod);
            productsMainList.add(electronicProduct);
            break;
        }while (true);

    }
    private void getClothingItem(){
        Scanner input=new Scanner(System.in);
        do {
            System.out.println("+----------Enter Valid Clothing Product Details----------+");
            ArrayList<Object> productDetailsList= (ArrayList<Object>) getProductDetails();
            System.out.print("Enter the size (S,M,L,XL,XXL,XXXl) : ");
            String clothSize=input.next();
            switch (clothSize){
                case "S": case "M": case "XL": case "XXL": case "XXXl":
                    System.out.println("Selected size : "+clothSize);
                break;
                default:
                    System.out.println("Wrong size. Try with a valid input.");
                    continue;
            }
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
            int availableQuantity;
            while (true){
                try {
                    availableQuantity= Integer.parseInt(input.next());
                    if (availableQuantity<=0){
                        System.out.println("Enter a positive value !");
                        System.out.print("Enter available quantity : ");
                        continue;
                    }
                    break;
                }catch (NumberFormatException | InputMismatchException e){
                    System.out.println("*** -> Input an Integer type value !");
                    System.out.print("Enter available quantity : ");
                }
            }

            System.out.print("Enter unit price         : ");
            double unitPrice;
            while (true){
                try{
                    unitPrice= Double.parseDouble(input.next());
                    if (unitPrice<=0){
                        System.out.println("Enter a positive value !");
                        System.out.print("Enter unit price         : ");
                        continue;
                    }
                    break;
                }catch (NumberFormatException | InputMismatchException e){
                    System.out.println("*** -> Input an Double type value !");
                    System.out.print("Enter unit price         : ");
                }
            }

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
        if (id.length() == 5) {
            String prefix = id.substring(0, 2);

            if ("EP".equals(prefix) || "CP".equals(prefix)) {
                String digits = id.substring(2);

                if (digits.matches("\\d{3}")) {
                    return true;
                }
            }
        }

        return false;
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
