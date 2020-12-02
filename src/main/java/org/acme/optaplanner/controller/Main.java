package org.acme.optaplanner.controller;

import javafx.application.Application;

import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        boolean deco;
        do{
            deco = false;
            String connectedAs = connexion.Display();
            switch (connectedAs)  {
                case ("Admin"):
                    deco = admin.Display();
                    break;
                case ("Secretariat"):
                    break;
                case ("Scolarite"):
                    break;
            }
        }while(deco);
    }
}