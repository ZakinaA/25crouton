<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Engin"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>APPLICATION DE GESTION SDIS CALVADOS</title>
    </head>
    <body>
        <h1>Liste des engins du Calvados</h1>
        
        <!-- Récupération de la liste des engins depuis la requête -->
        <%
            ArrayList<Engin> lesEngins = (ArrayList) request.getAttribute("pLesEngins");
        %>

        <!-- Table pour afficher la liste des engins -->
        <table border="1">
            <thead>
                <tr>
                    <th>ID de l'Engin</th>
                    <th>Type de l'Engin</th>
                </tr>
            </thead>
            <tbody>
                <% 
                  
                    for (Engin engin : lesEngins) {
                %>
                    <tr>
                        <td><%= engin.getId() %></td>
                        <td><%= engin.getType().getLibelle() %></td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
