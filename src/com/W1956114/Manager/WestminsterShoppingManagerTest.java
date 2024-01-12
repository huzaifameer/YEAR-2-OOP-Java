package com.W1956114.Manager;

import com.W1956114.SubClasses.Clothing;
import com.W1956114.SubClasses.Electronic;
import com.W1956114.Super.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WestminsterShoppingManagerTest {

    private WestminsterShoppingManager manager;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        manager = new WestminsterShoppingManager();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void addAProduct_ValidProduct_Success() {
        // Set up input for the test
        provideInput("E\nPC123\nTestProduct\n10\n20.0\nBrand\n12");

        // Call the method to add a product
        manager.addAProduct();

        // Check if the product has been added successfully
        assertEquals(1, manager.getProductsMainList().size());
        assertEquals("PC123", manager.getProductsMainList().get(0).getProductID());
    }

    @Test
    void deleteAProduct_ProductExists_Success() {
        // Add a product to the list
        manager.getProductsMainList().add(new Electronic("Electronic", "PC123", "TestProduct", 10, 20.0, "Brand", 12));

        // Call the method to delete a product by providing the product ID
        manager.deleteAProduct();

        // Check if the product has been deleted successfully
        assertEquals(0, manager.getProductsMainList().size());
        assertTrue(outputStreamCaptor.toString().contains("Product id PC123 deleted successfully"));
    }

    @Test
    void displayProducts_ProductsExist_Success() {
        // Add products to the list
        manager.getProductsMainList().add(new Electronic("Electronic", "PC123", "TestProduct", 10, 20.0, "Brand", 12));
        manager.getProductsMainList().add(new Clothing("Clothing", "PC124", "TestClothing", 8, 15.0, "L", "Red"));

        // Call the method to display products
        manager.displayProducts();

        // Check if products are displayed
        assertTrue(outputStreamCaptor.toString().contains("PC123"));
        assertTrue(outputStreamCaptor.toString().contains("TestProduct"));
        assertTrue(outputStreamCaptor.toString().contains("PC124"));
        assertTrue(outputStreamCaptor.toString().contains("TestClothing"));
    }

    @Test
    void saveToAFile_ProductsExist_Success() {
        // Add products to the list
        manager.getProductsMainList().add(new Electronic("Electronic", "PC123", "TestProduct", 10, 20.0, "Brand", 12));
        manager.getProductsMainList().add(new Clothing("Clothing", "PC124", "TestClothing", 8, 15.0, "L", "Red"));

        // Call the method to save to a file
        manager.saveToAFile();

        // Check if the data has been saved successfully
        assertTrue(outputStreamCaptor.toString().contains("Data has been saved successfully"));
    }

    @Test
    void readFromAFile_FileExists_Success() {
        // Set up a product list for comparison
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Electronic("Electronic", "PC123", "TestProduct", 10, 20.0, "Brand", 12));
        productList.add(new Clothing("Clothing", "PC124", "TestClothing", 8, 15.0, "L", "Red"));

        // Add products to the list
        manager.getProductsMainList().add(new Electronic("Electronic", "PC123", "TestProduct", 10, 20.0, "Brand", 12));
        manager.getProductsMainList().add(new Clothing("Clothing", "PC124", "TestClothing", 8, 15.0, "L", "Red"));

        // Call the method to read from a file
        manager.readFromAFile();

        // Check if the data has been read successfully
        assertTrue(outputStreamCaptor.toString().contains("*** All the added products are mentioned below ***"));
        assertTrue(outputStreamCaptor.toString().contains("Product Type         : Electronic"));
        assertTrue(outputStreamCaptor.toString().contains("Product Type         : Clothing"));
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }
}
