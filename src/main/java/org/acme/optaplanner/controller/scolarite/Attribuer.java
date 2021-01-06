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

public class Attribuer {

    //implémentations

    static TextField nom = new TextField();
    static TextField prenom = new TextField();

    static Button Bvalider = new Button("Valider");
    static Button Bannuler = new Button("Annuler");

    static ComboBox<String> filiere = new ComboBox<String>();

    static Label Lnom = new Label("Nom : ");
    static Label Lprenom = new Label("Prénom : ");
    static Label Lfiliere = new Label("Filiere : ");

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

        //eleve
        grid.add(Lnom, 0, 0);
        grid.add(nom, 1, 0);
        grid.add(Lprenom, 0, 1);
        grid.add(prenom, 1, 1);

        //filiere
        St= con.createStatement();
        Rs = St.executeQuery("SELECT filiere FROM examen");
        while (Rs.next())
        {
            filiere.getItems().add((Rs.getString("filiere")));
        }
        grid.add(Lfiliere, 0, 2);
        grid.add(filiere, 1, 2);

        //boutons
        vbox.getChildren().add(Bvalider);
        vbox.getChildren().add(Bannuler);

        //fonctionnement boutons
        Bvalider.setOnAction(e ->
        {
            //envoi les infos vers la bdd, puis renvoi vers l'accueil
            try {
                String requete = "UPDATE eleve SET filiere = '"+filiere.getSelectionModel().getSelectedItem()+"' " +
                        "WHERE nom = '"+nom.getText()+"' AND"+" prenom = '"+prenom.getText()+"'";

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