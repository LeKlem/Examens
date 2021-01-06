package org.acme.optaplanner.controller;


import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.acme.optaplanner.domain.Examens;
import org.acme.optaplanner.domain.Room;
import org.acme.optaplanner.domain.TimeTable;
import org.acme.optaplanner.domain.Timeslot;

public class MyDataSourceFactory {
    static MysqlDataSource mysqlDS;
    static Connection con;


    public static Connection getConnection() throws SQLException {
        //Singleton, renvoie la connexion en la créant si elle n'existe pas
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
        //Crypte le mdp selon la même clé static que celle utilisée pour remplir la bdd
        psw = PasswordUtils.HashPassword(psw);
        Statement stmt;
        ResultSet rs = null;
        try {
            String s = "SELECT * FROM Users WHERE login = ?";
            PreparedStatement pst = con.prepareStatement(s);
            pst.setString(1, log);
            rs = pst.executeQuery();
            while(rs.next()) {
                //Si nous avons un résultat, on compare les deux mdp cryptés et on renvoie le role si ça correspond
                if(rs.getString("password").equals(psw)) {
                    return (rs.getString("job"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "error";
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
    public static TimeTable GetExamsToPlan(Connection con)  {
        List<Timeslot> timeslotList = new ArrayList<Timeslot>();
        List<Room> roomList = new ArrayList<Room>();
        List<Examens> examensList = new ArrayList<Examens>();

        try {
          String s = "Select * from room";
          ResultSet rs = null;
          Statement stmt = con.createStatement();
          rs = stmt.executeQuery(s);
          while(rs.next()){
              roomList.add(new Room(rs.getString("name"), rs.getInt("sits")));
          }
          s = "Select * from examens";
          rs = stmt.executeQuery(s);
          while (rs.next()){
              examensList.add(new Examens(rs.getInt("annee"), rs.getString("filiere"), rs.getString("matiere"), rs.getString("name")));
          }
          s = "Select * from timeslots";
          rs = stmt.executeQuery(s);
          while (rs.next()){
              timeslotList.add(new Timeslot(rs.getDate("Day"), rs.getTime("Start").toLocalTime(), rs.getTime("End").toLocalTime()));
          }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new TimeTable(timeslotList, roomList, examensList);
    }

    public static void CreateExam (Connection con, String matiere, String anneEtu,String filiere ,int duree, String format,String salle) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO examen(matiere,annee,filiere,Durée,format,salle) VALUES (?, ?, ?, ?, ?, ? )");
        stmt.setString(1,matiere);
        stmt.setString(2,anneEtu);
        stmt.setString(3,filiere);
        stmt.setInt(4,duree);
        stmt.setString(5,format);
        stmt.setString(6,salle);
        stmt.execute();
    }
    static int getNumberOfExam(Connection con) throws SQLException {
        int x = 0;
        String str = "SELECT COUNT(*) AS X FROM Examen";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(str);
        while (rs.next()){
            x = rs.getInt("X");
        }
        return x;
    }
    static int getNumberOfUser(Connection con) throws SQLException {
        int x = 0;
        String str = "SELECT COUNT(*) AS X FROM users";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(str);
        while (rs.next()){
            x = rs.getInt("X");
        }
        return x;
    }
}
