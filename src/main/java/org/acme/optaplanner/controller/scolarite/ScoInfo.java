package org.acme.optaplanner.controller.scolarite;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.acme.optaplanner.controller.MyDataSourceFactory;

import java.sql.*;

import java.util.Optional;

public class ScoInfo {

    //implémentations

    static Label salle;
    static Label examen;
    static Label date;
    static Label heure;

    static Button Battribuer = new Button("Attribuer");
    static Button Bannuler = new Button("Annuler");
    static Button Bmodifier = new Button("Modifier");

    static Label Lsalle = new Label("Salle libre : ");
    static Label Lexamen = new Label("Examen : ");
    static Label Ldate = new Label("Date : ");
    static Label Lheure = new Label("Heure : ");

    static Connection con;
    static boolean exit;
    private static Statement St;
    private static ResultSet Rs;


    public static boolean Display() throws SQLException {

        exit = false;

        //connexion
        con = MyDataSourceFactory.getConnection();

        //container
        Stage window = new Stage();
        window.setTitle("Info");
        GridPane grid = new GridPane();
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        //contenu :

        //examen
        String a = "SELECT nom FROM examen WHERE id = ?";
        PreparedStatement p1 = con.prepareStatement(a);
        p1.setInt(1, ScoRechercher.id);
        Rs = p1.executeQuery();
        while (Rs.next())
        {
            examen = new Label(Rs.getString("nom"));
        }
        grid.add(Lexamen, 0, 0);
        grid.add(examen, 1, 0);

        //date (convertit en idTimeSlot par le solver)
        //grid.add(Ldate, 0, 1);
        //grid.add(date, 1, 1);

        //heure (convertit en idTimeSlot par le solver)
        //grid.add(Lheure, 0, 2);
        //grid.add(heure, 1, 2);

        //salle
        String b = "SELECT salle FROM examen WHERE id = ?";
        PreparedStatement p2 = con.prepareStatement(b);
        p2.setInt(1, ScoRechercher.id);
        Rs = p2.executeQuery();
        while (Rs.next())
        {
            salle = new Label(Rs.getString("salle"));
        }
        grid.add(Lsalle, 0, 3);
        grid.add(salle, 1, 3);

        //boutons
        vbox.getChildren().add(Battribuer);
        vbox.getChildren().add(Bmodifier);
        vbox.getChildren().add(Bannuler);

        //fonctionnement boutons
        Battribuer.setOnAction(e ->
        {
            try {
                Attribuer.Display();
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        });

        Bmodifier.setOnAction(e ->
        {
            try {
                ScoModifier.Display();
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        });

        Bannuler.setOnAction(e -> //demande confirmation pour annuler puis renvoi vers l'accueil
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes vous sur de vouloir annuler ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                exit = true;
                try {
                    ScoAccueil.Display();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        //affichage
        vbox.getChildren().add(grid);
        Scene scene = new Scene(vbox, 300, 220);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}
