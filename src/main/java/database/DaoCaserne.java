/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import static database.DaoCaserne.requeteSql;
import static database.DaoCaserne.resultatRequete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Caserne;

/**
 *
 * @author zakina
 */
public class DaoCaserne {
    
    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;
    
    public static ArrayList<Caserne> getLesCasernes(Connection cnx){
        
        ArrayList<Caserne> lesCasernes= new ArrayList<Caserne>();
        try{
            requeteSql = cnx.prepareStatement("select * from caserne");
            resultatRequete = requeteSql.executeQuery();
            
            while (resultatRequete.next()){
                
                Caserne c = new Caserne();
                    c.setId(resultatRequete.getInt("id"));
                    c.setNom(resultatRequete.getString("nom"));
                    c.setRue(resultatRequete.getString("rue"));
                    c.setCps(resultatRequete.getString("cps"));
                    c.setVille(resultatRequete.getString("ville"));
                     System.out.println("CASRER"+ c.getVille());

                lesCasernes.add(c);
            }
           
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getLesPompiers e généré une erreur");
        }
        return lesCasernes;
    }
    
    public static Caserne getCaserneById(Connection cnx, int idCaserne){
        
        Caserne c = null ;
        try{
            requeteSql = cnx.prepareStatement("select caserne.id as c_id, caserne.nom as c_nom, caserne.rue as c_rue, caserne.cps as c_cps, caserne.ville as c_ville  " +
                         " from caserne " +
                         " where caserne.id= ?");
            
            requeteSql.setInt(1, idCaserne);
            resultatRequete = requeteSql.executeQuery();
            
            if (resultatRequete.next()){
                
                    c = new Caserne();
                    c.setId(resultatRequete.getInt("c_id"));
                    c.setNom(resultatRequete.getString("c_nom"));
                    c.setRue(resultatRequete.getString("c_rue"));
                    c.setCps(resultatRequete.getString("c_cps"));
                    c.setVille(resultatRequete.getString("c_ville")); 
            }   
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getPompierById  a généré une erreur");
        }
        return c ;
    }
    
    public static Caserne getPompiersParCaserne(Connection cnx, int idCaserne){
        
        Caserne c = null ;
        try{
            requeteSql = cnx.prepareStatement("SELECT p.nom, p.prenom" + 
                    "FROM pompier p" + 
                    "inner join caserne c on caserne_id = c.id" + 
                    "where caserne_id = ?");
            
            requeteSql.setInt(1, idCaserne);
            resultatRequete = requeteSql.executeQuery();
            
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getPompierById a généré une erreur");
        }
        return c ;
    }
}
