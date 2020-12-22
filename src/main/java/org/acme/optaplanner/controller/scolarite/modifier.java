package org.acme.optaplanner.controller.scolarite;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.acme.optaplanner.controller.MyDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Optional;

public class modifier {

    //implémentations

    static TextField examen = new TextField();
    static TextField heure = new TextField();

    static Button Bannuler = new Button("Annuler");
    static Button Bmodifier = new Button("Modifier");

    static ComboBox<String> Dsalle = new ComboBox<String>();

    static DatePicker Pdate = new DatePicker();

    static Label Lsalle = new Label("Salle libre : ");
    static Label Lexamen = new Label("Examen : ");
    static Label Ldate = new Label("Date : ");
    static Label Lheure = new Label("Heure : ");

    static Connection con;
    static boolean exit;


    public static boolean Display() throws SQLException {

        exit = false;

        //connexion
        con = MyDataSourceFactory.getConnection();

        //container
        Stage window = new Stage();
        window.setTitle("Modifier");
        GridPane grid = new GridPane();
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        //contenu :

        //examen
        grid.add(Lexamen, 0, 0);
        grid.add(examen, 1, 0);

        //date
        grid.add(Ldate, 0, 1);
        grid.add(Pdate, 1, 1);

        //heure
        grid.add(Lheure, 0, 2);
        grid.add(heure, 1, 2);

        //salle
        grid.add(Lsalle, 0, 3);
        grid.add(Dsalle, 1, 3);

        //boutons
        vbox.getChildren().add(Bmodifier);
        vbox.getChildren().add(Bannuler);

        //fonctionnement des boutons
        Bmodifier.setOnAction(e ->
        {

            //modifie dans la bdd et retourne à Info

            try {
                info.Display();
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
        Scene scene = new Scene(vbox, 300, 250);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}
