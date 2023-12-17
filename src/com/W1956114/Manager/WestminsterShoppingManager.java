package com.W1956114.Manager;

import com.W1956114.Main;
import com.W1956114.SubClasses.Clothing;
import com.W1956114.SubClasses.Electronic;
import com.W1956114.Super.Product;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


public class WestminsterShoppingManager implements ShoppingManager{

    private static final ArrayList<Product> productsMainList = new ArrayList<>();


    @Override
    public void addAProduct() {
        Scanner input=new Scanner(System.in);
        String productType;
        /*to add a product the valid product ID and the name should be given as input datas*/
        do{
            if (getProductsMainList().size()<50){
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
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println();
            System.out.println("+--- Remove a existing product ---+");
            System.out.print("Enter the Product Id to delete : ");
            String productDeleteId = input.next();
            boolean deleted = false;
            for (Product product : getProductsMainList()) {
                if (product.getProductID().equals(productDeleteId)) {
                    getProductsMainList().remove(product);
                    deleted = true;
                    break;
                }
            }
            if (deleted) {
                System.out.println("Product id " + productDeleteId + " deleted successfully");
            } else {
                System.out.println("No matching product ID !");
            }
            break;
        }
    }

    @Override
    public void displayProducts() {
        System.out.println();
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.printf("%-10s%-20s%-10s%-10s%n", "ID", "Name", "Price", "Available");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        /*this will display if the product list is empty*/
        if (getProductsMainList().isEmpty()){
            System.out.println("Please Add Products to display !");
        }
        /*this will sort all the products using the last 3 numbers of the product ID*/
        for (int i = 0; i < getProductsMainList().size() - 1; i++) {
            for (int j = 0; j < getProductsMainList().size() - i - 1; j++) {
                /*getting a product*/
                Product productNum1 = getProductsMainList().get(j);
                /*getting the next product in order*/
                Product productNum2 = getProductsMainList().get(j + 1);

                /*getting the both ID's and casting it into Integer type to sort*/
                int idNum1 = Integer.parseInt(productNum1.getProductID().substring(2));
                int idNum2 = Integer.parseInt(productNum2.getProductID().substring(2));

                if (idNum1 > idNum2) {
                    // Swapping the products if they are in the wrong order
                    getProductsMainList().set(j, productNum2);
                    getProductsMainList().set(j + 1, productNum1);
                }
            }
        }
        /*printing the sorted products*/
        for (Product product : getProductsMainList()) {
            product.displayInfo();
        }
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    }

    @Override
    public void saveToAFile() {
        try {
            //creating the text file named "Product_Details.txt"
            FileWriter productWriter = new FileWriter("Product_Details.txt");
            int row_Count=1;//creating a variable to count the products
            productWriter.write("*** All the added products are mentioned below ***\n\n");
            for (Product productDetails : getProductsMainList()) {
                productWriter.write(row_Count+").\n");
                productWriter.write("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
                productWriter.write("Product Type         : " + productDetails.getProductType()+"\n");
                productWriter.write("Product ID           : " + productDetails.getProductID() + "\n");
                productWriter.write("Product Name         : " + productDetails.getProductName() + "\n");
                productWriter.write("Available Quantity   : " + productDetails.getAvailableQuantity() + "\n");
                productWriter.write("Product Unit Price   : " +"€ "+productDetails.getProductPrice() + "\n");
                if (productDetails.getProductType().equals("Electronic")) {
                    Electronic electronicProduct = (Electronic) productDetails;
                    productWriter.write("Product Brand        : " + electronicProduct.getBrand() + "\n");
                    productWriter.write("Warranty Period      : " + electronicProduct.getWarrantyDays() + " Months\n");
                } else if (productDetails.getProductType().equals("Clothing")) {
                    Clothing clothingProduct = (Clothing) productDetails;
                    productWriter.write("Product Size         : " + clothingProduct.getClothSize() + "\n");
                    productWriter.write("Product Color        : " + clothingProduct.getClothColor() + "\n");
                }
                /*if (productDetails instanceof Electronic) {//special details of Electronic products
                    Electronic electronicProduct = (Electronic) productDetails;//productDetails is cast into Electronic
                    productWriter.write("Product Brand        : " + electronicProduct.getBrand() + "\n");
                    productWriter.write("Warranty Period      : " + electronicProduct.getWarrantyDays() + " years\n");
                } else if (productDetails instanceof Clothing) {//special details of Clothing products
                    Clothing clothingProduct = (Clothing) productDetails;//productDetails is cast into Clothing
                    productWriter.write("Product Size         : " + clothingProduct.getClothSize() +"\n");
                    productWriter.write("Product Color        : " + clothingProduct.getClothColor() +"\n");
                }*/
                productWriter.write("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
                productWriter.write("\n"); // Add a blank line between products
                row_Count+=1;
            }
            productWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //if there's an error the error will display through the print statement
        }
    }
    @Override
    public void readFromAFile() {
        System.out.println();
        try {
            FileReader readProductTextFile = new FileReader("Product_Details.txt");//getting the file to read
            int charactor = readProductTextFile.read();//assigning the first character to an int type variable
            while(charactor!=-1){
                //reading the file until the last character
                System.out.print((char)charactor);//casting into char type
                charactor=readProductTextFile.read();//passing to the next character
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //if there's an error the error will display through the "sout" statement.
        }
        System.out.println();
    }
    //----------------Extra defined methods------------------//
    public int getSelectedOption(){
        Scanner inputOption=new Scanner(System.in);
        while (true){
            System.out.println();
            System.out.println("+-----------------------------------------+");
            System.out.println("|          Manager Control Panel          |");
            System.out.println("+-----------------------------------------+");
            System.out.println("[ 1 ] - Add a Product");
            System.out.println("[ 2 ] - Remove a Product");
            System.out.println("[ 3 ] - Display Products");
            System.out.println("[ 4 ] - Save To a File");
            System.out.println("[ 5 ] - Read From a File");
            System.out.println("[ 0 ] - Back to Home");
            System.out.println("+-----------------------------------------+");
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
                break;
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
                break;
        }
    }
    private void getElectronicItem(){
        Scanner input=new Scanner(System.in);
        do {
            System.out.println("+----------Enter Valid Electronic Product Details----------+");
            ArrayList<Object> productDetailsList= (ArrayList<Object>) getProductDetails();
            System.out.print("Enter the brand of the product        : ");
            String electronicProductBrand=input.next();

            System.out.print("Enter the warranty period [in months] : ");
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
            Product electronicProduct=new Electronic("Electronic",//For an electronic item
                    (String) productDetailsList.get(0),
                    (String) productDetailsList.get(1),
                    (Integer) productDetailsList.get(2),
                    (Double) productDetailsList.get(3),
                    electronicProductBrand,
                    warrantyPeriod);
            getProductsMainList().add(electronicProduct);
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
                break;
                default:
                    System.out.println("Wrong size. Try with a valid input.");
                    continue;
            }
            System.out.print("Enter the color of the cloth       :");
            String clothColor=input.next();
            Product clothingProduct=new Clothing("Clothing", // For all the clothing products
                    (String) productDetailsList.get(0),
                    (String) productDetailsList.get(1),
                    (Integer) productDetailsList.get(2),
                    (Double) productDetailsList.get(3),
                    clothSize,
                    clothColor);
            getProductsMainList().add(clothingProduct);
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

            if ("PC".equals(prefix)) {
                String digits = id.substring(2);

                if (digits.matches("\\d{3}")) {
                    return true;
                }
            }
        }

        return false;
    }
    private boolean isDuplicateID(String id) {
        for (Product productDetail : getProductsMainList()) {
            if (productDetail.getProductID() != null && productDetail.getProductID().equals(id)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Product> getProductsMainList() {
        return productsMainList;
    }
}
