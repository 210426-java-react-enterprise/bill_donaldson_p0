package com.revature.bill_donaldson_p0.daos;
import com.revature.bill_donaldson_p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* I use SSN as my identifier when moving through the tables as my primary key.
   this block of code returns the current customer's SSN.
 */


public class GetUserSsn {
    public static String getUserid() {
        String ssn = "";
        PreparedStatement pstmt;
        String sql;


        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            sql = "create table get_user_ssn as"+
                    "select public.users.ssn"+
                    "from public.curr_user inner join public.users on"+
                    "public.curr_user.curruser = public.users.username";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ssn = rs.getString("ssn");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return ssn;

    }
}