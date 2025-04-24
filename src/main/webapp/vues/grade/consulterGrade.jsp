<%-- 
    Document   : consulterPompier
    Created on : 18 mars 2024, 12:03:07
    Author     : zakina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Pompier"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Association du Grade </title>
    </head>
    <body>
        <%
            Surgrade s = (Surgrade)request.getAttribute("pSurgrade");
        %>
        <h1>Le Surgrade  <%  out.println(s.getLibelle());%> appartient au grade  : </h1>
        <table>         
            <tr>
                <td>Grade : </td><td><%  out.println(s.getUngrade().getLibelle());%></td>
            </tr>
        </table>
    </body>
</html>
