<%--
  Document   : lister_interventions.jsp
  Created on : [Date de création]
  Author     : zakina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Intervention"%>
<%@page import="model.Situation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SDIS WEB - Interventions</title>
    </head>
    <body>
        <h1>Liste des interventions</h1>
        <%
            ArrayList<Intervention> lesInterventions = (ArrayList) request.getAttribute("pLesInterventions");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Lieu</th>
                    <th>Date</th>
                    <th>Heure Appel</th>
                    <th>Heure Arrivée</th>
                    <th>Durée (min)</th>
                    <th>Situation</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (lesInterventions != null) {
                        for (Intervention i : lesInterventions) {
                            out.println("<tr>");
                            out.println("<td>" + i.getId() + "</td>");
                            out.println("<td><a href ='../ServletIntervention/consulterIntervention?idIntervention=" + i.getId() + "'>" + i.getLieu() + "</a></td>"); // Lien vers consulterIntervention
                            out.println("<td>" + dateFormat.format(i.getDate()) + "</td>");
                            out.println("<td>" + timeFormat.format(i.getHeureAppel()) + "</td>");
                            out.println("<td>" + timeFormat.format(i.getHeureArrivee()) + "</td>");
                            out.println("<td>" + i.getDuree() + "</td>");
                            out.println("<td>" + i.getSituation().getType() + "</td>");
                            out.println("</tr>");
                        }
                    } else {
                        out.println("<tr><td colspan='7'>Aucune intervention trouvée.</td></tr>");
                    }
                %>
            </tbody>
        </table>
    </body>
</html>