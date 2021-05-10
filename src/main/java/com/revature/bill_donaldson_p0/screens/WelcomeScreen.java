package com.revature.bill_donaldson_p0.screens;

import com.revature.bill_donaldson_p0.util.ScreenRouter;
import static com.revature.bill_donaldson_p0.Driver.app;

import java.io.BufferedReader;

public class WelcomeScreen extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("WelcomeScreen", "/welcome");
        this.consoleReader = consoleReader;
        this.router = router;
    }
/************************  Part 1 ******************************************/
    @Override
    public void render() {

        System.out.println("Welcome to The Bank of Donaldson");
        System.out.println("1) Login");
        System.out.println("2) Register");

        /* The next line is temporary until I build additional screens.*/
        //System.out.println("55) Make a Transaction");
        /* ************************************************************/
        //System.out.println("3) Make a transaction");
        System.out.println("3) Exit application");

        try {
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("Navigating to login screen");
                    router.navigate("/login");

                    break;
                case "2":
                    System.out.println("Navigating to register screen");
                    router.navigate("/register");
                    break;
                case "3":
                    System.out.println("Exiting application!");
                    // we need to figure out how to tell the app the shutdown
//                    System.exit(0); // very bad practice; force closes the JVM
                    app().setAppRunning(false);
                    break;
                default:
                    System.out.println("Invalid selection!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

