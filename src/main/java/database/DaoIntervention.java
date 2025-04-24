/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Intervention;
import model.Pompier;

import model.Situation;


/**
 *
 * @author TS1SIO
 */
public class DaoIntervention {
    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;
    
    public static ArrayList<Intervention> getLesInterventions(Connection cnx){
        
        ArrayList<Intervention> lesInterventions = new ArrayList<Intervention>();
        try{
            requeteSql = cnx.prepareStatement("SELECT id_i,lieu,date,heure_appel,heure_arrivee,duree,type " +
                         " FROM intervention " +
                         " JOIN situation ON intervention.situation_id = situation.id_s ");
            resultatRequete = requeteSql.executeQuery();
            
            while (resultatRequete.next()){
                
                Intervention i = new Intervention();
                    i.setId(resultatRequete.getInt("id_i"));
                    i.setLieu(resultatRequete.getString("lieu"));
                    i.setDate(resultatRequete.getDate("date"));
                    i.setHeureAppel(resultatRequete.getTime("heure_appel"));
                    i.setHeureArrivee(resultatRequete.getTime("heure_arrivee"));
                    i.setDuree(resultatRequete.getInt("duree"));
                    
                    
                Situation s = new Situation();
                    
                    s.setType(resultatRequete.getString("type"));
                
                i.setSituation(s);
                
                lesInterventions.add(i);
            }
           
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getLesInterventions e généré une erreur");
        }
        return lesInterventions;
    }
    
    public static Intervention getInterventionById(Connection cnx, int idIntervention) {
    Intervention i = null;
    try {
        PreparedStatement requeteSql = cnx.prepareStatement(
                "SELECT intervention.id_i, intervention.lieu, intervention.date, intervention.heure_appel, intervention.heure_arrivee, intervention.duree, situation.type " +
                        "FROM intervention " +
                        "JOIN situation ON intervention.situation_id = situation.id_s " +
                        "WHERE intervention.id_i = ?");
        requeteSql.setInt(1, idIntervention);
        ResultSet resultatRequete = requeteSql.executeQuery();

        if (resultatRequete.next()) {
            i = new Intervention(); 
            i.setId(resultatRequete.getInt("id_i"));
            i.setLieu(resultatRequete.getString("lieu"));
            i.setDate(resultatRequete.getDate("date"));
            i.setHeureAppel(resultatRequete.getTime("heure_appel"));
            i.setHeureArrivee(resultatRequete.getTime("heure_arrivee"));
            i.setDuree(resultatRequete.getInt("duree")); 

            Situation s = new Situation();
            
            s.setType(resultatRequete.getString("type"));

            i.setSituation(s);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("La requête de getInterventionById a généré une erreur");
    }
    return i;
    }
     public static ArrayList<Pompier> getLesPompiersIntervention(Connection cnx, int idIntervention) {
        ArrayList<Pompier> lesPompiers = new ArrayList<>();
        try {
            String sql = "SELECT Pompier.id , pompier.nom,pompier.prenom FROM Pompier " +
                         "JOIN Intervention_Pompier ON Pompier.id = Intervention_Pompier.pompier_id " +
                         "WHERE Intervention_Pompier.intervention_id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, idIntervention);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pompier pompier = new Pompier();
                pompier.setId(rs.getInt("id"));
                pompier.setNom(rs.getString("nom"));
                pompier.setPrenom(rs.getString("prenom"));
               
                lesPompiers.add(pompier);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return lesPompiers;
    }
    public static Intervention addIntervention(Connection connection, Intervention i) {      
    int idGenere = -1;  
    try {
        
        String sql = "INSERT INTO intervention (lieu, date, heure_appel, heure_arrivee, duree, situation_id) VALUES (?,?,?,?,?,?)";
        requeteSql = connection.prepareStatement(sql, requeteSql.RETURN_GENERATED_KEYS);
        
        
        requeteSql.setString(1, i.getLieu());
        requeteSql.setDate(2, new java.sql.Date(i.getDate().getTime()));
        requeteSql.setTime(3, i.getHeureAppel());
        requeteSql.setTime(4, i.getHeureArrivee());
        requeteSql.setInt(5, i.getDuree());
        requeteSql.setInt(6, i.getSituation().getId());  
        
       
        requeteSql.executeUpdate();
        
       
        resultatRequete = requeteSql.getGeneratedKeys();
        if (resultatRequete.next()) {
            idGenere = resultatRequete.getInt(1);
            i.setId(idGenere);  
        }

       
        i = getInterventionById(connection, i.getId());
        
    } catch (SQLException e) {
        e.printStackTrace();
        
    }
    return i;  
}


}
