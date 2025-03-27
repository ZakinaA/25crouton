package test;

import model.Engin;
import model.TypeEngin;

public class TestEngin {
    public static void main(String[] args) {

        TypeEngin type1 = new TypeEngin(1, "Camion Citerne", 1);

        Engin engin1 = new Engin(1, type1);

        System.out.println("Type de l'engin : " + engin1.getType().getLibelle());
        System.out.println("ID de l'engin : " + engin1.getId());
        System.out.println("ID du type : " + type1.getId());
        System.out.println("Libellé du type : " + type1.getLibelle());
    }
}
