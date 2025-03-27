/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import database.ConnexionBdd;
import database.DaoCaserne;
import model.Caserne;
import java.sql.Connection;

/**
 *
 * @author zakina
 */
public class TestDaoCaserne {
    
      public static void main (String args[]){
        
        Connection cnx = ConnexionBdd.ouvrirConnexion();
        System.out.println ("nombre de casernes=" + DaoCaserne.getLesCasernes(cnx).size());
        
        System.out.println ("Le caserne 1 s'appelle =" + DaoCaserne.getCaserneById(cnx,1).getNom());
        
        Caserne c = new Caserne();
        c.setId(4);
        c.setNom("CHAUVEL");
        c.setRue("14 rue");
        c.setCps("14000");
        c.setVille("ifs");

        System.out.println("le nouveau pompier a reçu l id = " + c.getId());

      }
}
