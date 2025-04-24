<%-- 
    Document   : lister_pompiers.jsp
    Created on : 15 mars 2024, 16:50:49
    Author     : zakina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Pompier"%>
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
        <h1>Liste des Grades du Sdis</h1>
            <%
                ArrayList<Surgrade> LesSurgrade = (ArrayList)request.getAttribute("pLesSurgrade");
            %>
            <table>  
            <thead>
                <tr>             
                    <th>id</th>
                    <th>grade</th>
                    <th>surgrade</th>
                               
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                        for (Surgrade s : LesSurgrade)
                        {              
                            out.println("<tr><td>");
                            out.println(s.getId());
                            out.println("</td>");

                            out.println("<td><a href='../ServletGrade/consulter?idGrade=" + s.getId() + "'>" + s.getLibelle() + "</a></td>");
                            out.println(s.getLibelle());
                            out.println("</a></td>");

                           
                            out.println("<td>");
                            out.println(s.getUngrade().getLibelle());
                            out.println("</td>");
                               
                        }
                    %>
                </tr>
            </tbody>
        </table>
    </body>
    </body>
</html>
