package com.revature.bill_donaldson_p0.util;

import com.revature.bill_donaldson_p0.daos.UserDAO;
import com.revature.bill_donaldson_p0.screens.LoginScreen;
import com.revature.bill_donaldson_p0.screens.RegisterScreen;
import com.revature.bill_donaldson_p0.screens.WelcomeScreen;
import com.revature.bill_donaldson_p0.screens.TransactionScreen;
import com.revature.bill_donaldson_p0.services.Transactions;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private boolean appRunning;



    public AppState() {
        System.out.println("Initializing application...");

        appRunning = true;
        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        final UserDAO userDao = new UserDAO();

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader, router))
                .addScreen(new LoginScreen(consoleReader))
                .addScreen(new RegisterScreen(consoleReader))
                .addScreen(new TransactionScreen(consoleReader));


        System.out.println("Application initialized!");
    }

    public ScreenRouter getRouter() {
        return router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }

}
