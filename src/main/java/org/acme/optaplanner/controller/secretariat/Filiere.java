package org.acme.optaplanner.controller.secretariat;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.acme.optaplanner.controller.MyDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Filiere
{
    //Initialisation des différents éléments
    static Button send = new Button("Choisir");
    static Button cancel = new Button("Annuler");
    static CheckBox l1inf = new CheckBox("Informatique");
    static CheckBox l2inf = new CheckBox("Mathématiques");
    static CheckBox l3inf = new CheckBox("Physique");
    static Connection con;


    public static void Display() throws SQLException
    {

        con = MyDataSourceFactory.getConnection();
        //Containers---------------------------------------
        HBox hbox = new HBox();
        HBox hbox1 = new HBox();
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setSpacing(10);
        Stage window = new Stage();
        window.setTitle("Choisissez la(s) filière(s)");
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);

        //Boutons------------------------
        hbox1.getChildren().add(l1inf);
        hbox1.getChildren().add(l2inf);
        hbox1.getChildren().add(l3inf);
        hbox.getChildren().add(send);
        hbox.getChildren().add(cancel);


        //Organisation

        vbox.getChildren().add(hbox1);
        vbox.getChildren().add(hbox);

        //Fonctionnement des différents boutons
        send.setOnAction(e ->
        {
            AjouteExam.afficheFiliere();
            window.close();
        });

        cancel.setOnAction(e ->
        {
            window.close();
        });

        //Affichage
        Scene scene = new Scene(vbox,350,250);
        window.setScene(scene);
        window.showAndWait();
    }
}

