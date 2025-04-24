/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import database.ConnexionBdd;
import database.DaoSurgrade;
import java.sql.Connection;
import java.util.ArrayList;
import model.Surgrade;

public class TestDaoSurgrade {

    public static void main(String[] args) {
        Connection cnx = ConnexionBdd.ouvrirConnexion();

        ArrayList<Surgrade> listeSurgrades = DaoSurgrade.getLesSurgrades(cnx);

        System.out.println("Liste des Grades ");

        for (Surgrade s : listeSurgrades) {
            System.out.println("ID : " + s.getId());
            System.out.println("Libellé : " + s.getLibelle());
            System.out.println("Grade ID : " + s.getGrade_id());
            
            
        }
    }
}
