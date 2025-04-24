package servlet;

import database.DaoSurgrade;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Surgrade;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet("/ServletGrade")
public class ServletGrade extends HttpServlet {

    private Connection cnx;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection) servletContext.getAttribute("connection");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String idParam = request.getParameter("idGrade");

        if ("modifier".equals(action) && idParam != null) {
            int id = Integer.parseInt(idParam);
            Surgrade s = DaoSurgrade.getSurgradeById(cnx, id);
            request.setAttribute("leSurgrade", s);
            request.getRequestDispatcher("/vues/grade/modifierGrade.jsp").forward(request, response);
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Consulter Surgrade</title></head><body>");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Surgrade s = DaoSurgrade.getSurgradeById(cnx, id);

            if (s != null) {
                out.println("<h1>Le Surgrade " + s.getLibelle() + " appartient au grade :</h1>");
                out.println("<p><strong>" + s.getUngrade().getLibelle() + "</strong></p>");
            } else {
                out.println("<p>Surgrade introuvable pour id = " + id + "</p>");
            }

        } else {
            ArrayList<Surgrade> surgrades = DaoSurgrade.getLesSurgrades(cnx);
            out.println("<h1>Liste des Surgrades</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Libellé</th><th>Grade ID</th><th>Modifier</th></tr>");

            for (Surgrade s : surgrades) {
                out.println("<tr>");
                out.println("<td>" + s.getId() + "</td>");
                out.println("<td><a href='ServletGrade?idGrade=" + s.getId() + "'>" + s.getLibelle() + "</a></td>");
                out.println("<td>" + s.getGrade_id() + "</td>");
                out.println("<td><a href='ServletGrade?action=modifier&idGrade=" + s.getId() + "'>Modifier</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");
        }

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("idGrade"));
        String libelle = request.getParameter("libelle");

        Surgrade s = new Surgrade();
        s.setId(id);
        s.setLibelle(libelle);

        DaoSurgrade.updateSurgrade(cnx, s);

        response.sendRedirect("ServletGrade");
    }
}
