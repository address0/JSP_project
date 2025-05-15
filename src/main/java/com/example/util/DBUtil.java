package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import jakarta.servlet.ServletContext;

public class DBUtil {
    private static final String url = "jdbc:oracle:thin:@dinkdb_medium?TNS_ADMIN=";
    private static final String USER = "DA2519";
    private static final String PASSWORD = "Data2519";

    public DBUtil() {
    }

    public static Connection getConnection(ServletContext context) throws Exception {
        String walletPath = context.getRealPath("/WEB-INF/wallet").replace("\\", "/");
        Class.forName("oracle.jdbc.driver.OracleDriver");
//        return DriverManager.getConnection("jdbc:oracle:thin:@//192.168.217.202:1521/KOPODA", "da2519", "da19");
        return DriverManager.getConnection(url + walletPath, USER, PASSWORD);
    }
}