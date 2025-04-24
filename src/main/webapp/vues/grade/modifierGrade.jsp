<%-- 
    Document   : modifierGrade
    Created on : 24 avr. 2025
    Author     : TS1SIO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Surgrade"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Modifier un Surgrade</title>
    </head>
    <body>
        <%
            Surgrade s = (Surgrade) request.getAttribute("leSurgrade");
        %>

        <h1>Modifier le surgrade : <%= s.getLibelle() %></h1>

        <form action="ServletGrade" method="post">
            <input type="hidden" name="idGrade" value="<%= s.getId() %>">

            <label for="libelle">Libellé :</label>
            <input type="text" id="libelle" name="libelle" value="<%= s.getLibelle() %>" required>

            <br><br>

            <input type="submit" value="Enregistrer">
        </form>

        <br>
        <a href="ServletGrade">Retour à la liste</a>
    </body>
</html>
