package org.acme.optaplanner.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.MysqlDataSource;

public class MyDataSourceFactory {
    static MysqlDataSource mysqlDS;
    static Connection con;


    static Connection getConnection () throws SQLException {
        if(mysqlDS == null){
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL("jdbc:mysql://localhost/exams?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC");
            mysqlDS.setUser("root");
            mysqlDS.setPassword("");
            con = mysqlDS.getConnection();
        }
        return con;
    }


    public static String Connecting(Connection con, String log, String psw) {
        psw = PasswordUtils.HashPassword(psw);
        Statement stmt;
        ResultSet rs = null;
        try {
            String s = "SELECT * FROM Users WHERE login = ?";
            PreparedStatement pst = con.prepareStatement(s);
            pst.setString(1, log);
            rs = pst.executeQuery();
            while(rs.next()) {
                if(rs.getString("password").equals(psw)) {
                    return (rs.getString("job"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void CreateAccount(Connection con, String log, String password, String nom, String prenom, String job)  {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Users (login, password, nom, prenom, job) VALUES (?, ?, ?, ?, ? )");
            stmt.setString(1, log);
            stmt.setString(2, password);
            stmt.setString(3, nom);
            stmt.setString(4, prenom);
            stmt.setString(5, job);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
