package com.W1956114;

import com.W1956114.Manager.WestminsterShoppingManager;
import com.W1956114.User.ShoppingCart;
import com.W1956114.User.User;
import java.util.ArrayList;
import java.util.Scanner;


public class Main{
    private static User currentUser;//saving the current user
    private static final ArrayList<User> commonUserList=new ArrayList<>();//to store the data of the customers
    private static final ArrayList<User> managerUserList=new ArrayList<>();//to store the data of the managers
    static {
        // Adding manager's data (username and password) for the list
        User manager1 = new User("Manager-1", "12345");
        managerUserList.add(manager1);//adding manager 1 to the list
        User manager2 = new User("Manager-2", "12345");
        managerUserList.add(manager2);//adding manager 2 to the list
    }

    public static void main(String[] args) {
        while (true){//uses a while loop to loop infinitely
            setOption();//calling up the main selection method to start the process
        }
    }

    //The method to display the main selection panel
    private static int getMainOption(){
        do {
            //printing the main display in the console
            System.out.println("----------------------------------------------------------");
            System.out.println("xxxxxxxx- Welcome to Westminster Shopping System -xxxxxxxx");
            System.out.println("----------------------------------------------------------");
            System.out.println("[ 1 ] -> Manager Control Panel");
            System.out.println("[ 2 ] -> User Shopping Area");
            System.out.println("[ 0 ] -> Exit The System");
            System.out.println("----------------------------------------------------------");
            Scanner inputMainOption=new Scanner(System.in);
            System.out.print("Enter an Option : "); //getting an option
            int option;
            option = getOption(inputMainOption);//passing the input value to another method to validate it
            return option;//returns the selected option
        } while (true);
    }

    //method to get the above option
    public static void setOption() {
        int option = getMainOption();//This statement will assign the returning value from the getOption()
        //Calling the switch-case to direct the system user
        switch (option) {
            case 0:
                System.exit(0);//option to exit from the program
                break;
            case 1:
                WestminsterShoppingManager westminsterShoppingManager=new WestminsterShoppingManager();

                if (loginManager()){
                    //checking the availability of the manager in the system
                    while (true) {
                        westminsterShoppingManager.selectOption();
                    }
                }
                break;
            case 2:
                if (loginCustomer()){//checking the customers availability in the list
                    System.out.println("User Logged In to the System Successfully !");
                    new ShoppingCart();//opens the GUI
                    setOption();
                    break;
                }
                System.out.println("Wrong User Login Details ! Please try again !");
                setOption();
                break;
            default:
                System.out.println("Try with the correct Option !");//error message
                break;
        }
    }

    //method to validate the manager
    private static boolean loginManager(){
        Scanner inputData=new Scanner(System.in);
        //getting valid manager credentials
        System.out.println("x------Enter Valid Manager Credentials------x");
        System.out.print("Input Manager Username : ");
        String managerUserName=inputData.next();
        System.out.print("Input The Password     : ");
        String managerPassword=inputData.next();
        for (User manager:managerUserList){//checking the manager list
            if (manager.getUserName().equals(managerUserName) && manager.getPassword().equals(managerPassword)){
                return true;
            }
        }
        System.out.println("You have entered wrong information ! Please try again !\n");//error message
        return false;
    }

    //method to validate the customer
    private static boolean loginCustomer(){
        Scanner input=new Scanner(System.in);
        //getting an valid option
        System.out.println("Login to Shop             - [1]");
        System.out.println("Create an Account to shop - [2]");
        System.out.print("Select an Option : ");
        int option;

        do {
            option = getOption(input);
            break;
        }while (true);
        switch (option){
            case 1:
                System.out.print("Customer Username    : ");
                String customerUsername=input.next();
                System.out.print("Enter the Password : ");
                String customerPassword=input.next();

                for (User customer : commonUserList) {
                    if (customer.getUserName().equals(customerUsername) && customer.getPassword().equals(customerPassword)) {
                        currentUser = customer; // Set the current user to the variable
                        return true;
                    }
                }
                break;
            case 2:
                //This part will add a new customer to the system
                System.out.println("+----- Create your user account here -----+");
                System.out.print("Enter a username : ");
                String newUsername=input.next();
                System.out.print("Enter a password : ");
                String newPassword=input.next();
                User newUser=new User(newUsername,newPassword);//sending through the User constructor
                commonUserList.add(newUser);//adding the new customer to the list
                currentUser = newUser;
                return true;
            default:
                System.out.println("You have entered wrong information ! Please try again !\n");//error message
                setOption();
                return false;
        }
        return false;
    }
    private static int getOption(Scanner input) {
        /*method to get a valid input in a specific range*/
        /*reducing the code [stopping duplicating code lines]*/
        int option;
        while (true) {
            try {
                option = Integer.parseInt(input.nextLine());//this line will get an integer as an option
                break;//if the input is an integer it'll break the loop from here
            } catch (NumberFormatException e) {
                System.out.println("**** It's not an option! Please try again..");
                System.out.println();
                System.out.print("Enter an Option : ");
            }
        }
        if (option > 3 | option < 0) {
            System.out.println("Wrong option selected! Please try again..");
            System.out.println();
            /*continue;*/
        }
        return option;
    }
    // Implement getCurrentUser() method
    public static User getCurrentUser() {
        return currentUser;
    }

}
