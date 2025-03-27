<%--  
    Document   : consulterEngin
    Created on : 27 mars 2024
    Author     : TonNom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Engin"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SDIS WEB - Consulter Engin</title>
    </head>
    <body>
        <%
            Engin engin = (Engin) request.getAttribute("pEngin");
        %>
        <h1>Détails de l'engin</h1>
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
                    <td>Numero d'ordre du type: </td>
                    <td><%=engin.getType().getNumeroOrdre() %></td>
                </tr>
                <% if (engin.getCaserne() != null) { %>
                    <tr>
                        <td>Caserne :</td>
                        <td><%= engin.getCaserne().getNom()%></td>
                    </tr>
                <% } else { %>
                     <tr>
                        <td>Caserne :</td>
                        <td>Aucune caserne affectée</td>
                    </tr>
                <% } %>
            </table>
        <% } else { %>
            <p>Engin non trouvé.</p>
        <% } %>
    </body>
</html>
