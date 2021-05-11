package com.revature.bill_donaldson_p0.daos;

import com.revature.bill_donaldson_p0.util.ConnectionFactory;
import com.revature.bill_donaldson_p0.daos.UserDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddToTransactionTable {
    public static void addTotable(String ssn, String trantype, double Amount) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql;
            PreparedStatement pstmt;
            ResultSet rs;


            /* I have to create a temporary row to which I will add a timestamp.*/
            sql = "insert into public.temp_trans (ssn, transtype, Amount)" + "values(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ssn);
            pstmt.setString(2, trantype);
            pstmt.setDouble(3, Amount);
            pstmt.executeUpdate();

            /*Add the timestamp.*/
            sql = "create table hope as" +
                    "select *, timestamp as Trandate from public.temp_trans";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();

            /* Now I have to append the row.*/
            sql = "insert into public.transactionTable select * from hope";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

            sql = "drop table public.hope";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
