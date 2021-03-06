    package org.acme.optaplanner.view;

    import javafx.application.Application;
    import javafx.scene.control.Alert;
    import javafx.stage.Stage;

    /**
     * <b>Main est la classe contenant le main permettant l'articulation des différentes parties entre-elles</b>
     * <p>
     * Elle pourra être amenée à appeller les interfaces suivantes
     * <ul>
     * <li>Connexion.</li>
     * <li>Admin.</li>
     * <li>Secretariat.</li>
     * <li>Scolarité</li>
     * </ul>
     * <p>
     * Cette classe fonctionne en jouant avec les paramètres récupérés d'une interface, et en appliquant des opérations selon les cas
     * Par exemple, connectedAs contient le rôle de l'utilisateur si le mot de passe et l'identifiant sont correctes, ce qui permet de
     * lancer l'interface associée.
     * </p>
     *
     *
     * @author Clement Eischen
     * @version 3.0
     */

    public class Main extends Application {

        /**
         * @param args
         */
        public static void main(String[] args) {
            launch();
        }

        /**
         * @param primaryStage
         * @throws Exception
         * Boucle tant que l'utilisateur se déconnecte pour permettre une reconnexion,
         * ne se produit pas quand l'utilisateur clique sur la croix rouge
         */

        @Override
        public void start(Stage primaryStage) throws Exception {
            boolean deco;
            do{
                deco = false;
                String connectedAs ="waiting";
                connectedAs = connexion.Display();
                switch (connectedAs)  {
                    case ("Admin"):
                        deco = admin.Display();
                        break;
                    case ("Secretariat"):
                        deco = Accueil.Display();
                        break;
                    case ("Scolarité"):
                        deco = ScoAccueil.Display();
                        break;
                    case ("error"):
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Aucune correspondance");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                        break;
                }
            }while(deco);
        }
    }