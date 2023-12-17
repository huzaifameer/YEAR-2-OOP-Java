package com.W1956114.User;

import com.W1956114.Manager.WestminsterShoppingManager;
import com.W1956114.Super.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart extends JFrame {
    private final JComboBox<String> productTypeDropdown ;
    private JTable productDataTable;
    /*------------------------------------------------------------*/
    private final ArrayList<Product> productsCartList = new ArrayList<>();
    public ShoppingCart(){
        JFrame shoppingCart=new JFrame();
        shoppingCart.setSize(500,600);

        shoppingCart.setTitle("Westminster Shopping Center");
        //JPanel panelTop=new JPanel(new GridLayout(1,4));
        //---creating the left top label with the text
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        /*------------------------------------------------------------*/
        JLabel labelTopLeft = new JLabel("Select Product Category  ");
        panelTop.add(labelTopLeft);

        //Setting up the drop down menu with 3 items
        String[] productTypes = {"Clothing", "Electronic", "All"};

        productTypeDropdown = new JComboBox<>(productTypes);
        productTypeDropdown.setPreferredSize(new Dimension(100,30));
        productTypeDropdown.setSelectedIndex(2);
        productTypeDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected product type from the dropdown
                String selectedProductType = (String) productTypeDropdown.getSelectedItem();
                // Perform actions based on the selected product type
                System.out.println("Selected Product Type: " + selectedProductType);
            }
        });
        panelTop.add(productTypeDropdown);

        JButton shoppingCartButton = new JButton("Shopping Cart");
        JLabel gap=new JLabel();
        panelTop.add(gap);
        shoppingCartButton.setSize(200,40);
        panelTop.add(shoppingCartButton);

        shoppingCart.add("North",panelTop);
        //---------------------------------------------//
        // Center panel with JTable
        JPanel panelCenter = new JPanel(new BorderLayout());

        int horizontalPadding = 20;
        panelCenter.setBorder(BorderFactory.createEmptyBorder(0, horizontalPadding, 0, horizontalPadding));

        // Creating a sample table model with 5 columns
        DefaultTableModel productModel = new DefaultTableModel();
        // Set row height
        //productDataTable.setRowHeight(25);

        productModel.addColumn("Product ID");
        productModel.addColumn("Name");
        productModel.addColumn("Category");
        productModel.addColumn("Price ($)");
        productModel.addColumn("Info");

        // Adding some sample data
        WestminsterShoppingManager shoppingManager=new WestminsterShoppingManager();
        /*for (int i = 0; i < 43; i++) {
            productModel.addRow(new Object[]{"Data " + (i + 1), "Data " + (i + 1), "Data " + (i + 1), "Data " + (i + 1), "Data " + (i + 1)});
        }*/
        //
        for(Product data: shoppingManager.getProductsMainList()){
            productModel.addRow(new Object[]{data.getProductID(),data.getProductName(),data.getProductType(),data.getProductPrice(),data.getAvailableQuantity()});
        }

        productDataTable = new JTable(productModel);
        productDataTable.setSize(300,300);

        JTableHeader header = productDataTable.getTableHeader();
        header.setFont(new Font(header.getFont().getName(), Font.BOLD, header.getFont().getSize()));
        //panelCenter.add(Box.createRigidArea(new Dimension(20, 0)));
        //panelCenter.add(new JSeparator(SwingConstants.HORIZONTAL));
        JScrollPane scrollPane = new JScrollPane(productDataTable);
        scrollPane.setSize(300,300);

        panelCenter.add(scrollPane, BorderLayout.CENTER);

        shoppingCart.add("Center", panelCenter);
        //------------------------------------------------//
        JPanel panelBottom=new JPanel(new GridLayout(8,2));
        int topPadding = 20;
        int leftPadding = 15;
        panelBottom.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        JLabel selecetedTextLabel=new JLabel("Selected Product Details");
        panelBottom.add(selecetedTextLabel);
        shoppingCart.add("South",panelBottom);

        /*shoppingCart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        shoppingCart.setVisible(true);
    }

    public void addAProduct() {
        System.out.println("Adding a product");
    }
    /*this part should be edited later*/
    public void removeAProduct(String productID) {/*here the id should be an input value this is temp.*/
        for (Product product : productsCartList) {
            if (product.getProductName().equals(productID)) {
                productsCartList.remove(product);
                System.out.println(productID + " removed from the cart.");
                return;
            }
        }
        System.out.println(productID + " not found in the cart.");
    }

    public double calculateTheTotal() {
        double totalCost = 0;
        for (Product product : productsCartList) {
            totalCost += product.getProductPrice();
        }
        return totalCost;
    }
    public void setAnOption(){
        Scanner input=new Scanner(System.in);

    }
}
