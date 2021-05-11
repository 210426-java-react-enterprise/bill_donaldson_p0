package com.revature.bill_donaldson_p0.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.revature.bill_donaldson_p0.daos.GetBalance;

/* This class is called from Transaction Table.  The sole purpose is to verify that the person is
not entering a negative deposit or withdrawal and that there are sufficient funds to cover the withdrawal.
 */


public class CheckTranactionParameters {

    public void checkCustomerinputs(char trantype, double amount) throws IOException {
        int get_valid_amount = 1;
        double current_balance =0.0;
        GetBalance custbalance = new GetBalance();
        int cont_ask = 1;
        current_balance = custbalance.currBalance();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.printf("Your available balance = %f",current_balance);
        try {
            while ((get_valid_amount == 1) && (cont_ask == 1)) {

                while (get_valid_amount == 1) {
                    System.out.println("Enter the amount to deposit.");
                    String amt_string = reader.readLine();
                    amount = Double.parseDouble(amt_string);

                    if ((amount < current_balance) && (amount > 0)){
                        get_valid_amount =0;
                    }
                    else
                        System.out.println("Negative amounts are not allowed or requested amount is greater than available.  Try again.");
                }
            }
        }
             catch (Exception e) {
                e.printStackTrace();

        }
    }
}
