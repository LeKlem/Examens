package org.acme.optaplanner.controller.secretariat;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Rechercher
{
    //Implémentation des différents éléments
    static Label matiere = new Label("Matière :");
    static ComboBox comboMatiere = new ComboBox();
    static Label anneeEtu = new Label("Année d'étude");
    static ComboBox comboEtu = new ComboBox();
    static Label examen = new Label("Examens");
    static ComboBox comboExamen = new ComboBox();
    static Button valider = new Button("Valider");
    static  Button cancel = new Button("Annuler");
    static boolean exit;

    public static boolean Display() throws SQLException
    {
        exit = false;
        //Containers----------------------------
        Stage window = new Stage();
        window.setTitle("Rechercher un examen");
        GridPane gpane = new GridPane();
        gpane.setAlignment(Pos.CENTER);
        gpane.setHgap(10);
        gpane.setVgap(10);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);


        //Contenue
        //Matière---------------------------
        gpane.add(matiere,0,0);
        gpane.add(comboMatiere,13,0);

        //Ligne année d'étude----------------
        gpane.add(anneeEtu,0,1);
        gpane.add(comboEtu,1,1);

        //Ligne Exemens---------------------
        gpane.add(examen,0,2);
        gpane.add(comboExamen,1,2);

        //Bouton valider et annuler----------
        hbox.getChildren().add(valider);
        hbox.getChildren().add(cancel);

        //Mise en forme de la page------------
        vbox.getChildren().add(gpane);
        vbox.getChildren().add(hbox);

        //Fonctionnement des différents boutons
        valider.setOnAction(e ->
        {
            //connection avec la bdd a faire
            try
            {
                Info.Display();
            }catch(SQLException throwables)     //cf Acceuil
            {
                throwables.printStackTrace();
            }
        });

        cancel.setOnAction(e ->
        {
            exit = true;
            window.close();
        });

        //Affichage
        Scene scene = new Scene(vbox,350,250);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}

