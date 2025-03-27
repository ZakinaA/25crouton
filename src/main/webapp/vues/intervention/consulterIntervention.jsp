<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Intervention"%>
<%@page import="model.Pompier"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Détails de l'intervention</title>
    </head>
    <body>
        <h1>Détails de l'intervention</h1>

        <%
            Intervention intervention = (Intervention) request.getAttribute("pIntervention");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        %>

        <p><strong>ID :</strong> <%= intervention.getId() %></p>
        <p><strong>Lieu :</strong> <%= intervention.getLieu() %></p>
        <p><strong>Date :</strong> <%= dateFormat.format(intervention.getDate()) %></p>
        <p><strong>Heure Appel :</strong> <%= timeFormat.format(intervention.getHeureAppel()) %></p>
        <p><strong>Heure Arrivée :</strong> <%= timeFormat.format(intervention.getHeureArrivee()) %></p>
        <p><strong>Durée (min) :</strong> <%= intervention.getDuree() %></p>
        <p><strong>Situation :</strong> <%= intervention.getSituation().getType() %></p>

        <h2>Pompiers participants à l'intervention du <%= dateFormat.format(intervention.getDate()) %></h2>
        <%
            ArrayList<Pompier> pompiers = intervention.getLesPompiers();
            if (pompiers != null && !pompiers.isEmpty()) {
        %>
                <ul>
                    <%
                        for (Pompier pompier : pompiers) {
                    %>
                        <li>Nom : <%= pompier.getNom() %>, Prénom : <%= pompier.getPrenom() %></li>
                    <%
                        }
                    %>
                </ul>
        <%
            } else {
        %>
                <p>La liste des pompiers est vide.</p>
        <%
            }
        %>
    </body>
</html>
