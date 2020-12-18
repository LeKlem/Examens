package org.acme.optaplanner.controller.secretaria;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

public class AjouteExam
{
    //Implémentations des différents éléments --------------
    static Label matiere = new Label("Matière :");
    static Label anneeEtu = new Label("Année d'étude :");
    static ComboBox comboMatiere = new ComboBox();
    static ComboBox comboAnnee = new ComboBox();
    static Label filiere = new Label("Filière :");
    static Button bFiliere = new Button("+");
    static Label duree = new Label("Durée :");
    static TextField tfDuree = new TextField();
    static  Label format = new Label("Format :");
    static Label salle = new Label("Salle :");
    static ToggleGroup gFormat = new ToggleGroup();
    static RadioButton radFormat1 = new RadioButton("Ecrit");
    static RadioButton radFormat2 = new RadioButton("Oral");
    static ComboBox comboSalle = new ComboBox();
    static Button add = new Button("Ajouter");
    static Button cancel = new Button("Annuler");
    static boolean exit;


    public static boolean Display() throws SQLException
    {
        exit  = false;

        //Containers-----------------------
        GridPane gpane = new GridPane();
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        exit = false;
        Stage window = new Stage();
        window.setTitle("Ajout d'un exemen");
        gpane.setAlignment(Pos.CENTER);
        gpane.setHgap(10);
        gpane.setVgap(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(25);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        //Contenue :

        //Matières-------------------------
        gpane.add(matiere, 0, 0);
        gpane.add(comboMatiere, 1, 0);

        //Année d'étude ------------------
        gpane.add(anneeEtu, 0, 1);
        gpane.add(comboAnnee, 1, 1);

        //Filières------------------------
        gpane.add(filiere, 0, 2);
        gpane.add(bFiliere, 1, 2);

        //Duree----------------------------
        gpane.add(duree, 0, 3);
        gpane.add(tfDuree, 1, 3);

        //Format-----------------------------
        gpane.add(format, 0, 4);
        radFormat1.setToggleGroup(gFormat);
        radFormat2.setToggleGroup(gFormat);
        gpane.add(radFormat1, 1, 4);
        gpane.add(radFormat2, 2, 4);
        gpane.add(salle, 0, 5);
        gpane.add(comboSalle, 1, 5);

        //Boutons----------------------------
        hbox.getChildren().add(add);
        hbox.getChildren().add(cancel);

        //Fonctionnement des Boutons
        bFiliere.setOnAction(e ->
        {
            try {
                Filiere.Display();
            } catch (SQLException throwables) //cf Acceuil
            {
                throwables.printStackTrace();
            }
        });

        add.setOnAction(e ->
        {
            //ajoute a la bdd
        });

        cancel.setOnAction(e ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes vous sur de vouloir annuler ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                exit = true;
                window.hide();
            }
        });

        //Affichage---------------------------------------
        vbox.getChildren().add(gpane);
        vbox.getChildren().add(hbox);
        Scene scene = new Scene(vbox, 350, 250);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }


}





