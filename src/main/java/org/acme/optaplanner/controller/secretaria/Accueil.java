package org.acme.optaplanner.controller.secretaria;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.SQLException;

/**
 * <b>Accueil est la classe permettant de choisirs si l'utilisateur veux créer ou rechercher un Examen</b>
 * <p>
 *     Elle pourra être ammenée à appeller les interfaces suivantes :
 *     <ul>
 *         <li>AjouteExam.</li>
 *         <li>Rechercher.</li>
 *     </ul>
 *
 *
 *
 * @author Nathan Chapelle
 * @version 1.0
 */
public class Accueil
{
    //Implémentation des différents éléments
    static Button addExam = new Button("Ajouter un examen");
    static Button search = new Button("Rechercher un examen");
    static boolean exit;

    /**
     * Affichage de l'interface graphique
     * @return  Si true ou false en fonction de si il a quitté la page
     * @throws SQLException si jamais il y a un problème avec la base de donnée
     */
    public static boolean Display() throws SQLException
    {
        exit = false;

        //Container------------------
        Stage window = new Stage();
        window.setTitle("Accueil");
        VBox menue = new VBox();
        menue.setAlignment(Pos.CENTER);
        menue.setSpacing(10);

        //Contenu------------------------
        menue.getChildren().add(addExam);   //Ajouter un exam
        menue.getChildren().add(search);    //Rechercher un exam

        //Fonctionnement des différents buttons
        addExam.setOnAction(e ->
        {
            try {
                AjouteExam.Display();
            } catch (SQLException throwables)   //try catch ? Besoin de résoudre le problème pour rendre le code homogène
            {
                throwables.printStackTrace();
            }
        });

        search.setOnAction(e ->
        {
            try
            {
                Rechercher.Display();
            }catch (SQLException throwables)    //same energy here
            {
                throwables.printStackTrace();
            }
        });

        //Affichage
        Scene scene = new Scene(menue,350,250);
        window.setScene(scene);
        window.showAndWait();
        return exit;
    }
}

