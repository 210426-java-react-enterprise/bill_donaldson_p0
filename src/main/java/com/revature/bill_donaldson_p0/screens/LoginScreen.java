package com.revature.bill_donaldson_p0.screens;

import com.revature.bill_donaldson_p0.daos.UserDAO;
import com.revature.bill_donaldson_p0.models.AppUser;
import com.revature.bill_donaldson_p0.daos.SaveCurrentUser;
import com.revature.bill_donaldson_p0.util.AppState;
import com.revature.bill_donaldson_p0.util.ScreenRouter;


import java.io.BufferedReader;

public class LoginScreen extends Screen {

    public static String username;
    private UserDAO userDao = new UserDAO();
    private BufferedReader consoleReader;
    public static AppState app2 = new AppState();
    private ScreenRouter router;

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

            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                AppUser authenticatedUser = userDao.findUserByUsernameAndPassword(username, password);
                if (authenticatedUser != null) {
                    System.out.println("Login successful!");
                    // We know that the user has an account, so to go to the transction screen is proper.
                    // System.out.println("Navigating to transaction screen");
                    //                    router.navigate("/transaction");
                    System.out.println("***************************************");
                    System.out.println("***************************************");
                    System.out.println("You may now make a transaction");
                    System.out.println("***************************************");
                    System.out.println("***************************************");
                    System.out.println("");
                    System.out.println("Navigating to Transaction screen");
                    System.out.println("The value of username just before the call to transaction = " + username);
                    SaveCurrentUser.currCust(username);
                    app2.getRouter().navigate("/transaction");

                } else {
                    System.out.println("Login failed!");
                }
            } else {
                System.out.println("It looks like you didn't provide any credentials!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

