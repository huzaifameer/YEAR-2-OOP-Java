package com.W1956114;

import com.W1956114.Manager.WestminsterShoppingManager;
import com.W1956114.User.ShoppingCart;
import com.W1956114.User.User;
import java.util.ArrayList;
import java.util.Scanner;


public class Main{
    private static final ArrayList<User> commonUserList=new ArrayList<>();

    public static void main(String[] args) {
        while (true){
            setOption();
        }
    }

    private static int getMainOption(){
        do {
            System.out.println("----------------------------------------------------------");
            System.out.println("xxxxxxxx- Welcome to Westminster Shopping System -xxxxxxxx");
            System.out.println("----------------------------------------------------------");
            System.out.println("[ 1 ] -> Manager Control Panel");
            System.out.println("[ 2 ] -> User Shopping Area");
            System.out.println("[ 0 ] -> Exit The System");
            System.out.println("----------------------------------------------------------");
            Scanner inputMainOption=new Scanner(System.in);
            System.out.print("Enter an Option : ");
            int option;
            option = getOption(inputMainOption);
            return option;
        } while (true);
    }
    public static void setOption() {
        int option = getMainOption();//This statement will assign the returning value from the getOption()
        //Calling the switch-case
        switch (option) {
            case 0:
                System.exit(0);//Exit from the program
                break;
            case 1:
                WestminsterShoppingManager westminsterShoppingManager=new WestminsterShoppingManager();
                if (loginManager()){
                    while (true){
                        westminsterShoppingManager.selectOption();
                    }
                }
                break;
            case 2:
                if (loginCustomer()){
                    ShoppingCart shoppingCart=new ShoppingCart();
                    System.out.println("User Panel");
                }
                System.out.println("Finished !");
                break;
            default:
                setOption();
                break;
        }
    }
    private static boolean loginManager(){
        Scanner inputData=new Scanner(System.in);
        System.out.println("x------Enter Valid Manager Credentials------x");
        User manager1 = new User("Manager-1", "12345");
        commonUserList.add(manager1);
        User manager2 = new User("Manager-2", "12345");
        commonUserList.add(manager2);
        System.out.print("Input Manager Username : ");
        String managerUserName=inputData.next();
        System.out.print("Input The Password     : ");
        String managerPassword=inputData.next();
        for (User manager:commonUserList){
            if (manager.getUserName().equals(managerUserName) && manager.getPassword().equals(managerPassword)){
                return true;
            }
        }
        System.out.println("You have entered wrong information ! Please try again !\n");
        return false;
    }
    private static boolean loginCustomer(){
        Scanner input=new Scanner(System.in);
        System.out.println("Login to Shop             - [1]");
        System.out.println("Create an Account to shop - [2]");
        System.out.print("Select an Option : ");
        int option;
        boolean validateOption;
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
                for (User customer:commonUserList){
                    if (customer.getUserName().equals(customerUsername) && customer.getPassword().equals(customerPassword)){
                        validateOption= true;
                    }
                }
                validateOption= false;
                break;
            case 2:
                System.out.println("+----- Create your user account here -----+");
                System.out.print("Enter a username : ");
                String newUsername=input.next();
                System.out.print("Enter a password : ");
                String newPassword=input.next();
                User newUser=new User(newUsername,newPassword);
                commonUserList.add(newUser);
                validateOption= true;
                break;
            default:
                System.out.println("You have entered wrong information ! Please try again !\n");
                validateOption= false;
                break;
        }
        return validateOption;
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

}
