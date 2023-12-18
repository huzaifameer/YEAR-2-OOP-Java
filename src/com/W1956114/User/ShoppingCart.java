package com.W1956114.User;

import com.W1956114.Manager.WestminsterShoppingManager;
import com.W1956114.SubClasses.Clothing;
import com.W1956114.SubClasses.Electronic;
import com.W1956114.Super.Product;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShoppingCart extends JFrame {
    private final JComboBox<String> productTypeDropdown ;
    private JTable productDataTable;
    private static TableRowSorter<DefaultTableModel> tableSorter;
    private JLabel label1, label2, label3, label4, label5,label6,label7;

    /*------------------------------------------------------------*/
    //private final ArrayList<Product> productsCartList = new ArrayList<>();
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

        //Setting up the drop-down menu with 3 items
        String[] productTypes = {"Clothing", "Electronic", "All"};

        productTypeDropdown = new JComboBox<>(productTypes);
        productTypeDropdown.setPreferredSize(new Dimension(100,30));
        productTypeDropdown.setSelectedIndex(2);
        productTypeDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected product type from the dropdown
                String selectedType = (String) productTypeDropdown.getSelectedItem();
                filteringTheTable(selectedType);

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
        DefaultTableModel productTableModel = new DefaultTableModel();
        // Set row height

        productTableModel.addColumn("Product ID");
        productTableModel.addColumn("Name");
        productTableModel.addColumn("Category");
        productTableModel.addColumn("Price ($)");
        productTableModel.addColumn("Info");

        // Adding some sample data
        WestminsterShoppingManager shoppingManager=new WestminsterShoppingManager();

        //setting the product's data into the table
        for(Product rowData: shoppingManager.getProductsMainList()){
            //code line to get the extra details
            String additionalDetails="";//creating and initializing a string typed variable to add the additional information
            if (rowData.getProductType().equals("Electronic")){
                Electronic electronic= (Electronic) rowData;//casting into Electronic type
                additionalDetails="Brand: "+electronic.getBrand()+" Warranty: "+electronic.getWarrantyDays();
            }else {
                Clothing clothing= (Clothing) rowData;//casting into Clothing type
                additionalDetails="Size: "+clothing.getClothSize()+" Color: "+clothing.getClothColor();
            }
            Object[] dataRowLine = {//creating an object type array to add all the values
                    rowData.getProductID(),
                    rowData.getProductName(),
                    rowData.getProductType(),
                    rowData.getProductPrice(),
                    additionalDetails
            };
            productTableModel.addRow(dataRowLine);
        }
        productDataTable = new JTable(productTableModel);
        productDataTable.setSize(300,300);

        JTableHeader header = productDataTable.getTableHeader();
        header.setFont(new Font(header.getFont().getName(), Font.BOLD, header.getFont().getSize()));
        JScrollPane scrollPane = new JScrollPane(productDataTable);
        scrollPane.setSize(300,300);

        panelCenter.add(scrollPane, BorderLayout.CENTER);

        tableSorter = new TableRowSorter<>(productTableModel);
        productDataTable.setRowSorter(tableSorter);

        shoppingCart.add("Center", panelCenter);
        //------------------------------------------------//
        JPanel panelBottom=new JPanel(new GridLayout(8,2));
        panelBottom.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        JLabel selecetedTextLabel=new JLabel("Selected Product Details");
        selecetedTextLabel.setFont(new Font("",2,15));
        panelBottom.add(selecetedTextLabel);
        label1=new JLabel(" ");
        label2=new JLabel(" ");
        label3=new JLabel(" ");
        label4=new JLabel(" ");
        label5=new JLabel(" ");
        label6=new JLabel(" ");
        label7=new JLabel(" ");

        panelBottom.add(label1);
        panelBottom.add(label2);
        panelBottom.add(label3);
        panelBottom.add(label4);
        panelBottom.add(label5);
        panelBottom.add(label6);
        panelBottom.add(label7);

        // Add ListSelectionListener to detect row selection changes
        productDataTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    displaySelectedRowDetails();
                }
            }
        });
        shoppingCart.add("South",panelBottom);

        /*shoppingCart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        shoppingCart.setVisible(true);
    }
    private void displaySelectedRowDetails() {
        int selectedRowIndex = productDataTable.getSelectedRow();
        if (selectedRowIndex != -1) {
            // Get the data from the selected row
            Object productID = productDataTable.getValueAt(selectedRowIndex, 0);
            WestminsterShoppingManager wsm=new WestminsterShoppingManager();
            for (Product product:wsm.getProductsMainList()){
                if (productID.equals(product.getProductID())){
                    label1.setText("Product ID : "+product.getProductID());
                    label2.setText("Product Type : "+product.getProductType());
                    label3.setText("Product Name : "+product.getProductName());
                    label4.setText("Product Price : $ "+product.getProductPrice());
                    label5.setText("Available Quantity : "+product.getAvailableQuantity());
                    if (product.getProductType().equals("Electronic")){
                        Electronic electronic=(Electronic)product;
                        label6.setText("Product Brand : "+electronic.getBrand());
                        label7.setText("Warranty Period : "+electronic.getWarrantyDays()+" Months");
                    }else{
                        Clothing clothing=(Clothing)product;
                        label6.setText("Product Size : "+clothing.getClothSize());
                        label7.setText("Product Color : "+clothing.getClothColor());
                    }
                }
            }
        }
    }
    private void filteringTheTable(String selectedProductType) {
        RowFilter<DefaultTableModel, Object> rowFilter = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                // Customize the filtering logic based on the selected product type
                if ("All".equals(selectedProductType)) {
                    return true; // Show all rows
                }

                String category = (String) entry.getValue(2); // Assuming category is at index 2
                if ("Electronic".equals(selectedProductType)) {
                    return "Electronic".equals(category);
                } else if ("Clothing".equals(selectedProductType)) {
                    return "Clothing".equals(category);
                } else {

                    return false;
                }
            }
        };

        // Apply the filter
        tableSorter.setRowFilter(rowFilter);
    }

}
