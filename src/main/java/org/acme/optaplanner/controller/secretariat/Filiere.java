package org.acme.optaplanner.controller.secretariat;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

public class Filiere
{
    //Initialisation des différents éléments
    static Button send = new Button("Envoyer");
    static Button cancel = new Button("Annuler");
    static boolean exit;

    public static boolean Display() throws SQLException
    {
        exit = false;
        //Containers---------------------------------------
        HBox hbox = new HBox();
        Stage window = new Stage();
        window.setTitle("Choisissez la(s) filière(s)");
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);

        //Boutons------------------------
        hbox.getChildren().add(send);
        hbox.getChildren().add(cancel);

        //Fonctionnement des différents boutons
        send.setOnAction(e ->
        {
            //récupérer les info des checkbox et mettre dans la bdd
        });

        cancel.setOnAction(e ->
        {
            exit = true;
            window.close();
        });

        //Affichage
        Scene scene = new Scene(hbox,250,150);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}

