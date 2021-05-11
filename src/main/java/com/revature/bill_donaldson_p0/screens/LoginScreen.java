package com.revature.bill_donaldson_p0.screens;

import com.revature.bill_donaldson_p0.daos.UserDAO;
import com.revature.bill_donaldson_p0.models.AppUser;
import com.revature.bill_donaldson_p0.daos.SaveCurrentUser;
import com.revature.bill_donaldson_p0.util.AppState;
import com.revature.bill_donaldson_p0.util.ScreenRouter;
import com.revature.bill_donaldson_p0.services.UserService;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    public static String username;
    private UserDAO userDao = new UserDAO();
    private BufferedReader consoleReader;
    public static AppState app2 = new AppState();
    private ScreenRouter router;
    private UserService userService;

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

            AppUser authenticatedUser = userService.authenticate(username, password);
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

