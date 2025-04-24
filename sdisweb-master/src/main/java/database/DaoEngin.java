package database;

import model.Caserne;
import model.Engin;
import model.TypeEngin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoEngin {

    private Connection connection;

    public DaoEngin(Connection connection) {
        this.connection = connection;
    }

    public List<Engin> getLesEngins() throws SQLException {
        List<Engin> engins = new ArrayList<>();

        if (connection == null) {
            System.err.println("Impossible d'obtenir une connexion à la base de données.");
            return engins;
        }

        String sql = "SELECT e.id as enginId, e.type_id as type_id, t.libelle as typeLibelle, t.numero_ordre as typeNumeroOrdre, " +
                     "c.id as caserneId, c.nom as caserneNom " +
                     "FROM engin e " +
                     "INNER JOIN type_engin t ON e.type_id = t.id " +
                     "LEFT JOIN caserne c ON e.caserne_id = c.id";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int enginId = rs.getInt("enginId");
                int typeId = rs.getInt("type_id");
                String typeLibelle = rs.getString("typeLibelle");
                int typeNumeroOrdre = rs.getInt("typeNumeroOrdre");
                TypeEngin typeEngin = new TypeEngin(typeId, typeLibelle, typeNumeroOrdre);

                int caserneId = rs.getInt("caserneId");
                String caserneNom = rs.getString("caserneNom");
                Caserne caserne = null;
                if (caserneId != 0) {
                    caserne = new Caserne(caserneId, caserneNom);
                }

                Engin engin = new Engin(enginId, typeEngin);
                if (caserne != null) {
                    engin.setCasernes(List.of(caserne)); // Utiliser setCasernes avec une liste immutable
                }
                engins.add(engin);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des Engins : " + e.getMessage());
            throw e;
        }
        return engins;
    }

    public Engin getEnginById(int id) throws SQLException {
        Engin engin = null;

        if (connection == null) {
            System.err.println("Impossible d'obtenir une connexion à la base de données.");
            return null;
        }

        String sql = "SELECT e.id as enginId, e.type_id as type_id, t.libelle as typeLibelle, t.numero_ordre as typeNumeroOrdre, " +
                     "c.id as caserneId, c.nom as caserneNom " +
                     "FROM engin e " +
                     "INNER JOIN type_engin t ON e.type_id = t.id " +
                     "LEFT JOIN caserne c ON e.caserne_id = c.id " +
                     "WHERE e.id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int enginId = rs.getInt("enginId");
                    int typeId = rs.getInt("type_id");
                    String typeLibelle = rs.getString("typeLibelle");
                    int typeNumeroOrdre = rs.getInt("typeNumeroOrdre");
                    TypeEngin typeEngin = new TypeEngin(typeId, typeLibelle, typeNumeroOrdre);

                    int caserneId = rs.getInt("caserneId");
                    String caserneNom = rs.getString("caserneNom");
                    Caserne caserne = null;
                    if (caserneId != 0) {
                        caserne = new Caserne(caserneId, caserneNom);
                    }

                    engin = new Engin(enginId, typeEngin);
                    if (caserne != null) {
                        engin.setCasernes(List.of(caserne)); // Utiliser setCasernes avec une liste immutable
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'Engin par ID : " + e.getMessage());
            throw e;
        }
        return engin;
    }
    public int ajouterEngin(Connection cnx, Engin engin) throws SQLException {
        String sql = "INSERT INTO engin (type_id, caserne_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, engin.getType().getId());
            if (engin.getCasernes() != null && !engin.getCasernes().isEmpty()) {
                pstmt.setInt(2, engin.getCasernes().get(0).getId());
            } else {
                pstmt.setNull(2, java.sql.Types.INTEGER); 
            }
            return pstmt.executeUpdate(); 
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'engin : " + e.getMessage());
            throw e;
        }}}
    