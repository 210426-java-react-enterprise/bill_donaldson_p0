package com.revature.bill_donaldson_p0.daos;

import com.revature.bill_donaldson_p0.models.AppUser;
import com.revature.bill_donaldson_p0.util.ConnectionFactory;

import java.io.*;
import java.sql.*;

public class UserDAO {

    // TODO (Associate task) Implement me!
    public void save(AppUser newUser) {


        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement pstmt;
            String sql;
            /* Add the user to the Customer Table.*/

            sql = "insert into public.users (username,password,email,first_name,last_name,age,ssn)" +
                    "values(?,?,?,?,?,?,?)";
            //String sql = "select * from quizzard.users";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getFirstName());
            pstmt.setString(5, newUser.getLastName());
            pstmt.setInt(6,newUser.getAge());
            pstmt.setString(7,newUser.getSsn());

            System.out.println(sql);
          //  ResultSet rs = pstmt.executeQuery();
            pstmt.executeUpdate();

            /* Create an account for the Customer.*/

            sql = "insert into public.savings (ssn, amount)" +
                    "values(?,?)";
            //String sql = "select * from quizzard.users";
            System.out.println("You are here");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUser.getSsn());
            pstmt.setDouble(2,'0');
            //pstmt.setString(2, String.valueOf(0));
            System.out.println(sql);
            //  ResultSet rs = pstmt.executeQuery();
            pstmt.executeUpdate();
            System.out.println("You executed the query.");


            /* Now I have to save the ID for the current user.*/

           sql = "delete from public.curr_user";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();

            sql = "insert into public.curr_user (username,ssn)" +
                    "values(?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getSsn());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




    public AppUser findUserByUsernameAndPassword(String username, String password) {

        AppUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from users where username = ? and password = ?";
            //String sql = "select * from quizzard.users";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new AppUser();
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
                user.setSsn(rs.getString("ssn"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

}

