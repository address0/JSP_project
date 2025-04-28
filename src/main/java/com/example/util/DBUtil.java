package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:oracle:thin:@//192.168.217.202:1521/KOPODA";
    private static final String USER = "da2519";
    private static final String PASSWORD = "da19";

    public DBUtil() {
    }

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@//192.168.217.202:1521/KOPODA", "da2519", "da19");
    }
}
