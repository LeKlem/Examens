package org.acme.optaplanner.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.acme.optaplanner.controller.MyDataSourceFactory;
import org.acme.optaplanner.controller.PasswordUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class admin {
    static TextField nom = new TextField();
    static TextField prenom = new TextField();
    static TextField Login = new TextField();
    static TextField Password = new TextField();
    static Label Poste = new Label("Poste : ");
    static Button deco = new Button("Déconnexion");
    static Button valider = new Button("Valider");
    static Label Lnom = new Label("Nom :  ");
    static Label Lprenom = new Label("Prénom : ");
    static Label Llog = new Label("Identifiant : ");
    static Label Lpsw = new Label("Mot de passe : ");
    static ComboBox<String> combo = new ComboBox<String>();
    static boolean exit;
    static Connection con;

    public static boolean Display() throws SQLException {
        exit = false;
        combo.getItems().addAll("Secrétariat", "Scolarité");
        combo.setValue("Selectionner une valeur");
        Stage window = new Stage();
        window.setTitle("Connexion");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(Lnom, 0, 0);
        grid.add(nom, 1, 0);
        grid.add(Lprenom, 0, 1);
        grid.add(prenom, 1, 1);
        grid.add(Llog, 0, 2);
        grid.add(Login, 1, 2);
        grid.add(Lpsw, 0, 3);
        grid.add(Password, 1, 3);
        grid.add(Poste, 0, 4);
        grid.add(combo, 1, 4);
        grid.add(deco, 0 ,5);
        grid.add(valider , 1, 5);
        con = MyDataSourceFactory.getConnection();
        valider.setOnAction(e -> {
            int count = 0;
            try {
                 count = MyDataSourceFactory.getNumberOfUser(con);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String job = combo.getValue();
            if(!job.equals("Selectionner une valeur")) {
                String log = Login.getText();
                String psw = Password.getText();
                String n =  nom.getText();
                String p = prenom.getText();
                psw = PasswordUtils.HashPassword(psw);
                if(!( log.isEmpty() || psw.isEmpty() || n.isEmpty() || p.isEmpty()))
                    MyDataSourceFactory.CreateAccount(con, log, psw, n, p, job);
                try {
                    System.out.println(count);
                    System.out.println(MyDataSourceFactory.getNumberOfUser(con));
                    if(count == (MyDataSourceFactory.getNumberOfUser(con) -  1))
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Compte crée avec succès!");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                nom.setText("");
                prenom.setText("");
                Login.setText("");
                Password.setText("");
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Erreur : aucune action effectuée");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });
        deco.setOnAction(e -> {
            exit = true;
            window.close();
        });
       // deco.setStyle("text-color : blue");
        Scene scene = new Scene(grid, 400, 200 );
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}