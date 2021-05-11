package com.revature.bill_donaldson_p0.screens;
import com.revature.bill_donaldson_p0.util.ScreenRouter;
import com.revature.bill_donaldson_p0.services.CheckTranactionParameters;


import java.io.BufferedReader;

public class TransactionScreen extends Screen {


    private BufferedReader consoleReader;
    private ScreenRouter router;
    public String curruser;
    public String transOption;
    public double amount_of_change;
    int get_valid_amount = 1;

    public TransactionScreen(BufferedReader consoleReader) {

        super("TransactionScreen", "/transaction");
        this.consoleReader = consoleReader;


    }


    public void render(){

        try {
            System.out.println("/*********************************************************/");

            System.out.println("/*********************************************************/");
            System.out.println("The following list contains all possible transactions.");
            System.out.println("1) Deposit");
            System.out.println("2) Withdrawal");
            System.out.println("3) Check Balance");
            System.out.println("4) Get all transactions");
            System.out.print("> ");





            transOption = consoleReader.readLine();
            switch(transOption) {
                case "1": {


                    break;

                }
                    case "2": {
                        System.out.println("Enter the amount to Withdrawal.");
                        amount_of_change = Double.parseDouble(consoleReader.readLine());
                        amount_of_change = -amount_of_change;
                        System.out.printf("The value = %f",amount_of_change);

                        break;
                    }
                case "3": {

                }
                default:
                    System.out.println("Invalid selection!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
