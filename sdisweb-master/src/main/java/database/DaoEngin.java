package database;

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

    // Constructeur qui prend la connexion comme paramètre
    public DaoEngin(Connection connection) {
        this.connection = connection;
    }

    public List<Engin> getLesEngins() throws SQLException {
        List<Engin> engins = new ArrayList<>();

        if (connection == null) {
            System.err.println("Impossible d'obtenir une connexion à la base de données.");
            return engins;
        }

        String sql = "SELECT e.id as enginId, e.type_id as type_id, t.libelle as libelle, t.numero_ordre as numero_ordre " +
                     "FROM engin e INNER JOIN type_engin t ON e.type_id = t.id";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int enginId = rs.getInt("enginId");
                int typeId = rs.getInt("type_id");
                String typeLibelle = rs.getString("libelle");
                int typeNumeroOrdre = rs.getInt("numero_ordre");
                TypeEngin typeEngin = new TypeEngin(typeId, typeLibelle, typeNumeroOrdre);
                Engin engin = new Engin(enginId, typeEngin);
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

        String sql = "SELECT e.id as enginId, e.type_id as type_id, t.libelle as libelle, t.numero_ordre as numero_ordre " +
                     "FROM engin e INNER JOIN type_engin t ON e.type_id = t.id WHERE e.id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int enginId = rs.getInt("enginId");
                    int typeId = rs.getInt("type_id");
                    String typeLibelle = rs.getString("libelle");
                    int typeNumeroOrdre = rs.getInt("numero_ordre");
                    TypeEngin typeEngin = new TypeEngin(typeId, typeLibelle, typeNumeroOrdre);
                    engin = new Engin(enginId, typeEngin);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'Engin par ID : " + e.getMessage());
            throw e;
        }
        return engin;
    }
}
