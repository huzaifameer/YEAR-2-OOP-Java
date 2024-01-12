package com.W1956114;

import static org.junit.Assert.*;

import com.W1956114.User.User;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MainTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Before
    public void setUp() {
        Main.commonUserList.clear(); // Clearing user lists before each test
        Main.managerUserList.clear();
    }

    @Test
    public void testLoginManagerSuccessful() {
        Main.managerUserList.add(new User("Manager-1", "12345"));

        String input = "Manager-1" + LINE_SEPARATOR + "12345" + LINE_SEPARATOR;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertTrue(Main.loginManager());
    }

    @Test
    public void testLoginManagerUnsuccessful() {
        Main.managerUserList.add(new User("Manager-1", "12345"));

        String input = "InvalidManager" + LINE_SEPARATOR + "WrongPassword" + LINE_SEPARATOR;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertFalse(Main.loginManager());
    }

    @Test
    public void testLoginCustomerSuccessful() {
        Main.commonUserList.add(new User("Customer1", "password"));

        String input = "1" + LINE_SEPARATOR + "Customer1" + LINE_SEPARATOR + "password" + LINE_SEPARATOR;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertTrue(Main.loginCustomer());
    }

    @Test
    public void testLoginCustomerUnsuccessful() {
        Main.commonUserList.add(new User("Customer1", "password"));

        String input = "1" + LINE_SEPARATOR + "InvalidCustomer" + LINE_SEPARATOR + "WrongPassword" + LINE_SEPARATOR;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertFalse(Main.loginCustomer());
    }

    @Test
    public void testCreateNewCustomerAccount() {
        String input = "2" + LINE_SEPARATOR + "NewCustomer" + LINE_SEPARATOR + "NewPassword" + LINE_SEPARATOR;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertTrue(Main.loginCustomer());
        assertEquals(1, Main.commonUserList.size());
        assertEquals("NewCustomer", Main.commonUserList.get(0).getUserName());
        assertEquals("NewPassword", Main.commonUserList.get(0).getPassword());
    }
}
