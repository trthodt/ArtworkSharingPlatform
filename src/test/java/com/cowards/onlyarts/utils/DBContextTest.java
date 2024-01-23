package com.cowards.onlyarts.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DBContextTest {
    
    public DBContextTest() {
    }

    @org.junit.jupiter.api.Test
    public void testGetConnectionSuccessfully() throws Exception {
        System.out.println("getConnection");
        String driverName = "" ;
        String driverVersion = "";
        Connection result = DBContext.getConnection();
        DatabaseMetaData dm = (DatabaseMetaData) result.getMetaData();
        assertEquals(driverName, dm.getDriverName());
        assertEquals(driverVersion, dm.getDriverVersion());
    }
    
    @org.junit.jupiter.api.Test
    public void testGetConnectionThrowsExceptions() throws Exception {
        System.out.println("getConnection");
        Connection result = DBContext.getConnection();
        assertEquals(SQLException.class, result);
    }
}
