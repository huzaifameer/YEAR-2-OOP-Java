package com.W1956114;

import com.W1956114.Manager.WestminsterShoppingManager;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private static JLabel headingLabel;
    private static JLabel subHeadingLabel;
    private static JButton userButton;
    private static JButton managerButton;
    public static void main(String[] args) {
        setMainDisplay();
        /*WestminsterShoppingManager westminsterShoppingManager=new WestminsterShoppingManager();*/
    }
    private static void setMainDisplay(){
        JFrame mainDisplay=new JFrame("Welcome Home");
        mainDisplay.setSize(500,300);

        headingLabel=new JLabel("Welcome to the Shopping System");
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setFont(new Font("",2,30));
        subHeadingLabel=new JLabel("Select an option");
        subHeadingLabel.setFont(new Font("",2,20));
        JPanel panelTop=new JPanel(new GridLayout(2,1));
        panelTop.add(headingLabel);
        panelTop.add(subHeadingLabel);
        mainDisplay.add("North",panelTop);


        mainDisplay.setVisible(true);
    }
}
