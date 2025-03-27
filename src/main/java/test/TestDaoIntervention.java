package test;

import database.ConnexionBdd;
import database.DaoIntervention;
import java.sql.Connection;
import model.Intervention;
import model.Situation;
import java.util.Date;
import java.sql.Time;

/**
 *
 * @author zakina
 */
public class TestDaoIntervention {

    public static void main(String args[]) {

        Connection cnx = ConnexionBdd.ouvrirConnexion();
        System.out.println("nombre d'interventions=" + DaoIntervention.getLesInterventions(cnx).size());

        Intervention intervention = DaoIntervention.getInterventionById(cnx, 1);
        if (intervention != null) {
            System.out.println("L'intervention 1 a pour lieu = " + intervention.getLieu());
        } else {
            System.out.println("L'intervention 1 n'a pas été trouvée.");
        }

       
        
    }
}