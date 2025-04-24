<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Pompier"%>
<%@page import="model.Surgrade"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Associer un Surgrade</title>
</head>
<body>
    <h1>Associer un surgrade à un pompier</h1>

 
    <form action="ServletPompier?action=associerSurgrade" method="POST">


      
        <label for="idPompier">Pompier :</label>
        <select name="idPompier" id="idPompier" required>
            <%
                ArrayList<Pompier> lesPompiers = (ArrayList<Pompier>) request.getAttribute("pLesPompiers");
                for (Pompier p : lesPompiers) {
            %>
                <option value="<%= p.getId() %>"><%= p.getNom() %> <%= p.getPrenom() %></option>
            <%
                }
            %>
        </select>
        <br><br>

        
        <label for="idSurgrade">Surgrade :</label>
        <select name="idSurgrade" id="idSurgrade" required>
            <%
                ArrayList<Surgrade> lesSurgrades = (ArrayList<Surgrade>) request.getAttribute("pLesSurgrades");
                for (Surgrade s : lesSurgrades) {
            %>
                <option value="<%= s.getId() %>"><%= s.getLibelle() %></option>
            <%
                }
            %>
        </select>
        <br><br>

        <input type="submit" value="Associer" />
    </form>
</body>
</html>
