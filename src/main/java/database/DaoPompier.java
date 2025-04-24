package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Caserne;
import model.Pompier;
import model.Surgrade;

public class DaoPompier {

    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;

    public static ArrayList<Pompier> getLesPompiers(Connection cnx) {
        ArrayList<Pompier> lesPompiers = new ArrayList<>();

        try {
            requeteSql = cnx.prepareStatement(
                "SELECT pompier.id AS p_id, pompier.nom AS p_nom, pompier.prenom AS p_prenom, " +
                "c.id AS c_id, c.nom AS c_nom, " +
                "s.id AS s_id, s.libelle AS s_libelle " +
                "FROM pompier " +
                "INNER JOIN caserne c ON pompier.caserne_id = c.id " +
                "LEFT JOIN surgrade s ON pompier.surgrade_id = s.id"
            );

            resultatRequete = requeteSql.executeQuery();

            while (resultatRequete.next()) {
                Pompier p = new Pompier();
                p.setId(resultatRequete.getInt("p_id"));
                p.setNom(resultatRequete.getString("p_nom"));
                p.setPrenom(resultatRequete.getString("p_prenom"));

                Caserne c = new Caserne();
                c.setId(resultatRequete.getInt("c_id"));
                c.setNom(resultatRequete.getString("c_nom"));
                p.setUneCaserne(c);

                if (resultatRequete.getInt("s_id") != 0) {
                    Surgrade s = new Surgrade();
                    s.setId(resultatRequete.getInt("s_id"));
                    s.setLibelle(resultatRequete.getString("s_libelle"));
                    p.setUnSurgrade(s);
                }

                lesPompiers.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lesPompiers;
    }

    public static Pompier getPompierById(Connection cnx, int idPompier){
    Pompier p = null ;
    try{
        requeteSql = cnx.prepareStatement("SELECT pompier.id AS p_id, pompier.nom AS p_nom, pompier.prenom AS p_prenom, " +
                                         "c.id AS c_id, c.nom AS c_nom, " +
                                         "s.id AS s_id, s.libelle AS s_libelle " +
                                         "FROM pompier " +
                                         "INNER JOIN caserne c ON pompier.caserne_id = c.id " +
                                         "LEFT JOIN surgrade s ON pompier.surgrade_id = s.id " +
                                         "WHERE pompier.id = ?");
        requeteSql.setInt(1, idPompier);
        resultatRequete = requeteSql.executeQuery();

        if (resultatRequete.next()){
            p = new Pompier();
            p.setId(resultatRequete.getInt("p_id"));
            p.setNom(resultatRequete.getString("p_nom"));
            p.setPrenom(resultatRequete.getString("p_prenom"));

            // Récupérer les informations de la caserne
            Caserne c = new Caserne();
            c.setId(resultatRequete.getInt("c_id"));
            c.setNom(resultatRequete.getString("c_nom"));
            p.setUneCaserne(c);

            // Récupérer les informations du surgrade (grade)
            Surgrade s = new Surgrade();
            s.setId(resultatRequete.getInt("s_id"));
            s.setLibelle(resultatRequete.getString("s_libelle"));
            p.setUnSurgrade(s); // Ajouter le surgrade à l'objet Pompier
        }

    } catch (SQLException e){
        e.printStackTrace();
        System.out.println("La requête de getPompierById a généré une erreur");
    }
    return p;


    }

    public static Pompier addPompier(Connection connection, Pompier p) {
        int idGenere = -1;

        try {
            requeteSql = connection.prepareStatement(
                "INSERT INTO pompier (nom, prenom, caserne_id, surgrade_id) VALUES (?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
            );

            requeteSql.setString(1, p.getNom());
            requeteSql.setString(2, p.getPrenom());
            requeteSql.setInt(3, p.getUneCaserne().getId());
            requeteSql.setInt(4, p.getUnSurgrade() != null ? p.getUnSurgrade().getId() : null);

            requeteSql.executeUpdate();

            resultatRequete = requeteSql.getGeneratedKeys();
            if (resultatRequete.next()) {
                idGenere = resultatRequete.getInt(1);
                p.setId(idGenere);
                p = getPompierById(connection, idGenere);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    public static void associerSurgrade(Connection cnx, int idPompier, int idSurgrade) {
        try {
            requeteSql = cnx.prepareStatement(
                "UPDATE pompier SET surgrade_id = ? WHERE id = ?"
            );
            requeteSql.setInt(1, idSurgrade);
            requeteSql.setInt(2, idPompier);
            requeteSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
