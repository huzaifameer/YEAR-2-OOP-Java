package com.W1956114.User;

import com.W1956114.CustomerHistory.CustomerHistory;

// This class is used to save the manager,customer details additionally this is used to handle and save customers purchasing history too

public class User {
    /*details to get the user purchase history start*/
    private static int nextUser=1;//variable to get the next user
    private int userID;//setting an ID to the user
    private CustomerHistory customerPurchaseHistory;//saves the purchase history
    /*details to get the user purchase history start*/
    private String userName;//saves the name of the user
    private String password;//saves the password

    public User() {
    }
    //including all the purchasing history details in the constructor
    public User(String userName, String password) {
        userID=nextUser++;// this will increase the user id by 1
        this.userName = userName;
        this.password = password;
        this.customerPurchaseHistory=new CustomerHistory();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //method to get the purchasing history
    public CustomerHistory getCustomerHistory() {
        return customerPurchaseHistory;
    }
}
