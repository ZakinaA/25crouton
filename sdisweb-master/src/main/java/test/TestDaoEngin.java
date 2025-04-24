package test;

import database.ConnexionBdd;
import database.DaoEngin;
import model.Engin;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestDaoEngin {
    public static void main(String[] args) {
        Connection connection = ConnexionBdd.ouvrirConnexion();
        
        if (connection != null) {
            try {
                DaoEngin daoEngin = new DaoEngin(connection); 

                
                System.out.println("Test de récupération de tous les engins :");
                List<Engin> engins = daoEngin.getLesEngins();
                for (Engin engin : engins) {
                    System.out.println("ID: " + engin.getId() + ", Type: " + engin.getType().getLibelle());
                }

               
                int testId = 1; 
                System.out.println("\nTest de récupération d'un engin par ID: " + testId);
                Engin engin = daoEngin.getEnginById(testId);
                if (engin != null) {
                    System.out.println("ID: " + engin.getId() + ", Type: " + engin.getType().getLibelle());
                } else {
                    System.out.println("Aucun engin trouvé avec cet ID.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnexionBdd.fermerConnexion(connection);  
            }
        } else {
            System.out.println("Échec de la connexion à la base de données.");
        }
    }
}
