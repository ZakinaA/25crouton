<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Caserne"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SDIS WEB</title>
    </head>
    <body>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>APPLICATION DE GESTION SDIS CALVADOS</title>
    </head>
    <body>
        <h1>Liste des casernes du Calvados</h1>
            <%
                ArrayList<Caserne> lesCasernes = (ArrayList)request.getAttribute("cLesCasernes");
            %>
            <table>  
            <thead>
                <tr>             
                    <th>id</th>
                    <th>nom</th>
                    <th>rue</th>
                    <th>cps</th>                
                    <th>ville</th>                
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                        for (Caserne c : lesCasernes)
                        {              
                            out.println("<tr><td>");
                            out.println(c.getId());
                            out.println("</td>");

                            out.println("<td><a href ='../ServletCaserne/consulter?idCaserne="+ c.getId()+ "'>");
                            out.println(c.getNom());
                            out.println("</td>");;

                            out.println("<td>");
                            out.println(c.getRue());
                            out.println("</td>");
                            
                            out.println("<td>");
                            out.println(c.getCps());
                            out.println("</td>");
                            
                            out.println("<td>");
                            out.println(c.getVille());
                            out.println("</td>");
                           
                               
                        }
                    %>
                </tr>
            </tbody>
        </table>
    </body>
    </body>
</html>
