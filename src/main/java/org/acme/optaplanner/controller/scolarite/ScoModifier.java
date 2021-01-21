package org.acme.optaplanner.controller.scolarite;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.acme.optaplanner.controller.MyDataSourceFactory;
import org.acme.optaplanner.view.ScoAccueil;

import java.sql.*;

import java.util.Optional;

public class ScoModifier {

    //implémentations

    static TextField heured = new TextField();
    static TextField heuref = new TextField();

    static int idTimeSlot;

    static Button Bannuler = new Button("Annuler");
    static Button Bmodifier = new Button("Modifier");

    static ComboBox<String> Dsalle = new ComboBox<String>();
    static ComboBox<String> Dexamen = new ComboBox<String>();

    static DatePicker Pdate = new DatePicker();

    static Label Lsalle = new Label("Salle libre : ");
    static Label Lexamen = new Label("Examen : ");
    static Label Ldate = new Label("Date : ");
    static Label Lheured = new Label("Heure de début : ");
    static Label Lheuref = new Label("Heure de fin : ");

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
        window.setTitle("Modifier");
        GridPane grid = new GridPane();
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        //contenu :

        //examen
        St= con.createStatement();
        Rs = St.executeQuery("SELECT nom FROM examen");
        while (Rs.next())
        {
            Dexamen.getItems().add((Rs.getString("nom")));
        }
        grid.add(Lexamen, 0, 0);
        grid.add(Dexamen, 1, 0);

        /*//date
        grid.add(Ldate, 0, 1);
        grid.add(Pdate, 1, 1);

        //heure
        grid.add(Lheured, 0, 2);
        grid.add(heured, 1, 2);

        grid.add(Lheuref, 0, 3);
        grid.add(heuref, 1, 3);*/

        //salle
        St= con.createStatement();
        Rs = St.executeQuery("SELECT nom FROM salle");
        while (Rs.next())
        {
            Dsalle.getItems().add((Rs.getString("nom")));
        }
        grid.add(Lsalle, 0, 4);
        grid.add(Dsalle, 1, 4);

        //boutons
        vbox.getChildren().add(Bmodifier);
        vbox.getChildren().add(Bannuler);

        /*set idTimeSlot
        St= con.createStatement();
        Rs = St.executeQuery("SELECT idTimeSlot FROM examen WHERE nom = '"+Dexamen.getSelectionModel().getSelectedItem()+"'");
        while (Rs.next())
        {
            idTimeSlot = Rs.getInt("idTimeSlot");
        }*/

        //fonctionnement des boutons
        Bmodifier.setOnAction(e ->
        {
            //modifie les valeurs ci-dessus, puis renvoi vers l'accueil
            try {
                // revoir système timeslot
                /*String requete = "UPDATE timeslots SET id = '"+idTimeSlot+"', Day = '"+Pdate.getValue()+"'" +
                        ", Start = '"+heured.getText()+"', End = '"+heuref.getText()+"'" +
                        "INNER JOIN examen ON timeslots.id = examen.idTimeSlot";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(requete);*/
                String requete2 = "UPDATE examen SET salle = '"+Dsalle.getSelectionModel().getSelectedItem()+"'" +
                        "WHERE nom = '"+Dexamen.getSelectionModel().getSelectedItem()+"'";
                Statement stmt2 = con.createStatement();
                stmt2.executeUpdate(requete2);
                ScoAccueil.Display();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


        Bannuler.setOnAction(x -> //demande confirmation pour annuler puis renvoi vers l'accueil
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
        Scene scene = new Scene(vbox, 300, 250);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}
