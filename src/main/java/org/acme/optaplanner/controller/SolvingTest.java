package org.acme.optaplanner.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.quarkus.test.junit.QuarkusTest;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.acme.optaplanner.domain.Room;
import org.acme.optaplanner.domain.Timeslot;
import org.acme.optaplanner.domain.Examens;
import org.acme.optaplanner.domain.TimeTable;

@QuarkusTest
public class SolvingTest {
    static Button valider = new Button("Résoudre : ");


    public static void Display() throws SQLException {

        Stage window = new Stage();
        window.setTitle("Resolution");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(valider, 0, 0);
        Connection con = MyDataSourceFactory.getConnection();
        valider.setOnAction(e -> {
         TimeTable prob = MyDataSourceFactory.GetExamsToPlan(con);
         //TimeTableResource.solve(prob);
        });

        Scene scene = new Scene(grid, 300, 150);
        window.setScene(scene);
        window.showAndWait();
    }

    private static TimeTable generateProblem() {
        List<Timeslot> timeslotList = new ArrayList<>();
        Date d1 = new Date(1/2/2021);
        Date d2 = new Date(3/4/2021);
        Date d3 = new Date(5/6/2021);
        Date d4 = new Date(7/8/2021);
        Date d5 = new Date(9/10/2021);

        timeslotList.add(new Timeslot(d1, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotList.add(new Timeslot(d2, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotList.add(new Timeslot(d3, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotList.add(new Timeslot(d4, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotList.add(new Timeslot(d5, LocalTime.of(14, 30), LocalTime.of(15, 30)));

        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Room A", 50));
        roomList.add(new Room("Room B", 125));
        roomList.add(new Room("Room C", 15));

        List<Examens> examensList = new ArrayList<>();
        examensList.add(new Examens(2021, "Science", "Math", "2021ScienceMath"));
        examensList.add(new Examens(2021, "Science", "Physique", "2021SciencePhysique"));
        examensList.add(new Examens(2021, "L", "Chomage", "2021LChomage"));
        examensList.add(new Examens(2021, "Eco", "Commerce", "2021EcoCommerce"));

        return new TimeTable(timeslotList, roomList, examensList);
    }
}