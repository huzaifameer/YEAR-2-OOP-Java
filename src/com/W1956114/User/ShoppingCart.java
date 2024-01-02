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
    private final JTable productDataTable;
    private JButton addToCart;
    private static TableRowSorter<DefaultTableModel> tableModelSorter;
    private static DefaultTableModel shoppingCartModel;
    private final JLabel label1;
    private final JLabel label2;
    private final JLabel label3;
    private final JLabel label4;
    private final JLabel label5;
    private final JLabel label6;
    private final JLabel label7;
    private final JLabel dataTypeLabel=new JLabel("");
    private final JLabel dataIDLabel=new JLabel("");
    private final JLabel dataNameLabel=new JLabel("");
    private final JLabel dataExtraLabel1=new JLabel("");
    private final JLabel dataExtraLabel2=new JLabel("");
    private final JLabel dataPriceLabel=new JLabel("");

    /*------------------------------------------------------------*/
    //private final ArrayList<Product> productsCartList = new ArrayList<>();
    public ShoppingCart(){
        JFrame westminsterShoppingCenter=new JFrame();
        westminsterShoppingCenter.setSize(500,650);

        westminsterShoppingCenter.setTitle("Westminster Shopping Center");
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
        JLabel gapLabel1=new JLabel();
        panelTop.add(gapLabel1);
        shoppingCartButton.setSize(200,40);
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Opening the UI for the shopping cart
                openShoppingCartPanel();
            }
        });
        panelTop.add(shoppingCartButton);

        westminsterShoppingCenter.add("North",panelTop);
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

        tableModelSorter = new TableRowSorter<>(productTableModel);
        productDataTable.setRowSorter(tableModelSorter);

        westminsterShoppingCenter.add("Center", panelCenter);
        //------------------------------------------------//
        JPanel panelBottom=new JPanel(new GridLayout(10,1));
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
        productDataTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displaySelectedRowDetails();
            }
        });
        JLabel gapLabel2=new JLabel();
        panelBottom.add(gapLabel2);
        JPanel panelBottomButton=new JPanel(new GridLayout(1,3));
        JLabel leftLabel=new JLabel();
        panelBottomButton.add(leftLabel);
        addToCart=new JButton("Add to Shopping Cart");
        addToCart.setMinimumSize(new Dimension(150, 30));
        panelBottomButton.add(addToCart);
        JLabel rightLabel=new JLabel();
        panelBottomButton.add(rightLabel);
        //addToCart.setSize(100,50);

        panelBottom.add("South",panelBottomButton);
        westminsterShoppingCenter.add("South",panelBottom);

        westminsterShoppingCenter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        westminsterShoppingCenter.setVisible(true);
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
                    dataIDLabel.setText(product.getProductID());
                    label2.setText("Product Type : "+product.getProductType());
                    dataTypeLabel.setText(product.getProductType());
                    label3.setText("Product Name : "+product.getProductName());
                    dataNameLabel.setText(product.getProductName());
                    label4.setText("Product Price : $ "+product.getProductPrice());
                    dataPriceLabel.setText(product.getProductPrice()+"");
                    label5.setText("Available Quantity : "+product.getAvailableQuantity());
                    if (product.getProductType().equals("Electronic")){
                        Electronic electronic=(Electronic)product;
                        label6.setText("Product Brand : "+electronic.getBrand());
                        dataExtraLabel1.setText(electronic.getBrand());
                        label7.setText("Warranty Period : "+electronic.getWarrantyDays()+" Months");
                        dataExtraLabel2.setText(electronic.getWarrantyDays()+" Months");
                    }else{
                        Clothing clothing=(Clothing)product;
                        label6.setText("Product Size : "+clothing.getClothSize());
                        dataExtraLabel1.setText(clothing.getClothSize());
                        label7.setText("Product Color : "+clothing.getClothColor());
                        dataExtraLabel2.setText(clothing.getClothColor());
                    }
                }
            }
        }
    }
    private void filteringTheTable(String selectedProductType) {
        RowFilter<DefaultTableModel, Object> rowFilter = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry <? extends DefaultTableModel, ? extends Object> entry) {
                // Customize the filtering logic based on the selected product type
                if ("All".equals(selectedProductType)) {
                    return true; // Displaying all rows when the "All" option selected
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
        tableModelSorter.setRowFilter(rowFilter);
    }
    private void openShoppingCartPanel() {
        // Creating a new JFrame for the shopping cart to display the added products
        JFrame shoppingCartUI = new JFrame("Shopping Cart");
        shoppingCartUI.setSize(500, 500);

        JPanel mainPanel=new JPanel(new GridLayout(2,1));
        shoppingCartUI.add(mainPanel);
        String[] columnData = {"Product", "Quantity", "Price"};

        shoppingCartModel=new DefaultTableModel(columnData,0);
        JTable shoppingCartTable=new JTable(shoppingCartModel);
        JScrollPane scrollPane=new JScrollPane(shoppingCartTable);
        mainPanel.add(scrollPane);

        /*-------------------------------*/
        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productDetails = dataIDLabel.getText() + " " + dataNameLabel.getText()+" "+dataExtraLabel1.getText()+" "+dataExtraLabel2.getText();
                int productQuantity = 1; // Assuming you have a JTextField for quantity
                double productPrice = Double.parseDouble(dataPriceLabel.getText()); // Assuming you have a JTextField for price
                // Adding a new row to the table
                int checkExistingRowIndex = findTheProductIndex(productDetails);//checking the existing product in the table
                if (checkExistingRowIndex != -1) {
                    // adding product already exists in the cart, so update quantity and price
                    int currentProductQuantity = (int) shoppingCartModel.getValueAt(checkExistingRowIndex, 1);
                    double currentProductPrice = (double) shoppingCartModel.getValueAt(checkExistingRowIndex, 2);

                    shoppingCartModel.setValueAt(currentProductQuantity + productQuantity, checkExistingRowIndex, 1);
                    shoppingCartModel.setValueAt(currentProductPrice + productPrice, checkExistingRowIndex, 2);
                } else {
                    // if the adding product does not exist, this line will add a new row to the table
                    Object[] newData = {productDetails, productQuantity, productPrice};
                    shoppingCartModel.addRow(newData);
                }
            }
        });
        /*-------------------------------*/

        shoppingCartUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Display the products cart
        shoppingCartUI.setVisible(true);

    }
    private int findTheProductIndex(String productName) {
        for (int i = 0; i < shoppingCartModel.getRowCount(); i++) {
            if (productName.equals(shoppingCartModel.getValueAt(i, 0))) {
                return i; //if the product found this will return the index of the value
            }
        }
        return -1; // returns the value when the product not founded
    }

}
