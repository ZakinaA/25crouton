package test;

import model.Caserne;
import model.Engin;
import model.TypeEngin;

public class TestCaserne {

    public static void main(String[] args) {

        Caserne caserne1 = new Caserne(1, "Centre Ville");
        System.out.println("Caserne créée : ID = " + caserne1.getId() + ", Nom = " + caserne1.getNom());

  
        TypeEngin typeCamion = new TypeEngin(1, "Camion Citerne", 1);
        Engin engin1 = new Engin(1, typeCamion);

        // Assignation d'un engin à la caserne
        caserne1.setEngin(engin1);
        if (caserne1.getEngin() != null) {
            System.out.println("Engin affecté à la caserne : ID = " + caserne1.getEngin().getId() + ", Type = " + caserne1.getEngin().getType().getLibelle());
        } else {
            System.out.println("Aucun engin affecté à la caserne.");
        }

     
    }
}