package com.revature.bill_donaldson_p0.screens;

import com.revature.bill_donaldson_p0.daos.UserDAO;
import com.revature.bill_donaldson_p0.models.AppUser;
import com.revature.bill_donaldson_p0.util.ScreenRouter;
import com.revature.bill_donaldson_p0.services.UserService;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    public static String username;
    private UserDAO userDao = new UserDAO();
    private final BufferedReader consoleReader;
    private ScreenRouter router;
    private UserService userService = new UserService(userDao);

    //public String username;
    public String password;

    public LoginScreen(BufferedReader consoleReader) {

        super("LoginScreen", "/login");
        this.consoleReader = consoleReader;

    }

    public void render() {

        try {


            System.out.println("Log into your account!");
            System.out.println("+---------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();
            System.out.println("You are about to go to authenticate");
            System.out.println("The value of username = "+ username);
            System.out.println("The value of password = " + password);
            AppUser authenticatedUser = userService.authenticate(username, password);
            System.out.println("You are back from Authenticate user.");
            if (authenticatedUser != null) {
                System.out.println("Login successful!");
                router.navigate("/transaction");
            }
            else {
                    System.out.println("Login failed!");
                }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

