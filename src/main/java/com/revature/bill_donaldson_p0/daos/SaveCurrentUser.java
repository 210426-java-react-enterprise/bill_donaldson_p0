package com.revature.bill_donaldson_p0.daos;

import com.revature.bill_donaldson_p0.models.AppUser;
import com.revature.bill_donaldson_p0.util.ConnectionFactory;

import java.io.*;
import java.sql.*;

public class SaveCurrentUser {

    // TODO (Associate task) Implement me!
    public static void currCust(String  curruser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql;
            PreparedStatement pstmt;
            ResultSet rs;

            sql = "delete * from public.curr_user";
            pstmt = conn.prepareStatement(sql);

            pstmt.executeQuery();
            //String sql = "select * from quizzard.curruser";
            //pstmt = conn.prepareStatement(sql);
            //pstmt.executeQuery();
            sql = "insert into public.curr_user (curruser)" + "values(?)";
            pstmt.setString(1, curruser);
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();




        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}