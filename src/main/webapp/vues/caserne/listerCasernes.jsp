<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Caserne"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>APPLICATION DE GESTION SDIS CALVADOS</title>
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
            width: 80%;
            background-color: #ffffff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #e0e0e0;
        }
        th {
            background-color: #3498db;
            color: white;
            text-transform: uppercase;
            font-size: 14px;
        }
        td a {
            text-decoration: none;
            color: #2980b9;
            font-weight: bold;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
    <h1>Liste des casernes du Calvados</h1>
    <%
        ArrayList<Caserne> lesCasernes = (ArrayList)request.getAttribute("cLesCasernes");
    %>
    <table>  
        <thead>
            <tr>             
                <th>Id</th>
                <th>Nom</th>
                <th>Rue</th>
                <th>Code Postal</th>                
                <th>ville</th>                
            </tr>
        </thead>
        <tbody>
            <%
                for (Caserne c : lesCasernes) {
                    out.println("<tr><td>");
                    out.println(c.getId());
                    out.println("</td>");

                    out.println("<td><a href='../ServletCaserne/consulter?idCaserne=" + c.getId() + "'>");
                    out.println(c.getNom());
                    out.println("</a></td>");

                    out.println("<td>");
                    out.println(c.getRue());
                    out.println("</td>");

                    out.println("<td>");
                    out.println(c.getCps());
                    out.println("</td>");

                    out.println("<td>");
                    out.println(c.getVille());
                    out.println("</td></tr>");
                }
            %>
        </tbody>
    </table>
</body>
</html>
