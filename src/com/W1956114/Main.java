package com.W1956114;

import com.W1956114.Manager.WestminsterShoppingManager;

import java.util.Scanner;


public class Main{

    public static void main(String[] args) {
        setOption();
    }
    private static int getMainOption(){
        do {
            System.out.println("xxxxxxxx- Welcome to Westminster Shopping System -xxxxxxxx");
            System.out.println("----------------------------------------------------------");
            System.out.println("[ 1 ] -> Manager Control Panel");
            System.out.println("[ 2 ] -> User Shopping Area");
            System.out.println("[ 0 ] -> Exit The System");
            System.out.println("----------------------------------------------------------");
            Scanner inputMainOption=new Scanner(System.in);
            System.out.print("Enter an Option : ");
            int option;
            while (true) {
                try {
                    option = Integer.parseInt(inputMainOption.nextLine());//this line will get an integer as an option
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
                continue;
            }
            return option;
        } while (true);
    }
    public static void setOption() {
        int option = getMainOption();//This statement will assign the returning value from the getOption()
        //Calling the switch-case
        switch (option) {
            case 0:
                System.exit(0);//Exit from the program
            case 1:
                WestminsterShoppingManager westminsterShoppingManager=new WestminsterShoppingManager();
                westminsterShoppingManager.selectOption();
                break;
            case 2:
                System.out.println("User Panel");
                break;
        }
    }
    private static boolean loginManager(){

        return false;
    }

}
