package org.acme.optaplanner.controller.scolarite;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.acme.optaplanner.controller.MyDataSourceFactory;

import java.sql.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

public class DispoSalle {

    //implémentations

    static Button Bvalider = new Button("Valider");
    static Button Bannuler = new Button("Annuler");

    static ComboBox<String> salle = new ComboBox<String>();

    static RadioButton b1 = new RadioButton("Disponible");
    static RadioButton b2 = new RadioButton("Indisponible");

    static Label Lsalle = new Label("Salle : ");
    static Label Lcapacite = new Label("Capacité : ");

    static TextField capacite = new TextField();

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
        window.setTitle("Attribuer");
        GridPane grid = new GridPane();
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        //contenu :

        //salle
        St= con.createStatement();
        Rs = St.executeQuery("SELECT nom FROM salle");
        while (Rs.next())
        {
            salle.getItems().add((Rs.getString("nom")));
        }
        grid.add(Lsalle, 0, 0);
        grid.add(salle, 1, 0);

        //capacité
        grid.add(Lcapacite, 0, 1);
        grid.add(capacite, 1, 1);

        //choix statut salle
        vbox.getChildren().add(b1);
        vbox.getChildren().add(b2);

        //boutons
        vbox.getChildren().add(Bvalider);
        vbox.getChildren().add(Bannuler);

        //fonctionnement boutons
        Bvalider.setOnAction(e ->
        {
            //envoi les infos vers la bdd, puis renvoi vers l'accueil
            try {
                String requete;
                if (b1.isSelected()) {
                    requete = "UPDATE salle SET statut = 'Disponible', capacité = '"+capacite.getText()+"'" +
                            "WHERE nom = '"+salle.getSelectionModel().getSelectedItem()+"'";
                } else {
                    requete = "UPDATE salle SET statut = 'Indisponible', capacité = '"+capacite.getText()+"'" +
                            "WHERE nom = '"+salle.getSelectionModel().getSelectedItem()+"'";
                }
                Statement stmt = con.createStatement();
                stmt.executeUpdate(requete);
                ScoAccueil.Display();

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
        Scene scene = new Scene(vbox, 300, 200);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}