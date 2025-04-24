package test;

import database.ConnexionBdd;
import database.DaoCaserne;
import java.sql.Connection;
import java.util.ArrayList;
import model.Caserne;

public class TestDaoCaserne {

    public static void main(String args[]) {

        Connection cnx = ConnexionBdd.ouvrirConnexion();

        ArrayList<Caserne> lesCasernes = DaoCaserne.getLesCasernes(cnx);

        if (lesCasernes != null && !lesCasernes.isEmpty()) {
            for (Caserne caserne : lesCasernes) {
                System.out.println("Nom de la caserne : " + caserne.getNom());
            }
            System.out.println("Nombre total de casernes : " + lesCasernes.size());
        } else {
            System.out.println("Aucune caserne trouvée.");
        }

        ConnexionBdd.fermerConnexion(cnx);
    }
}