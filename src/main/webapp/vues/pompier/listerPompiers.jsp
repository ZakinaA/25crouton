<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Pompier"%>
<%@page import="model.Surgrade"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Pompiers du Calvados</title>
</head>
<body>
    <h1>Liste des pompiers du Calvados</h1>

    <table>
        <thead>
            <tr>
                <th>id</th>
                <th>nom</th>
                <th>prénom</th>
                <th>caserne</th>
                <th>grade</th> <!-- Colonne pour afficher le grade -->
            </tr>
        </thead>
        <tbody>
            <% 
                // Récupération de la liste des pompiers
                ArrayList<Pompier> lesPompiers = (ArrayList<Pompier>) request.getAttribute("pLesPompiers");
                for (Pompier p : lesPompiers) {
            %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getNom() %></td>
                <td><%= p.getPrenom() %></td>
                <td><%= p.getUneCaserne().getNom() %></td>
                <td><%= p.getUnSurgrade() != null ? p.getUnSurgrade().getLibelle() : "Aucun" %></td> <!-- Affiche le grade -->
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
</body>
</html>
