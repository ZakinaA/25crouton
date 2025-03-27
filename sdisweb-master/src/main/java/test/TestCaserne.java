package test;

import model.Caserne;
import model.Engin;
import model.TypeEngin;
import java.util.ArrayList;

public class TestCaserne {
    public static void main(String[] args) {
        Caserne caserne1 = new Caserne(1, "Caserne Centrale");

        TypeEngin typeCamion = new TypeEngin(1, "Camion Pompier", 2);
        TypeEngin typeEchelle = new TypeEngin(2, "Camion Échelle", 1);

        Engin engin1 = new Engin(101, typeCamion, caserne1);
        Engin engin2 = new Engin(102, typeEchelle, caserne1);

        caserne1.addEngin(engin1);
        caserne1.addEngin(engin2);
        System.out.println("Caserne: " + caserne1.getNom());

        ArrayList<Engin> engins = caserne1.getLesEngins();

        if (engins.isEmpty()) {
            System.out.println("Aucun engin dans cette caserne.");
        } else {
            System.out.println("Liste des engins :");
            for (Engin e : engins) {
                System.out.println("  Engin ID: " + e.getId() + ", Type: " + e.getType().getLibelle());
            }
        }
    }
}
