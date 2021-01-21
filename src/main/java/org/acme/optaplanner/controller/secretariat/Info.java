package org.acme.optaplanner.controller.secretariat;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.acme.optaplanner.view.Accueil;

import java.sql.SQLException;
import java.util.Optional;

public class Info
{
    //Implémentations des différents éléments
    static Label matiere = new Label("Matière :");
    static Label lMatiere = new Label();
    static Label filiere = new Label("Filières :");
    static Label lFiliere = new Label();
    static Label format = new Label("Format :");
    static Label lFormat = new Label();
    static Label salle = new Label("Salle :");
    static Label lSalle = new Label();
    static Label duree = new Label("Durée :");
    static Label lDuree = new Label();
    static Button modify = new Button("Modifier");
    static Button delete = new Button("Supprimer");
    static Button cancel = new Button("Annuler");
    static boolean exit;

    public static boolean Display() throws SQLException
    {
        exit = false;

        //Définition des containers-------------------
        Stage window = new Stage();
        window.setTitle("Informations sur l'examen");
        GridPane gpane = new GridPane();
        gpane.setAlignment(Pos.CENTER_LEFT);
        gpane.setVgap(10);
        gpane.setHgap(10);
        gpane.setTranslateX(20);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);

        //Contenue :

        //Matière--------------------
        gpane.add(matiere,0,0);
        gpane.add(lMatiere,1,0);

        //Filière----------------------
        gpane.add(filiere,0,1);
        gpane.add(lFiliere,1,1);

        //Format------------------------
        gpane.add(format,0,2);
        gpane.add(lFormat,1,2);

        //Salle------------------------
        gpane.add(salle,0,3);
        gpane.add(lSalle,1,3);

        //Duree---------------------
        gpane.add(duree,0,4);
        gpane.add(lDuree,1,4);

        //Buttons-----------------------
        hBox.getChildren().add(modify);
        hBox.getChildren().add(delete);
        hBox.getChildren().add(cancel);

        //Mise en page-------------------
        vBox.getChildren().add(gpane);
        vBox.getChildren().add(hBox);

        //Fonctionnement des différents boutons
        modify.setOnAction(e ->
        {
            try
            {
                Modifier.Display();
            }catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        });

        delete.setOnAction(e ->
        {
            //connecter la bdd et faire en sorte que l'examen soit delete de celle-ci
        });

        cancel.setOnAction(e ->
        {
            //Initialisation d'une alerte + affichage
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Que faire ?");
            ButtonType buttonTypeAccueil = new ButtonType("Accueil");
            ButtonType buttonTypeRecherche = new ButtonType("Rechercher un Exemen");
            ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeAccueil,buttonTypeRecherche,buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == buttonTypeAccueil)
            {
                try { exit = true; Accueil.Display(); }catch(SQLException throwables) { throwables.printStackTrace(); }

            }else if(result.get() == buttonTypeRecherche)
            {
                try{exit = true; Rechercher.Display(); }catch(SQLException throwables) { throwables.printStackTrace();  }
            }
        });

        //Affichage de la fenêtre
        Scene scene = new Scene(vBox,350,250);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}

