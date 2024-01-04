package com.W1956114.User;

import com.W1956114.CustomerHistory.CustomerHistory;

public class User {
    /*details to get the user history*/
    private static int nextUser=1;
    private int userID;
    private CustomerHistory customerHistory;
    /*details to get the user history*/
    private String userName;
    private String password;

    public User() {
    }
    public User(String userName, String password) {
        userID=nextUser++;
        this.userName = userName;
        this.password = password;
        this.customerHistory=new CustomerHistory();
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

    public int getUserId() {
        return userID;
    }
    public CustomerHistory getCustomerHistory() {
        return customerHistory;
    }
}
