package org.acme.optaplanner.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.acme.optaplanner.controller.MyDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class connexion {
    static String connectedAs;
    static TextField Login = new TextField();
    static TextField Password = new TextField();
    static Button valider = new Button("Valider");
    static Label Llog = new Label("Identifiant : ");
    static Label Lpsw = new Label("Mot de passe : ");
    static Label error = new Label("Aucune correspondance");
    static Connection con;

    public static String Display() throws SQLException {
        connectedAs = "";
        Stage window = new Stage();
        window.setTitle("Connexion");
        GridPane GRconn = new GridPane();

        /**
         *  Set up de la scène
         */
        GRconn.setAlignment(Pos.CENTER);
        GRconn.add(Llog, 0, 0);
        GRconn.add(Login, 1, 0);
        GRconn.add(Lpsw, 0, 1);
        GRconn.add(Password, 1, 1);
        GRconn.add(valider, 1, 2);
        con = MyDataSourceFactory.getConnection();
        valider.setOnAction(e -> {
            String login = Login.getText();
            String password = Password.getText();
            if (!(login.isEmpty() || password.isEmpty()))
                connectedAs = MyDataSourceFactory.Connecting(con, login, password);
            if(connectedAs != "")
                window.close();
        });
        Scene scene = new Scene(GRconn, 300, 150);
        window.setScene(scene); 
        window.showAndWait();
        return connectedAs;
    }
}