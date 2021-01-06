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

public class ScoRechercher {

    //implémentations

    static Button Brechercher = new Button("Rechercher");
    static Button Bannuler = new Button("Annuler");

    static ComboBox<Integer> Dannee = new ComboBox<Integer>();
    static ComboBox<String> Dfiliere = new ComboBox<String>();
    static ComboBox<String> Dmatiere = new ComboBox<String>();
    static ComboBox<String> Dexamen = new ComboBox<String>();

    static int id;

    static String nomExam;

    static Label Lannee = new Label("Année d'étude :  ");
    static Label Lfiliere = new Label("Filière : ");
    static Label Lmatiere = new Label("Matière : ");
    static Label Lexamen = new Label("Examen : ");

    static Connection con;
    static boolean exit;
    private static Statement St;
    private static ResultSet Rs;


    public static boolean Display() throws SQLException, ClassNotFoundException {

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
        St= con.createStatement();
        Rs = St.executeQuery("SELECT annee FROM examen");
        while (Rs.next())
        {
            //Pour affecter une valeur de base de données à un Combobox
            Dannee.getItems().add(Integer.valueOf(Rs.getString("annee")));
        }

        grid.add(Dannee, 1, 0);
        grid.add(Lannee, 0, 0);

        //filiere
        St= con.createStatement();
        Rs = St.executeQuery("SELECT filiere FROM examen");
        while (Rs.next())
        {
            Dfiliere.getItems().add((Rs.getString("filiere")));
        }
        grid.add(Lfiliere, 0, 1);
        grid.add(Dfiliere, 1, 1);

        //matiere
        St= con.createStatement();
        Rs = St.executeQuery("SELECT matiere FROM examen");
        while (Rs.next())
        {
            Dmatiere.getItems().add((Rs.getString("matiere")));
        }
        grid.add(Dmatiere, 1, 2);
        grid.add(Lmatiere, 0, 2);

        //examen
        St= con.createStatement();
        Rs = St.executeQuery("SELECT nom FROM examen");
        while (Rs.next())
        {
            Dexamen.getItems().add((Rs.getString("nom")));
        }
        grid.add(Dexamen, 1, 3);
        grid.add(Lexamen, 0, 3);

        //id
        nomExam = Dexamen.getSelectionModel().getSelectedItem();
        String s = "SELECT id FROM examen WHERE nom = ?";
        PreparedStatement pst = con.prepareStatement(s);
        pst.setString(1, nomExam);
        Rs = pst.executeQuery();
        while (Rs.next())
        {
            id = Rs.getInt("id");
        }

        //boutons
        vbox.getChildren().add(Brechercher);
        vbox.getChildren().add(Bannuler);

        //fonctionnement des boutons
        Brechercher.setOnAction(e ->
        {
            try {
                ScoInfo.Display();
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
        Scene scene = new Scene(vbox, 300, 220);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}
