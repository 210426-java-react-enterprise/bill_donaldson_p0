package com.revature.bill_donaldson_p0.daos;
import com.revature.bill_donaldson_p0.util.ConnectionFactory;
import com.revature.bill_donaldson_p0.daos.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.bill_donaldson_p0.models.AppUser;


/* This function is used to get the available balance for the customer.  The results are needed for both
   the Customer balance inquiry and when making withdrawals.*/


public class GetBalance {

    public double currBalance() {

        PreparedStatement pstmt;
        String sql;
        String ssn = "";
        double available_amount=0;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {


            /* Step 1 = Get the current user's SSN.*/
            sql = "create table get_user_ssn as" +
                    "select public.users.ssn" +
                    "from public.curr_user inner join public.users on" +
                    "public.curr_user.curruser = public.users.username";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();
            /* Pull the correct row from the savings account.*/
            sql = "create table get_savings_row as" +
                    "select savings2.amount " +
                    "   savings2 inner join get_user_ssn on" +
                    "savings.ssn = get_user_ssn.ssn";
            //pstmt.setString(1, curruser);
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            available_amount = rs.getDouble("amount");



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return available_amount;
    }
}




