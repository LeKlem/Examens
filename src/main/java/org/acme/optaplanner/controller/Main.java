package org.acme.optaplanner.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import org.acme.optaplanner.controller.secretaria.Accueil;


public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Boucle tant que l'utilisateur se déconnecte pour permettre une reconnexion,
        * ne se produit pas quand l'utilisateur clique sur la croix rouge
        */
        boolean deco;
        do{
            deco = false;
            /*connectedAs contient le rôle (job) du compte au quel correspondent les logs
            * Les retours des méthodes .Display s'executent quand la fenètre est .close
             */
            String connectedAs = connexion.Display();
            System.out.println(connectedAs);
            switch (connectedAs)  {
                case ("Admin"):
                    deco = admin.Display();
                    break;
                case ("Secretariat"):
                    deco = Accueil.Display();
                    break;
                case ("Scolarité"):
                    scolarite.Display();
                    break;
            }
        }while(deco);
    }
}