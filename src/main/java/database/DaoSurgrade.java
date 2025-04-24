/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import static database.DaoPompier.requeteSql;
import static database.DaoPompier.resultatRequete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Caserne;
import model.Grade;
import model.Pompier;
import model.Surgrade;

/**
 *
 * @author zakina
 */
public class DaoSurgrade {
    
    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;
    
    public static ArrayList<Surgrade> getLesSurgrades(Connection cnx) {

    ArrayList<Surgrade> surGrades = new ArrayList<>();

    try {
        requeteSql = cnx.prepareStatement(
            "SELECT " +
            "surgrade.id AS s_id, " +
            "surgrade.libelle AS s_libelle, " +
            "surgrade.grade_id AS s_grade_id, " +
            "g.id AS g_id, " +
            "g.libelle AS g_libelle " +
            "FROM surgrade " +
            "INNER JOIN grade g ON surgrade.grade_id = g.id"
        );

        resultatRequete = requeteSql.executeQuery();

        while (resultatRequete.next()) {

            Grade g = new Grade();
            g.setId(resultatRequete.getInt("g_id"));
            g.setLibelle(resultatRequete.getString("g_libelle"));

            Surgrade s = new Surgrade();
            s.setId(resultatRequete.getInt("s_id"));
            s.setLibelle(resultatRequete.getString("s_libelle"));
            s.setGrade_id(resultatRequete.getInt("s_grade_id"));
           

            surGrades.add(s);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("La requête de getLesSurgrades a généré une erreur");
    }

    return surGrades;
}
    
    public static Surgrade getSurgradeById(Connection cnx, int idSurgrade) {

    Surgrade s = null;

    try {
        requeteSql = cnx.prepareStatement(
            "SELECT " +
            "surgrade.id AS s_id, " +
            "surgrade.libelle AS s_libelle, " +
            "surgrade.grade_id AS s_grade_id, " +
            "g.id AS g_id, " +
            "g.libelle AS g_libelle " +
            "FROM surgrade " +
            "INNER JOIN grade g ON surgrade.grade_id = g.id " +
            "WHERE surgrade.id = ?"
        );

        requeteSql.setInt(1, idSurgrade);
        resultatRequete = requeteSql.executeQuery();

        if (resultatRequete.next()) {
            // Création du Grade
            Grade g = new Grade();
            g.setId(resultatRequete.getInt("g_id"));
            g.setLibelle(resultatRequete.getString("g_libelle"));

            // Création du Surgrade
            s = new Surgrade();
            s.setId(resultatRequete.getInt("s_id"));
            s.setLibelle(resultatRequete.getString("s_libelle"));
            s.setGrade_id(resultatRequete.getInt("s_grade_id"));
            s.setUngrade(g);  // on lie le grade au surgrade
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("La requête de getSurgradeById a généré une erreur");
    }

    return s;
}

    
    
    
}