<%-- 
    Document   : consulterPompier
    Created on : 18 mars 2024, 12:03:07
    Author     : zakina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Caserne"%>
<%@page import="model.Pompier"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SDIS WEB</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #2c3e50;
        }
        table {
            margin: 30px auto;
            border-collapse: collapse;
            width: 60%;
            background-color: #ffffff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        td {
            padding: 12px 15px;
            border-bottom: 1px solid #e0e0e0;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        td:first-child {
            font-weight: bold;
            color: #34495e;
            width: 30%;
        }
    </style>
</head>
<body>
    <%
        Caserne c = (Caserne)request.getAttribute("cCaserne");
    %>
    <h1>Caserne de <%= c.getVille() %></h1>
    <table>
        <tr>
            <td>Id :</td>
            <td><%= c.getId() %></td>
        </tr>
        <tr>
            <td>Adresse :</td>
            <td><%= c.getRue() %></td>
        </tr>
        <tr>
            <td>Ville :</td>
            <td><%= c.getVille() %></td>
        </tr>
        <tr>
            <td>Code postal :</td>
            <td><%= c.getCps() %></td>
        </tr>
    </table>
    <%
        ArrayList<Pompier> lesPompiers = (ArrayList<Pompier>) request.getAttribute("pLesPompiers");
        if (lesPompiers != null && !lesPompiers.isEmpty()) {
    %>
        <h1>Pompiers affectés à la caserne</h1>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Pompier p : lesPompiers) {
                       
                %>
                        <tr>
                            <td><%= p.getId() %></td>
                            <td><%= p.getNom() %></td>
                            <td><%= p.getPrenom() %></td>
                        </tr>
                <%
                        
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p style="text-align:center; color: gray;">Aucun pompiers affecté à cette caserne.</p>
    <%
        }
    %>

</body>
</html>
