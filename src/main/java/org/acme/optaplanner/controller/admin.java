package org.acme.optaplanner.controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
        combo.getItems().addAll("Secrétaria", "Scolarité");
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
        grid.add(valider, 0 ,5);
        grid.add(deco , 1, 5);
        con = MyDataSourceFactory.getConnection();
        valider.setOnAction(e -> {
            String job = combo.getValue();
            if(!job.equals("Selectionner une valeur")) {
                String log = Login.getText();
                String psw = Password.getText();
                String n =  nom.getText();
                String p = prenom.getText();
                psw = PasswordUtils.HashPassword(psw);
                if(!( log.isEmpty() || psw.isEmpty() || n.isEmpty() || p.isEmpty()))
                    MyDataSourceFactory.CreateAccount(con, log, psw, n, p, job);
                nom.setText("");
                prenom.setText("");
                Login.setText("");
                Password.setText("");
            }else{
                //Signaler erreur d'une manière sympa
            }
        });
        deco.setOnAction(e -> {
            exit = true;
            window.close();
        });
        Scene scene = new Scene(grid, 300, 150);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}