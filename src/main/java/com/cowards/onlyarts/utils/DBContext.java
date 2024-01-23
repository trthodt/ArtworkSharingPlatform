package com.cowards.onlyarts.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBContext {

    private static final String DB_NAME = "OnlyArts";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "12345";

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=" + DB_NAME + ";encrypt=true;trustServerCertificate=true;";
        conn = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
        return conn;
    }
}
