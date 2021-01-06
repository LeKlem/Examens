package org.acme.optaplanner.controller.scolarite;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.acme.optaplanner.controller.MyDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ScoAccueil {

    //implémentations
    static Button Battribuer = new Button("Attribuer");
    static Button Bsalle = new Button("Disponibilité salle");
    static Button Bmodif = new Button("Modifier");
    static Button Bcalendrier = new Button("Calendrier");
    static Button Brechercher = new Button("Rechercher");

    static Connection con;
    static boolean exit;


    public static boolean Display() throws SQLException {

        exit = false;

        //connexion
        con = MyDataSourceFactory.getConnection();

        //container
        Stage window = new Stage();
        window.setTitle("Acceuil");
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        //contenu
        vbox.getChildren().add(Battribuer);
        vbox.getChildren().add(Bsalle);
        vbox.getChildren().add(Bmodif);
        vbox.getChildren().add(Bcalendrier);
        vbox.getChildren().add(Brechercher);


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

        Bsalle.setOnAction(e ->
        {
            try {
                DispoSalle.Display();
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        });

        Bmodif.setOnAction(e ->
        {
            try {
                ScoModifier.Display();
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        });

       /* Bcalendrier.setOnAction(e ->
        {
            try {
                calendrier.Display();
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        }); */

        /* Brechercher.setOnAction(e ->
        {
            try {
                ScoRechercher.Display();
            } catch (SQLException | ClassNotFoundException throwables)
            {
                throwables.printStackTrace();
            }
        }); */

        //affichage
        Scene scene = new Scene(vbox, 300, 200);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }


}
