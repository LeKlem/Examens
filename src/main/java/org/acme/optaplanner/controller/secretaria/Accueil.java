package org.acme.optaplanner.controller.secretaria;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Accueil
{
    //Implémentation des différents éléments
    static Button addExam = new Button("Ajouter un examen");
    static Button search = new Button("Rechercher un examen");
    static boolean exit;

    public static boolean Display() throws SQLException
    {
        exit = false;

        //Container------------------
        Stage window = new Stage();
        window.setTitle("Accueil");
        VBox menue = new VBox();
        menue.setAlignment(Pos.CENTER);
        menue.setSpacing(10);

        //Contenu------------------------
        menue.getChildren().add(addExam);   //Ajouter un exam
        menue.getChildren().add(search);    //Rechercher un exam

        //Fonctionnement des différents buttons
        addExam.setOnAction(e ->
        {
            try {
                AjouteExam.Display();
            } catch (SQLException throwables)   //try catch ? Besoin de résoudre le problème pour rendre le code homogène
            {
                throwables.printStackTrace();
            }
        });


        search.setOnAction(e ->
        {
            try
            {
                Rechercher.Display();
            }catch (SQLException throwables)    //same energy here
            {
                throwables.printStackTrace();
            }
        });

        //Affichage
        Scene scene = new Scene(menue,350,250);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}

