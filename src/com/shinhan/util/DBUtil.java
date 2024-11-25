package com.shinhan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {

    // Database pool
    public static Connection getConnection2(){
        Context initContext;
        Connection conn = null;

        try {
            initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");  // 정해져 있음
            DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle"); // Datasource를 얻어라
            conn = ds.getConnection();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
    public static Connection getConnection(){
        Connection conn = null;
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String userid = "linechat";
        String userpass = "1234";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(url, userid, userpass);
        } catch (ClassNotFoundException e) {
            System.out.println("oracle.jdbc.OracleDriver" + " 클래스 없음");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("getConnection error");
            throw new RuntimeException(e);
        }

        return conn;
    }

    public static void dbDisconnection(Connection conn, Statement st, ResultSet rs){
        try{
            if(rs != null) rs.close();
            if(st != null) st.close();
            if(conn != null) conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void dbDisconnection(Connection conn, Statement st){
        dbDisconnection(conn, st, null);
    }
}
