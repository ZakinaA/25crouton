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
        System.out.println ("Nombre de caserne = " + DaoCaserne.getLesCasernes(cnx).size());
        System.out.println ("Caserne 1 = " + DaoCaserne.getCaserneById(cnx,1).getNom());

      }
}
