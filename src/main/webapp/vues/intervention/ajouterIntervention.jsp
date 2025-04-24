<%-- 
    Document   : ajouterIntervention
    Created on : 24 avril 2025
    Author     : TS1SIO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Intervention"%>
<%@page import="model.Pompier"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="form.FormIntervention"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SDIS WEB</title>
    </head>
    <body>
        <h1>AJOUTER UNE INTERVENTION</h1>
        
        <%
            FormIntervention form = (FormIntervention)request.getAttribute("form");
        %>
        
        <form class="form-inline" action="ajouterIntervention" method="POST">
    
            <label for="lieu">Lieu : </label>
            <input id="lieu" type="text" name="lieu" size="50" maxlength="100"/>
            </br>
    
            <label for="date">Date : </label>
            <input id="date" type="date" name="date"/>
            </br>
    
            <label for="heure_appel">Heure d'appel : </label>
            <input id="heure_appel" type="time" name="heure_appel"/>
            </br>
            
            <label for="heure_arrivee">Heure d'arrivée : </label>
            <input id="heure_arrivee" type="time" name="heure_arrivee"/>
            </br>
    
            <label for="situation_id">Situation (ID) : </label>
            <input id="situation_id" type="text" name="situation_id" size="30" maxlength="50"/>
            </br>
            <p>Pour rappel :</p>
            <p><li>L'ID 1 correspond a : Urgence</li></p>
            <p><li>L'ID 2 correspond a : Non-urgence</li></p>
            <p><li>L'ID 3 correspond a : SecoursPersonne</li></p>
    
            <label for="duree">Durée (en minutes) : </label>
            <input id="duree" type="text" name="duree"/>
            </br>

            
           

            
            </br>     
            
            <input type="submit" name="valider" id="valider" value="Valider"/>
        </form>
    </body>
</html>

