package com.revature.bill_donaldson_p0.daos;

import com.revature.bill_donaldson_p0.services.addTotransactiontable;
import com.revature.bill_donaldson_p0.util.ConnectionFactory;
import com.revature.bill_donaldson_p0.daos.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepositsWithdrawls {

    public static void changeBalance(double amountChange) {

        String changeAmount = Double.toString(amountChange);
        PreparedStatement pstmt;
        String sql;
        String ssn ="";

        addTotransactiontable curr_tran = new addTotransactiontable();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            /* The easy case is deposits.*/
            if (amountChange > 0) {
                /* Step 1 = Get the current user's SSN.*/
                sql = "create table get_user_ssn as"+
                        "select public.users.ssn"+
                        "from public.curr_user inner join public.users on"+
                        "public.curr_user.curruser = public.users.username";
                pstmt = conn.prepareStatement(sql);
                pstmt.executeQuery();
                /* Pull the correct row from the savings account.*/
                sql = "create table get_savings_row as"+
                        "select savings.* from " +
                        "   savings inner join get_user_ssn on" +
                        "savings.ssn = get_user_ssn.ssn";
                //pstmt.setString(1, curruser);
                pstmt = conn.prepareStatement(sql);
                pstmt.executeQuery();
                // Step 3 - Update the previously created table.
                sql = "update savings set amount = amount + amountChange" +
                        "values(?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setDouble(1, amountChange);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
