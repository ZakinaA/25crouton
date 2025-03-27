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
    </head>
    <body>
        <%
            Caserne c = (Caserne)request.getAttribute("cCaserne");
        %>
        <h1>Caserne de <%  out.println(c.getVille());%></h1>
        <table>
            <tr>
                <td>Id : </td><td><%  out.println(c.getId());%></td>
            </tr>
            <tr>
                <td>Adresse : </td><td><%  out.println(c.getRue());%></td>
            </tr>
            <tr>
                <td>Ville : </td><td><%  out.println(c.getVille());%></td>
            </tr>
            <tr>
                <td>Code postale : </td><td><%  out.println(c.getCps());%></td>
            </tr>
        </table>
        </br>
        <%-- <h1>Liste des pompiers de la caserne : <%= c.getNom()%></h1>
            <%
                ArrayList<Pompier> lesPompiers = (ArrayList)request.getAttribute("pLesPompiers");
            %>
            <table>  
            <thead>
                <tr>             
                    <th>id</th>
                    <th>nom</th>
                    <th>prenom</th>           
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                        for (Pompier p : lesPompiers) {
                            if (p.getUneCaserne().getId() == c.getId())
                            {
                                out.println("<tr><td>");
                                out.println(p.getId());
                                out.println("</a></td>");

                                out.println("<td><a href ='../ServletPompier/consulter?idPompier="+ p.getId()+ "'>");
                                out.println(p.getNom());
                                out.println("</td>");;

                                out.println("<td>");
                                out.println(p.getPrenom());
                                out.println("</td>");

                            }
                               
                        }
                    %>
                </tr>
            </tbody>
        </table>
        <%
          } else {
        %>
            <p>Aucun pompier trouvé pour cette caserne.</p> --%>
    </body>
</html>
