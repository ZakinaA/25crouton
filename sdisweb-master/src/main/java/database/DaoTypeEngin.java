package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TypeEngin;

public class DaoTypeEngin {

    private Connection connection;

    public DaoTypeEngin(Connection connection) {
        this.connection = connection;
    }

   
    public List<TypeEngin> getLesTypesEngins() throws SQLException {
        List<TypeEngin> typeEngins = new ArrayList<>();

        String sql = "SELECT id, libelle, numero_ordre FROM type_engin";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String libelle = rs.getString("libelle");
                int numeroOrdre = rs.getInt("numero_ordre");
                TypeEngin typeEngin = new TypeEngin(id, libelle, numeroOrdre);
                typeEngins.add(typeEngin);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des TypeEngin : " + e.getMessage());
            throw e;
        }
        return typeEngins;
    }

    public TypeEngin getTypeEnginById(int id) throws SQLException {
        TypeEngin typeEngin = null;

        String sql = "SELECT id, libelle, numero_ordre FROM type_engin WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    String libelle = rs.getString("libelle");
                    int numeroOrdre = rs.getInt("numero_ordre");
                    typeEngin = new TypeEngin(id, libelle, numeroOrdre);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du TypeEngin par ID : " + e.getMessage());
            throw e;
        }
        return typeEngin;
    }
}
