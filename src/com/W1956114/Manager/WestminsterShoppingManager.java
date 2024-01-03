package com.W1956114.Manager;

import com.W1956114.Main;
import com.W1956114.SubClasses.Clothing;
import com.W1956114.SubClasses.Electronic;
import com.W1956114.Super.Product;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class WestminsterShoppingManager implements ShoppingManager{

    private static final ArrayList<Product> productsMainList = new ArrayList<>();//list for store the product data

    //adding a product
    @Override
    public void addAProduct() {
        Scanner input=new Scanner(System.in);
        String productType;
        /*to add a product the valid product ID and the name should be given as input data's*/
        do{
            if (getProductsMainList().size()<50){//only 50 products can be added
                while (true){
                    //getting the product type
                    System.out.print("Type of the product (E/C) ? ");
                    productType=input.next();
                    break;
                }
                //checking the product type and getting the according details
                switch (productType){
                    case "e": case "E":
                        System.out.println("Input details for Electronic products");
                        getElectronicItem();//getting  details for a electronic data
                        break;
                    case "c": case "C":
                        System.out.println("Input details for Clothing products");
                        getClothingItem();//getting details for a clothing data
                        break;
                    default:
                        //message if a wrong option given
                        System.out.println("Wrong input please enter a correct option !");
                        continue;
                }
            }else{
                //message when the maximum limit exceeded
                System.out.println("Cannot add more products. Maximum limit reached.");
            }
            break;
        }while (true);
    }

    //deleting a product
    @Override
    public void deleteAProduct() {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println();
            System.out.println("+--- Remove a existing product ---+");
            System.out.print("Enter the Product Id to delete : ");//getting the deleting products ID
            String productDeleteId = input.next();
            boolean deleted = false;//checking the deletions status
            for (Product product : getProductsMainList()) {
                if (product.getProductID().equals(productDeleteId)) {
                    getProductsMainList().remove(product);//removing the matching product from the list
                    deleted = true;
                    break;
                }
            }
            if (deleted) {
                System.out.println("Product id " + productDeleteId + " deleted successfully");//success message
            } else {
                System.out.println("No matching product ID !");
            }
            break;
        }
    }

    //displaying product details
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

    //saving products in a text file
    @Override
    public void saveToAFile() {
        try {
            //creating the text file named "Product_Details.txt"
            FileWriter productWriter = new FileWriter("Product_Details.txt");
            int row_Count=1;//creating a variable to count the products
            productWriter.write("*** All the added products are mentioned below ***\n\n");
            for (Product productDetails : getProductsMainList()) {
                //writing all the data in the text file line by line
                productWriter.write(row_Count+").\n");
                productWriter.write("Product Type         : " + productDetails.getProductType()+"\n");
                productWriter.write("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
                productWriter.write("Product ID           : " + productDetails.getProductID() + "\n");
                productWriter.write("Product Name         : " + productDetails.getProductName() + "\n");
                productWriter.write("Available Quantity   : " + productDetails.getAvailableQuantity() + "\n");
                productWriter.write("Product Unit Price   : " +"€ "+productDetails.getProductPrice() + "\n");
                //----------------------------------------------//

                //checking the type of the product and adding the additional details for the text file
                if (productDetails.getProductType().equals("Electronic")) {
                    Electronic electronicProduct = (Electronic) productDetails;
                    productWriter.write("Product Brand        : " + electronicProduct.getBrand() + "\n");
                    productWriter.write("Warranty Period      : " + electronicProduct.getWarrantyDays() + " Months\n");
                } else if (productDetails.getProductType().equals("Clothing")) {
                    Clothing clothingProduct = (Clothing) productDetails;
                    productWriter.write("Product Size         : " + clothingProduct.getClothSize() + "\n");
                    productWriter.write("Product Color        : " + clothingProduct.getClothColor() + "\n");
                }

                productWriter.write("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
                productWriter.write("\n"); // Add a blank line between products
                row_Count+=1;
            }
            productWriter.close();//closing the opened file
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //if there's an error the error will display through the print statement
        }
    }

    //reading the saved data from the text file
    @Override
    public void readFromAFile() {
        System.out.println();
        try {
            //creating the file reader variable
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

    //----------------Extra defined methods for the main methods------------------//

    //method to get the selected valid option
    public int getSelectedOption(){
        Scanner inputOption=new Scanner(System.in);
        while (true){
            //printing the manager panel
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
                    //displaying the error message
                    System.out.println("**** It's not an option! Please try again..");
                    System.out.println();
                    System.out.print("Enter an Option to proceed : ");
                }
            }
            if (selectedOption > 6 | selectedOption < 0) {
                //checking the correct range of the options
                System.out.println("Wrong option selected! Please try again..");
                System.out.println();
                continue;
            }
            return selectedOption;//returning the valid option
        }
    }

    //getting option method
    public void selectOption(){
        int passedOption = getSelectedOption();//This statement will assign the returning value from the getOption()
        //Calling the switch-case
        switch (passedOption) {
            case 0:
                Main.setOption();//Exit from the program
                break;
            case 1:
                addAProduct();//adding a product method
                break;
            case 2:
                deleteAProduct();//deleting a product method
                break;
            case 3:
                displayProducts();//displaying the sorted products method
                break;
            case 4:
                saveToAFile();//saving data to the file method
                break;
            case 5:
                readFromAFile();//reading the saved data method
                break;
        }
    }

    //method to get the extra details of an Electronic product
    private void getElectronicItem(){
        Scanner input=new Scanner(System.in);
        do {
            System.out.println("+----------Enter Valid Electronic Product Details----------+");
            //creating a Object type cause there are varius type data
            ArrayList<Object> productDetailsList= (ArrayList<Object>) getProductDetails();
            System.out.print("Enter the brand of the product        : ");
            String electronicProductBrand=input.next();

            System.out.print("Enter the warranty period [in months] : ");//getting the warranty period
            int warrantyPeriod;
            while (true){
                try{
                    warrantyPeriod= Integer.parseInt(input.next());
                    if (warrantyPeriod<=0){//checking the valid range
                        System.out.println("Enter a positive value.");
                        System.out.print("Enter the warranty period [in years] : ");
                        continue;
                    }
                    break;
                }catch (NumberFormatException | InputMismatchException e){
                    //handling the input type exception
                    //error message
                    System.out.println("*** -> Input an Integer type value !");
                    System.out.print("Enter the warranty period [in years] : ");
                }
            }
            //adding the product details through the parameterized constructor
            Product electronicProduct=new Electronic("Electronic",
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

    //method to get the extra details of a Clothing product
    private void getClothingItem(){
        Scanner input=new Scanner(System.in);
        do {
            System.out.println("+----------Enter Valid Clothing Product Details----------+");
            //creating a Object type cause there are varius type data
            ArrayList<Object> productDetailsList= (ArrayList<Object>) getProductDetails();
            System.out.print("Enter the size (S,M,L,XL,XXL,XXXl) : ");//getting the clothing product's size
            String clothSize=input.next();
            switch (clothSize){
                case "S": case "M": case "XL": case "XXL": case "XXXl":
                break;
                default:
                    System.out.println("Wrong size. Try with a valid input.");//error message
                    continue;
            }
            System.out.print("Enter the color of the cloth       :");//getting the color
            String clothColor=input.next();
            //adding the product details through the parameterized constructor
            Product clothingProduct=new Clothing("Clothing",
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

    //method to get all the common details of a product
    private Object getProductDetails(){
        Scanner input =new Scanner(System.in);

        do {
            String validProductID=getValidID();//calling the method to get the valid ID

            //getting the products details and validating
            System.out.print("Enter product name       : ");
            String productName=input.next();

            System.out.print("Enter available quantity : ");
            int availableQuantity;
            while (true){
                try {
                    availableQuantity= Integer.parseInt(input.next());
                    if (availableQuantity<=0){//range check
                        System.out.println("Enter a positive value !");//error message
                        System.out.print("Enter available quantity : ");
                        continue;
                    }
                    break;
                }catch (NumberFormatException | InputMismatchException e){
                    //handling the exception
                    System.out.println("*** -> Input an Integer type value !");
                    System.out.print("Enter available quantity : ");
                }
            }

            System.out.print("Enter unit price         : ");//getting the unit price
            double unitPrice;
            while (true){
                try{//using to handle the input and number format exception
                    unitPrice= Double.parseDouble(input.next());
                    if (unitPrice<=0){//range check
                        System.out.println("Enter a positive value !");
                        System.out.print("Enter unit price         : ");
                        continue;
                    }
                    break;
                }catch (NumberFormatException | InputMismatchException e){
                    //error message
                    System.out.println("*** -> Input an Double type value !");
                    System.out.print("Enter unit price         : ");
                }
            }
            //temp. array list to return all the details
            ArrayList<Object> tempProductList=new ArrayList<>();
            //adding all the product to the temp. list
            tempProductList.add(validProductID);
            tempProductList.add(productName);
            tempProductList.add(availableQuantity);
            tempProductList.add(unitPrice);

            return tempProductList;
        }while (true);

    }

    //method to get a valid formatted ID
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

    //method to validate a fixed ID format
    private boolean validateID(String id){
        // Two uppercase letters "PC" followed by 3 digits
        if (id.length() == 5) {
            String prefix = id.substring(0, 2);

            if ("PC".equals(prefix)) {//the starting value of the id
                String digits = id.substring(2);

                if (digits.matches("\\d{3}")) {//checking the last 3 index are numbers
                    return true;
                }
            }
        }

        return false;
    }

    //method to check the duplicate ID's
    private boolean isDuplicateID(String id) {
        //checking throughout the list whether the ID is duplicated
        for (Product productDetail : getProductsMainList()) {
            if (productDetail.getProductID() != null && productDetail.getProductID().equals(id)) {
                return true;
            }
        }
        return false;//returns a boolean as the result
    }

    //method to get the product list
    public ArrayList<Product> getProductsMainList() {
        return productsMainList;
    }
}
