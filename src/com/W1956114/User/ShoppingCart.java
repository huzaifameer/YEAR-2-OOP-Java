package com.W1956114.User;

import com.W1956114.CustomerHistory.CustomerHistory;
import com.W1956114.Main;
import com.W1956114.Manager.WestminsterShoppingManager;
import com.W1956114.SubClasses.Clothing;
import com.W1956114.SubClasses.Electronic;
import com.W1956114.Super.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShoppingCart extends JFrame {
    private final JComboBox<String> productTypeDropdown ;//drop down menu to filter the products by the types
    private final JTable productDataTable;//product table (with all the data)
    private JButton addToCart;//creating the add to cart button
    private static TableRowSorter<DefaultTableModel> tableModelSorter; //table model sorter to display the table
    private DefaultTableModel shoppingCartModel;// default table model for the shopping cart table
    /*creating the list of labels to display the product's details start */
    private final JLabel label1;
    private final JLabel label2;
    private final JLabel label3;
    private final JLabel label4;
    private final JLabel label5;
    private final JLabel label6;
    private final JLabel label7;
    /*creating the list of labels to display the product's details start */

    /*creating the list of labels for the shopping cart start*/
    private final JLabel dataTypeLabel=new JLabel("");
    private final JLabel dataIDLabel=new JLabel("");
    private final JLabel dataNameLabel=new JLabel("");
    private final JLabel dataExtraLabel1=new JLabel("");
    private final JLabel dataExtraLabel2=new JLabel("");
    private final JLabel dataPriceLabel=new JLabel("");
    private final JLabel totalPriceLabel=new JLabel();
    private final JLabel firstBuyDiscount=new JLabel();
    private final JLabel sameCategoryDiscountLabel=new JLabel();
    private final JLabel finalTotalLabel=new JLabel();
    /*creating the list of labels for the shopping cart ending*/

    /*------------------------------------------------------------*/
    //This will open the shopping cart GUI
    public ShoppingCart(){
        JFrame westminsterShoppingCenter=new JFrame();
        westminsterShoppingCenter.setSize(500,650);

        westminsterShoppingCenter.setTitle("Westminster Shopping Center");//setting up the title for the GUI

        //---creating the left top label with the text
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        /*------------------------------------------------------------*/
        JLabel labelTopLeft = new JLabel("Select Product Category  ");
        panelTop.add(labelTopLeft);//adding the label to the panel

        //Setting up the drop-down menu with 3 items
        String[] productTypes = {"Clothing", "Electronic", "All"};//creating an array for store the types

        productTypeDropdown = new JComboBox<>(productTypes);//creating the combo box
        productTypeDropdown.setPreferredSize(new Dimension(100,30));//setting a size for the box
        productTypeDropdown.setSelectedIndex(2);//default selection
        productTypeDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected product type from the dropdown
                String selectedType = (String) productTypeDropdown.getSelectedItem();
                filteringTheTable(selectedType);//sending the selected method to filter the table
            }
        });
        panelTop.add(productTypeDropdown);//adding the drop-down menu

        JButton shoppingCartButton = new JButton("Shopping Cart");
        JLabel gapLabel1=new JLabel();
        panelTop.add(gapLabel1);
        shoppingCartButton.setSize(200,40);
        //creating an action listener for the button Shopping cart
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Opening the UI for the shopping cart and setting up default values for the new shopping cart
                totalPriceLabel.setText("");
                firstBuyDiscount.setText("");
                sameCategoryDiscountLabel.setText("");
                finalTotalLabel.setText("");
                openShoppingCartPanel();//method to open up the GUI for the shopping cart
            }
        });
        panelTop.add(shoppingCartButton);//adding the shopping cart button to the panel

        westminsterShoppingCenter.add("North",panelTop); //adding the top panel into the north direction
        //---------------------------------------------//
        // Center panel with JTable
        JPanel panelCenter = new JPanel(new BorderLayout());//using the border layout

        int horizontalPadding = 20;//adding a padding horizontally through a variable
        panelCenter.setBorder(BorderFactory.createEmptyBorder(0, horizontalPadding, 0, horizontalPadding));

        // Creating a table model with 5 columns to include products and its details
        DefaultTableModel productTableModel = new DefaultTableModel();
        //naming the columns in the table
        productTableModel.addColumn("Product ID");
        productTableModel.addColumn("Name");
        productTableModel.addColumn("Category");
        productTableModel.addColumn("Price ($)");
        productTableModel.addColumn("Info");

        WestminsterShoppingManager shoppingManager=new WestminsterShoppingManager();//creating to get the product list from the class
        productDataTable = new JTable(productTableModel);

        //setting the product's data into the table using a for each loop
        for(Product rowData: shoppingManager.getProductsMainList()){
            //code line to get the extra details by using conditions
            String additionalDetails="";//creating and initializing a string typed variable to add the additional information
            if (rowData.getProductType().equals("Electronic")){//condition to electronic type
                Electronic electronic= (Electronic) rowData;//casting into Electronic type
                additionalDetails="Brand: "+electronic.getBrand()+" Warranty: "+electronic.getWarrantyDays();
            }else {
                //when the type is clothing
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
            productTableModel.addRow(dataRowLine);//adding the row with the data to the table
        }
        productDataTable.setSize(300,300);//setting a size for the table

        JTableHeader header = productDataTable.getTableHeader();//header for the tables column
        header.setFont(new Font(header.getFont().getName(), Font.BOLD, header.getFont().getSize()));//customizing the header
        JScrollPane scrollPane = new JScrollPane(productDataTable);//scroll pane for the table
        scrollPane.setSize(300,300);//setting it same as the tables size


        panelCenter.add(scrollPane, BorderLayout.CENTER);//adding the table into the center

        tableModelSorter = new TableRowSorter<>(productTableModel);//model to sort the table
        productDataTable.setRowSorter(tableModelSorter);

        westminsterShoppingCenter.add("Center", panelCenter);//adding to the main display in the center
        //------------------------------------------------//
        JPanel panelBottom=new JPanel(new GridLayout(10,1));//grid layout display for the bottom panel
        panelBottom.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        JLabel selecetedTextLabel=new JLabel("Selected Product Details");//setting a heading to the label
        selecetedTextLabel.setFont(new Font("",2,15));
        panelBottom.add(selecetedTextLabel);
        //adding default values for the labels
        label1=new JLabel(" ");
        label2=new JLabel(" ");
        label3=new JLabel(" ");
        label4=new JLabel(" ");
        label5=new JLabel(" ");
        label6=new JLabel(" ");
        label7=new JLabel(" ");
        //adding all the labels to the panel
        panelBottom.add(label1);
        panelBottom.add(label2);
        panelBottom.add(label3);
        panelBottom.add(label4);
        panelBottom.add(label5);
        panelBottom.add(label6);
        panelBottom.add(label7);

        // Adding a ListSelectionListener to detect row selection changes
        productDataTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displaySelectedRowDetails();
            }
        });
        JLabel gapLabel2=new JLabel();
        panelBottom.add(gapLabel2);//empty valued label to create a gap

        JPanel panelBottomButton=new JPanel(new GridLayout(1,3));//panel for the add product button

        JLabel leftLabel=new JLabel();
        panelBottomButton.add(leftLabel);

        addToCart=new JButton("Add to Shopping Cart");//setting the text
        addToCart.setMinimumSize(new Dimension(150, 30));//setting a size to the button
        panelBottomButton.add(addToCart);//adding the button to the panel

        JLabel rightLabel=new JLabel();
        panelBottomButton.add(rightLabel);//adding a label to the right

        panelBottom.add("South",panelBottomButton);//adding to the south direction
        westminsterShoppingCenter.add("South",panelBottom);

        westminsterShoppingCenter.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//this will hide the GUI when it closed
        westminsterShoppingCenter.setVisible(true);//this will display the GUI
    }
    //method to display the selected rows data
    private void displaySelectedRowDetails() {
        int selectedRowIndex = productDataTable.getSelectedRow();//getting the selected rows index by passing the value to another method
        if (selectedRowIndex != -1) {
            // Get the data from the selected row
            Object productID = productDataTable.getValueAt(selectedRowIndex, 0);//getting the ID
            WestminsterShoppingManager wsm=new WestminsterShoppingManager();//creating an object to access the product list
            for (Product product:wsm.getProductsMainList()){
                if (productID.equals(product.getProductID())){
                    //setting the value for the label of the products table
                    //setting the value for the data label of the shopping cart label
                    label1.setText("Product ID : "+product.getProductID());
                    dataIDLabel.setText(product.getProductID());

                    label2.setText("Product Type : "+product.getProductType());
                    dataTypeLabel.setText(product.getProductType());

                    label3.setText("Product Name : "+product.getProductName());
                    dataNameLabel.setText(product.getProductName());

                    label4.setText("Product Price : $ "+product.getProductPrice());
                    dataPriceLabel.setText(product.getProductPrice()+"");

                    label5.setText("Available Quantity : "+product.getAvailableQuantity());
                    //finding out the type of the product and setting the values for the label (extra data)
                    if (product.getProductType().equals("Electronic")){
                        Electronic electronic=(Electronic)product;
                        label6.setText("Product Brand : "+electronic.getBrand());
                        dataExtraLabel1.setText(electronic.getBrand());

                        label7.setText("Warranty Period : "+electronic.getWarrantyDays()+" Months");
                        dataExtraLabel2.setText(electronic.getWarrantyDays()+" Months");
                    }else{
                        Clothing clothing=(Clothing)product;
                        label6.setText("Product Size : "+clothing.getClothSize());
                        dataExtraLabel1.setText(clothing.getClothSize());//setting the size

                        label7.setText("Product Color : "+clothing.getClothColor());//setting the color
                        dataExtraLabel2.setText(clothing.getClothColor());
                    }
                }
            }
        }
    }

    //method to filter the tables rows by the drop-down
    private void filteringTheTable(String selectedProductType) {
        // Creating a RowFilter to customize how the rows are being filtered in the table
        RowFilter<DefaultTableModel, Object> rowFilter = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry <? extends DefaultTableModel, ? extends Object> entry) {
                // Customizing the filtering process by the selected product type
                if ("All".equals(selectedProductType)) {
                    return true; // Displaying all rows when the "All" option selected
                }
                String category = (String) entry.getValue(2); // Assuming category is at index 2 and assigning it to a string typed variable
                //checking the selected product type from the dropdown menu
                if ("Electronic".equals(selectedProductType)) {
                    return "Electronic".equals(category);//returns the type as Electronic
                } else if ("Clothing".equals(selectedProductType)) {
                    return "Clothing".equals(category);
                } else {
                    return false;// Returns false for invalid cases (not "All", "Electronic","Clothing")
                }
            }
        };

        tableModelSorter.setRowFilter(rowFilter);// Applying the created RowFilter to the TableRowSorter associated with the table
    }

    //this method will open the shopping cart
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
            WestminsterShoppingManager wsm2=new WestminsterShoppingManager();
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shoppingCartUI.isVisible()){
                    String productDetails = dataIDLabel.getText() + " " + dataNameLabel.getText() + " " + dataExtraLabel1.getText() + " " + dataExtraLabel2.getText();
                    int productQuantity = 1; // Assuming you have a JTextField for quantity
                    //double productPrice = Double.parseDouble(dataPriceLabel.getText()); // Assuming you have a JTextField for price
                    double productPrice;
                    try {
                        productPrice = Double.parseDouble(dataPriceLabel.getText());
                        // getting the value from the table and casting it into a double
                    } catch (NumberFormatException error) {
                        // Handling the exception
                        JOptionPane.showMessageDialog(null, "No Products Selected !");
                        // displaying a message
                        return; //returning back
                    }
                    // Adding a new row to the table
                    int checkExistingRowIndex = findTheProductIndex(productDetails);//checking the product already is in the table

                    String productID = dataIDLabel.getText();//getting the products ID
                    // Checking whether the product exists in the system
                    Product selectedProduct = null;
                    for (Product prod : wsm2.getProductsMainList()) {
                        if (productID.equals(prod.getProductID())) {
                            selectedProduct = prod;//assigning the product object
                        }
                    }


                    if (checkExistingRowIndex != -1 && selectedProduct != null && selectedProduct.getAvailableQuantity() > 0) {
                        // This part will run when the product exists, and it'll update the price and the quantity
                        //get's the current values in the labels and the system
                        int currentProductQuantity = (int) shoppingCartModel.getValueAt(checkExistingRowIndex, 1);
                        double currentProductPrice = (double) shoppingCartModel.getValueAt(checkExistingRowIndex, 2);

                        // Check if available quantity is sufficient in the system
                        if (selectedProduct.getAvailableQuantity() >= 1) {
                            shoppingCartModel.setValueAt(currentProductQuantity + productQuantity, checkExistingRowIndex, 1);
                            shoppingCartModel.setValueAt(currentProductPrice + productPrice, checkExistingRowIndex, 2);

                            // Updates the available quantity in the system
                            selectedProduct.setAvailableQuantity(selectedProduct.getAvailableQuantity() - productQuantity);

                            // Update the label in the UI
                            label5.setText("Available Quantity : " + selectedProduct.getAvailableQuantity());
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient available quantity in the system.");
                        }
                    } else {
                        // when the Product does not exist in the cart, this line will add a new row to the table
                        if (selectedProduct != null && selectedProduct.getAvailableQuantity() >= 1) {
                            Object[] newData = {productDetails, productQuantity, productPrice};
                            shoppingCartModel.addRow(newData);

                            // Updating the available quantity in the system
                            selectedProduct.setAvailableQuantity(selectedProduct.getAvailableQuantity() - productQuantity);

                            // Updating the label in the GUI
                            label5.setText("Available Quantity : " + selectedProduct.getAvailableQuantity());
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient available quantity in the system.");
                        }
                    }
                    //this Calculates total value and update the label
                    double totalValue = calculateTotalPrice();
                    totalPriceLabel.setText("Total : $" + totalValue);

                    // Add the purchased product details to the customer's purchase history
                    CustomerHistory customer = new CustomerHistory();
                    customer.addToPurchaseHistory(productDetails);

                    // Check if it's the customer's first purchase and apply a 10% discount from the total value
                    double firstPurchasedDiscount = 0;
                    if (customer.isFirstPurchase()) {
                        firstPurchasedDiscount = totalValue * 0.1;//calculating the discount
                        customer.setFirstPurchase(false); // Updating the customer's first purchase status after applying the discount
                    }
                    firstBuyDiscount.setText("First Purchase Discount (10%) : -$ " + firstPurchasedDiscount);//setting the text for the discount value

                    double discountSame = calculateTheDiscountSameProduct(totalValue);//calculating the discount through a method
                    //setting values for the labels
                    sameCategoryDiscountLabel.setText("Three items in same category Discount (20%) : -$ " + discountSame);
                    finalTotalLabel.setText("Final Total : " + (totalValue - discountSame-firstPurchasedDiscount));
                }
            }
        });
        /*addToCart.addActionListener(new ActionListener() {
            WestminsterShoppingManager wsm2=new WestminsterShoppingManager();
            @Override
            public void actionPerformed(ActionEvent e) {
                String productDetails = dataIDLabel.getText() + " " + dataNameLabel.getText()+" "+dataExtraLabel1.getText()+" "+dataExtraLabel2.getText();
                int productQuantity = 1; // Assuming you have a JTextField for quantity
                //double productPrice = Double.parseDouble(dataPriceLabel.getText()); // Assuming you have a JTextField for price
                double productPrice;
                try {
                    productPrice = Double.parseDouble(dataPriceLabel.getText());
                    // Rest of your code that uses productPrice
                } catch (NumberFormatException error) {
                    // Handle the exception, e.g., display an error message or log it
                    JOptionPane.showMessageDialog(null, "No Products Selected !");
                    // Optionally, set a default value or take appropriate action
                    return; // or any default value
                    // Rest of your code that uses productPrice
                }
                // Adding a new row to the table
                int checkExistingRowIndex = findTheProductIndex(productDetails);//checking the existing product in the table

                String productID=dataIDLabel.getText();
                // Checking whether the product exists in the system
                Product selectedProduct=null;
                for (Product prod:wsm2.getProductsMainList()){
                    if (productID.equals(prod.getProductID())){
                        selectedProduct = prod;
                    }
                }


                if (checkExistingRowIndex != -1 && selectedProduct != null && selectedProduct.getAvailableQuantity() > 0) {
                    // Product already exists in the cart, update quantity and price
                    int currentProductQuantity = (int) shoppingCartModel.getValueAt(checkExistingRowIndex, 1);
                    double currentProductPrice = (double) shoppingCartModel.getValueAt(checkExistingRowIndex, 2);

                    // Check if available quantity is sufficient in the system
                    if (selectedProduct.getAvailableQuantity() >= 1) {
                        shoppingCartModel.setValueAt(currentProductQuantity + productQuantity, checkExistingRowIndex, 1);
                        shoppingCartModel.setValueAt(currentProductPrice + productPrice, checkExistingRowIndex, 2);

                        // Updates the available quantity in the system
                        selectedProduct.setAvailableQuantity(selectedProduct.getAvailableQuantity() - productQuantity);

                        // Update the label in the UI
                        label5.setText("Available Quantity : " + selectedProduct.getAvailableQuantity());
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient available quantity in the system.");
                    }
                } else {
                    // when the Product does not exist in the cart, this line will add a new row to the table
                    if (selectedProduct != null && selectedProduct.getAvailableQuantity() >= 1) {
                        Object[] newData = {productDetails, productQuantity, productPrice};
                        shoppingCartModel.addRow(newData);

                        // Updating the available quantity in the system
                        selectedProduct.setAvailableQuantity(selectedProduct.getAvailableQuantity() - productQuantity);

                        // Updating the label in the GUI
                        label5.setText("Available Quantity : " + selectedProduct.getAvailableQuantity());
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient available quantity in the system.");
                    }
                }
                // Calculate total value and update the label
                double totalValue = calculateTotalPrice();
                totalPriceLabel.setText("Total : $" + totalValue);
                //first buy discount
                // Add the purchased product details to the customer's purchase history
                CustomerHistory customer=new CustomerHistory();
                customer.addToPurchaseHistory(productDetails);

                // Check if it's the customer's first purchase and apply a 10% discount
                double firstPurchaseDiscount=0;
                if (customer.isFirstPurchase()) {
                    firstPurchaseDiscount = totalValue * 0.1;
                    customer.setFirstPurchase(false); // Update customer's first purchase status after applying discount
                }
                firstBuyDiscount.setText("First Purchase Discount (10%) : -$" + firstPurchaseDiscount);
                //firstBuyDiscount.setText("First Purchase Discount (10%) : ");
                double discountSame=calculateTheDiscountSameProduct(totalValue);
                sameCategoryDiscountLabel.setText("Three items in same category Discount (20%) : "+discountSame);
                finalTotalLabel.setText("Final Total : "+(totalValue-discountSame));

            }
        });*/
        /*-------------------------------*/

        JPanel shopCartDownPanel=new JPanel(new GridLayout(4,1));//creating a grid layout panel
        shopCartDownPanel.setBorder(new EmptyBorder(0,10,10,10));//adding a margin space
        //adding all the labels to the panel
        shopCartDownPanel.add(totalPriceLabel);
        shopCartDownPanel.add(firstBuyDiscount);
        shopCartDownPanel.add(sameCategoryDiscountLabel);
        shopCartDownPanel.add(finalTotalLabel);
        //adding the sub panel to the main panel
        mainPanel.add(shopCartDownPanel);


        shoppingCartUI.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // Displays the shopping cart GUI
        shoppingCartUI.setVisible(true);

    }

    //method to find the products index
    private int findTheProductIndex(String productName) {
        for (int i = 0; i < shoppingCartModel.getRowCount(); i++) {
            if (productName.equals(shoppingCartModel.getValueAt(i, 0))) {
                return i; //if the product found this will return the index of the value
            }
        }
        return -1; // returns the value when the product not founded
    }

    //method to calculate the total
    private double calculateTotalPrice() {
        double totalValue = 0;
        for (int i = 0; i < shoppingCartModel.getRowCount(); i++) {
            //getting the value from the cell of the tables price column
            double price = (double) shoppingCartModel.getValueAt(i, 2);
            //calculating the total
            totalValue +=price;
        }
        return totalValue;//returns the total
    }

    //method to calculate the discount value
    private double calculateTheDiscountSameProduct(double total){
        double discountedValue=0;//creating the variable to assign the discount
        for (int i = 0; i < shoppingCartModel.getRowCount(); i++) {

            int quantity = (int) shoppingCartModel.getValueAt(i, 1);//getting the quantity from the table
            //checking the quantity
            if (quantity>=3){
                discountedValue=(total*0.2);//this will calculate the total discount
            }
        }
        return discountedValue;//returns the discount value
    }

}


