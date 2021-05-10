package com.revature.bill_donaldson_p0.services;
import com.revature.bill_donaldson_p0.util.ConnectionFactory;

import java.io.*;
import java.sql.*;

public class depositsWithdrawls {

    public static void changeBalance(double amountChange) {

        String changeAmount = Double.toString(amountChange);
        PreparedStatement pstmt;
        String sql;

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
                sql = "update get_savings_row set amount = amount + ?" + "values(1,changeAmount)";
                //System.out.println(sql);
                //  ResultSet rs = pstmt.executeQuery();
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
