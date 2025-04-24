<%--
    Document   : consulterEngin
    Created on : 27 mars 2024
    Author     : TonNom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Engin"%>
<%@page import="model.Caserne"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SDIS WEB - Consulter Engin</title>
    </head>
    <body>
        <h1>Détails de l'engin</h1>
        <%
            Engin engin = (Engin) request.getAttribute("pEngin");
        %>
        <% if (engin != null) { %>
            <table>
                <tr>
                    <td>ID de l'Engin :</td>
                    <td><%= engin.getId()%></td>
                </tr>
                <tr>
                    <td>Type de l'Engin :</td>
                    <td><%= engin.getType().getLibelle()%></td>
                </tr>
                <tr>
                    <td>Numéro d'ordre du type :</td>
                    <td><%=engin.getType().getNumeroOrdre() %></td>
                </tr>
                <tr>
                    <td>Caserne :</td>
                    <td>
                        <% if (engin.getCasernes() != null && !engin.getCasernes().isEmpty()) { %>
                            <%= engin.getCasernes().get(0).getNom() %>
                        <% } else { %>
                            Aucune caserne affectée
                        <% } %>
                    </td>
                </tr>
            </table>
        <% } else { %>
            <p>Engin non trouvé.</p>
        <% } %>
        <br>
        <a href="<%= request.getContextPath() %>/ServletEngin/listerEngins">Retour à la liste des engins</a>
    </body>
</html>