<%-- 
    Document   : listerGrade.jsp
    Created on : 15 mars 2024
    Author     : zakina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Surgrade"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Liste des Grades du SDIS</title>
    </head>
    <body>
        <h1>Liste des Surgrades</h1>

        <%
            ArrayList<Surgrade> LesSurgrade = (ArrayList<Surgrade>) request.getAttribute("pLesSurgrade");
        %>

        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Libellé</th>
                    <th>Grade associé</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Surgrade s : LesSurgrade) {
                %>
                <tr>
                    <td><%= s.getId() %></td>
                    <td>
                        <a href="ServletGrade?idGrade=<%= s.getId() %>">
                            <%= s.getLibelle() %>
                        </a>
                    </td>
                    <td><%= s.getUngrade().getLibelle() %></td>
                    <td>
                        <a href="ServletGrade?action=modifier&idGrade=<%= s.getId() %>">Modifier</a>

                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
