<%-- Dans votre page ajouterEngin.jsp --%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="model.TypeEngin" %>
<%@ page import="model.Caserne" %>
<% if (request.getAttribute("erreurs") != null) { %>
    <div class="error-messages">
        <ul>
            <% Map<String, String> erreurs = (Map<String, String>) request.getAttribute("erreurs");
               for (Map.Entry<String, String> erreur : erreurs.entrySet()) { %>
                   <li><strong><%= erreur.getKey() %></strong> : <%= erreur.getValue() %></li>
            <% } %>
        </ul>
    </div>
<% } %>

<% if (request.getAttribute("resultat") != null) { %>
    <p class="success-message"><%= request.getAttribute("resultat") %></p>
<% } %>

<form class="form-inline" action="ajouter" method="POST">
    <div>
        <label for="typeEngin">Type d'engin :</label>
        <select name="typeEngin" id="typeEngin" required>
            <option value="">Sélectionner un type</option>
            <%
                List<TypeEngin> lesTypesEngins = (List<TypeEngin>) request.getAttribute("pLesTypesEngins");
                if (lesTypesEngins != null) {
                    for (TypeEngin type : lesTypesEngins) {
            %>
                        <option value="<%= type.getId() %>"><%= type.getLibelle() %></option>
            <%
                    }
                }
            %>
        </select>
        <% if (request.getAttribute("erreurs") != null && ((Map<String, String>) request.getAttribute("erreurs")).containsKey("typeEngin")) { %>
            <span class="error"><%= ((Map<String, String>) request.getAttribute("erreurs")).get("typeEngin") %></span>
        <% } %>
    </div>
    <div>
        <label for="caserne">Caserne (facultatif) :</label>
        <select name="caserne" id="caserne">
            <option value="">Aucune caserne</option>
            <%
                List<Caserne> lesCasernes = (List<Caserne>) request.getAttribute("pLesCasernes");
                if (lesCasernes != null) {
                    for (Caserne caserne : lesCasernes) {
            %>
                        <option value="<%= caserne.getId() %>"><%= caserne.getNom() %></option>
            <%
                    }
                }
            %>
        </select>
        <% if (request.getAttribute("erreurs") != null && ((Map<String, String>) request.getAttribute("erreurs")).containsKey("caserne")) { %>
            <span class="error"><%= ((Map<String, String>) request.getAttribute("erreurs")).get("caserne") %></span>
        <% } %>
    </div>
    <br>
    <input type="submit" value="Ajouter l'Engin">
</form>
<br>
<a href="<%= request.getContextPath() %>/ServletEngin/lister">Retour à la liste des engins</a>