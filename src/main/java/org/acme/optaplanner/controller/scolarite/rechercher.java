package org.acme.optaplanner.controller.scolarite;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.acme.optaplanner.controller.MyDataSourceFactory;

import java.sql.*;

import java.util.Optional;

public class rechercher {

    //implémentations

    static Button Brechercher = new Button("Rechercher");
    static Button Bannuler = new Button("Annuler");

    static ComboBox<Integer> Dannee = new ComboBox<Integer>();
    static ComboBox<String> Dfiliere = new ComboBox<String>();
    static ComboBox<String> Dmatiere = new ComboBox<String>();
    static ComboBox<String> Dexamen = new ComboBox<String>();

    static Label Lannee = new Label("Année d'étude :  ");
    static Label Lfiliere = new Label("Filière : ");
    static Label Lmatiere = new Label("Matière : ");
    static Label Lexamen = new Label("Examen : ");

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
        window.setTitle("Rechercher");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        //contenu :

        //annee
        grid.add(Dannee, 1, 0);
        grid.add(Lannee, 0, 0);

        //filiere
        grid.add(Lfiliere, 0, 1);
        grid.add(Dfiliere, 1, 1);

        //matiere
        grid.add(Dmatiere, 1, 2);
        grid.add(Lmatiere, 0, 2);

        //examen
        grid.add(Dexamen, 1, 3);
        grid.add(Lexamen, 0, 3);

        //boutons
        vbox.getChildren().add(Brechercher);
        vbox.getChildren().add(Bannuler);

        //fonctionnement des boutons
        Brechercher.setOnAction(e ->
        {
            //recherche dans la bdd
            ResultSet rs = null;
            String requete = "SELECT annee, filiere, matiere, exam FROM examen";

            //attribue les valeurs des combobox pour la bdd, puis renvoi vers Info
            try {
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery(requete);
                info.Display(); //il faut que cela renvoi les infos en rapport avec la requete
                while (rs.next()) {
                    Integer annee = (Integer) Dannee.getValue();
                    annee = rs.getInt("annee");
                    String filiere = (String) Dfiliere.getValue();
                    filiere = rs.getString("filiere");
                    String matiere = (String) Dmatiere.getValue();
                    matiere = rs.getString("matiere");
                    String exam = (String) Dexamen.getValue();
                    exam = rs.getString("exam");
                }
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
                    accueil.Display();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        //affichage
        vbox.getChildren().add(grid);
        Scene scene = new Scene(vbox, 300, 220);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}
