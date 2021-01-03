package org.acme.optaplanner.controller.secretariat;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

public class Modifier
{
    //Implémentations des Contenues
    static Label matiere = new Label("Matière :");
    static ComboBox comboMatiere = new ComboBox();
    static Label anneeEtu = new Label("Année d'étude :");
    static ComboBox comboAnnee = new ComboBox();
    static Label filiere = new Label("Filière :");
    static Button bFiliere = new Button("+");
    static Label duree = new Label("Durée :");
    static TextField tfDuree = new TextField();
    static Label format = new Label("Format :");
    static ToggleGroup gFormat = new ToggleGroup(); // <--- permet de grouper les 2 radio boutons
    static RadioButton radFormat1 = new RadioButton("Ecrit");
    static RadioButton radFormat2 = new RadioButton("Oral");
    static Label salle = new Label("Salle :");
    static ComboBox comboSalle = new ComboBox();
    static Button modify = new Button("Modifier");
    static Button cancel = new Button("Annuler");
    static boolean exit;


    public static boolean  Display() throws SQLException
    {
        exit = false;

        //Container ---------------------------
        Stage window = new Stage();
        window.setTitle("Modifier un Examen");

        GridPane gpane = new GridPane();
        gpane.setAlignment(Pos.CENTER);
        gpane.setHgap(10);
        gpane.setVgap(10);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(25);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        //Contenue :

        //Matière ------------------------------
        gpane.add(matiere, 0, 0);
        gpane.add(comboMatiere, 1, 0);

        //Année d'étude ----------------------
        gpane.add(anneeEtu, 0, 1);
        gpane.add(comboAnnee, 1, 1);

        //Filière -------------------------
        gpane.add(filiere, 0, 2);
        gpane.add(bFiliere, 1, 2);

        //Durée--------------------------------
        /*
        essayer de voir si il n'y  a pas moyen
        d'implémenter un formulaire d'horaire au
        lieu d'un simple TextField
         */
        gpane.add(duree, 0, 3);
        gpane.add(tfDuree, 1, 3);

        //Format------------------------------
        gpane.add(format, 0, 4);
        radFormat1.setToggleGroup(gFormat);
        radFormat2.setToggleGroup(gFormat);
        gpane.add(radFormat1, 1, 4);
        gpane.add(radFormat2, 2, 4);

        //Salle-----------------------------
        gpane.add(salle, 0, 5);
        gpane.add(comboSalle, 1, 5);

        //Boutons--------------------------
        hbox.getChildren().add(modify);
        hbox.getChildren().add(cancel);

        //Mise en forme------------------------------------
        vbox.getChildren().add(gpane);
        vbox.getChildren().add(hbox);

        //Fonctionnement des différents boutons---------------
        bFiliere.setOnAction(e ->
            {

            });

        modify.setOnAction(e ->
        {
            //connecter avec la bdd pour envoyer les infos
        });

        cancel.setOnAction(e ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes vous sur de vouloir annuler ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                exit = true;
                window.close();
            }
        });

        Scene scene = new Scene(vbox,350,250);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}
