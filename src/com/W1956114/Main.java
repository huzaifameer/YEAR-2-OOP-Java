package com.W1956114;

import com.W1956114.Manager.WestminsterShoppingManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        /*Main User Interface in the beginning*/
        JFrame mainDisplay=new JFrame("Welcome Home");
        mainDisplay.setSize(500,300);/*setting a size to the UI*/

        headingLabel=new JLabel("Welcome to the Shopping System");
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setFont(new Font("",2,30));
        subHeadingLabel=new JLabel("Select an option to proceed");
        subHeadingLabel.setFont(new Font("",2,20));
        JPanel panelTop=new JPanel(new GridLayout(2,1));
        panelTop.add(headingLabel);
        panelTop.add(subHeadingLabel);
        mainDisplay.add("North",panelTop);

        JPanel panelButton=new JPanel(new GridLayout(1,2));
        userButton=new JButton("User Login");
        userButton.setFont(new Font("",1,23));
        managerButton=new JButton("Manager Login");
        managerButton.setFont(new Font("",1,23));
        panelButton.add(userButton);
        panelButton.add(managerButton);
        mainDisplay.add("Center",panelButton);

        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WestminsterShoppingManager westminsterShoppingManager=new WestminsterShoppingManager();
                westminsterShoppingManager.displayHomeWindow();
            }
        });

        mainDisplay.setVisible(true);
    }
}
