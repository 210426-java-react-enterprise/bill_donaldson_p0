package com.revature.bill_donaldson_p0.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory connectionFactory;
    private Properties props = new Properties();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ConnectionFactory() {
        try {
           // props.load(new FileReader("src/main/resources/application.properties"));
            // I need to be able to connect to Wezley's database as a check.
            props.load(new FileReader("src/main/resources/application3.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }

        return connectionFactory;
    }

    public Connection getConnection() {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(
                    "jdbc:postgresql://"+ props.getProperty("host") + ":" +
                            props.getProperty("port") +"/"  +
                            props.getProperty("dbname") +
                            "?currentSchema=" +
                            props.getProperty("schemaname"),
                    props.getProperty("username"),
                    props.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }

}

