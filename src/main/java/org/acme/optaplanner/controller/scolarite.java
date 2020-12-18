package org.acme.optaplanner.controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class scolarite {
    static TextField nomE = new TextField();
    static TextField prenomE = new TextField();
    static TextField salle = new TextField();
    static TextField contrainte = new TextField();
    static TextField filiere = new TextField();
    static TextField matiere = new TextField();
    static TextField examen = new TextField();
    static TextField date = new TextField();
    static TextField heure = new TextField();

    static Button Bvalider = new Button("Valider");
    static Button Battribuer = new Button("Attribuer");
    static Button Bcalendrier = new Button("Calendrier");
    static Button Brechercher = new Button("Rechercher");
    static Button Bannuler = new Button("Annuler");
    static Button Bmodifier = new Button("Modifier");

    static ComboBox<String> Dannee = new ComboBox<String>();
    static ComboBox<String> Dmatiere = new ComboBox<String>();
    static ComboBox<String> Dexamen = new ComboBox<String>();
    static ComboBox<String> Dsalle = new ComboBox<String>();

    static DatePicker Pdate = new DatePicker();

    static Label Lnom = new Label("Nom de l'élève :  ");
    static Label Lprenom = new Label("Prénom de l'élève : ");
    static Label Lsalle = new Label("Salle libre : ");
    static Label Lcontrainte = new Label("Contrainte : ");
    static Label Lannee = new Label("Année d'étude :  ");
    static Label Lfiliere = new Label("Filière : ");
    static Label Lmatiere = new Label("Matière : ");
    static Label Lexamen = new Label("Examen : ");
    static Label Ldate = new Label("Date : ");
    static Label Lheure = new Label("Heure : ");


    public static void Display() throws SQLException {
        Stage window = new Stage();
        window.setTitle("Connexion");
        GridPane grid = new GridPane();

        grid.add(Battribuer, 0, 0);
        grid.add(Bcalendrier, 1, 0);
        grid.add(Brechercher, 2, 0);

        Scene scene = new Scene(grid, 300, 150);

        Battribuer.setOnAction(e -> window.setScene(getAScene()));
        window.setScene(scene);
        window.showAndWait();

       /* Bcalendrier.setOnAction(e -> window.setScene(getCScene()));
        window.setScene(scene);
        window.showAndWait(); */ //pas encore de scene calendrier

        Brechercher.setOnAction(e -> window.setScene(getRScene()));
        window.setScene(scene);
        window.showAndWait();
    }
    public static Scene getRScene(){

        Stage window = new Stage();
        window.setTitle("Recherche");
        GridPane grid = new GridPane();

        grid.add(Dannee, 1, 0);
        grid.add(Lannee, 0, 0);
        grid.add(Lfiliere, 0, 1);
        grid.add(Dmatiere, 1, 2);
        grid.add(Lmatiere, 0, 2);
        grid.add(Dexamen, 1, 3);
        grid.add(Lexamen, 0, 3);
        grid.add(Brechercher, 1, 4);
        grid.add(Bannuler, 2, 4);

        Scene sceneRechercher = new Scene(grid, 300, 150);

        Brechercher.setOnAction(e -> window.setScene(getIScene()));
        window.setScene(sceneRechercher);
        window.showAndWait();

        Bannuler.setOnAction(e -> window.setScene(getRScene()));
        window.setScene(sceneRechercher);
        window.showAndWait();

       /* Bvalider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
            }
        }); */
        return sceneRechercher;

    }

    public static Scene getIScene(){

        Stage window = new Stage();
        window.setTitle("Info");
        GridPane grid = new GridPane();

        grid.add(Lexamen, 0, 0);
        grid.add(examen, 1, 0);
        grid.add(Ldate, 0, 1);
        grid.add(date, 1, 1);
        grid.add(Lheure, 0, 2);
        grid.add(heure, 1, 2);
        grid.add(Lsalle, 0, 3);
        grid.add(salle, 1, 3);
        grid.add(Battribuer, 0, 4);
        grid.add(Bmodifier, 1, 4);
        grid.add(Bannuler, 2, 4);

        Scene sceneInfo = new Scene(grid, 300, 150);

        Battribuer.setOnAction(e -> window.setScene(getAScene()));
        window.setScene(sceneInfo);
        window.showAndWait();

        Bmodifier.setOnAction(e -> window.setScene(getMoScene()));
        window.setScene(sceneInfo);
        window.showAndWait();

        Bannuler.setOnAction(e -> window.setScene(getRScene()));
        window.setScene(sceneInfo);
        window.showAndWait();


      /*  Battribuer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
            }
        }); */
        return sceneInfo;

    }



    public static Scene getAScene(){

        Stage window = new Stage();
        window.setTitle("Attribution");
        GridPane grid = new GridPane();

        grid.add(Lexamen, 0, 0);
        grid.add(examen, 1, 0);
        grid.add(Ldate, 0, 1);
        grid.add(Pdate, 1, 1);
        grid.add(Lheure, 0, 2);
        grid.add(heure, 1, 2);
        grid.add(Lsalle, 0, 3);
        grid.add(Dsalle, 1, 3);
        grid.add(Bvalider, 0, 4);
        grid.add(Bannuler, 1, 4);

//SCENE ATTRIBUER
        Scene sceneAttribuer = new Scene(grid, 300, 150);

        Battribuer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
            }
        });

        Bannuler.setOnAction(e -> window.setScene(getIScene()));
        window.setScene(sceneAttribuer);
        window.showAndWait();

        return sceneAttribuer;

    }

    public static Scene getMoScene(){

        Stage window = new Stage();
        window.setTitle("Modification");
        GridPane grid = new GridPane();

        grid.add(Lexamen, 0, 0);
        grid.add(examen, 1, 0);
        grid.add(Ldate, 0, 1);
        grid.add(Pdate, 1, 1);
        grid.add(Lheure, 0, 2);
        grid.add(heure, 1, 2);
        grid.add(Lsalle, 0, 3);
        grid.add(Dsalle, 1, 3);
        grid.add(Bmodifier, 0, 4);
        grid.add(Bannuler, 1, 4);

//SCENE MODIFIER
        Scene sceneModif = new Scene(grid, 300, 150);

        Battribuer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
            }
        });

        Bannuler.setOnAction(e -> window.setScene(getIScene()));
        window.setScene(sceneModif);
        window.showAndWait();

        return sceneModif;

    }
}
