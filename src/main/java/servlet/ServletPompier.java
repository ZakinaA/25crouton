package servlet;

import database.DaoCaserne;
import database.DaoPompier;
import database.DaoSurgrade;
import form.FormPompier;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Caserne;
import model.Pompier;
import model.Surgrade;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class ServletPompier extends HttpServlet {

    Connection cnx;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection) servletContext.getAttribute("connection");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getRequestURI();

        if (url.endsWith("/lister")) {
            ArrayList<Pompier> lesPompiers = DaoPompier.getLesPompiers(cnx);
            request.setAttribute("pLesPompiers", lesPompiers);
            getServletContext().getRequestDispatcher("/vues/pompier/listerPompiers.jsp").forward(request, response);
        }

        if (url.endsWith("/consulter")) {
            int idPompier = Integer.parseInt(request.getParameter("idPompier"));
            Pompier p = DaoPompier.getPompierById(cnx, idPompier);
            request.setAttribute("pPompier", p);
            getServletContext().getRequestDispatcher("/vues/pompier/consulterPompier.jsp").forward(request, response);
        }

        if (url.endsWith("/ajouter")) {
            ArrayList<Caserne> lesCasernes = DaoCaserne.getLesCasernes(cnx);
            ArrayList<Surgrade> lesSurgrades = DaoSurgrade.getLesSurgrades(cnx);
            request.setAttribute("pLesCasernes", lesCasernes);
            request.setAttribute("pLesSurgrades", lesSurgrades);
            getServletContext().getRequestDispatcher("/vues/pompier/ajouterPompier.jsp").forward(request, response);
        }

        if (url.endsWith("/associerSurgrade")) {
            ArrayList<Pompier> lesPompiers = DaoPompier.getLesPompiers(cnx);
            ArrayList<Surgrade> lesSurgrades = DaoSurgrade.getLesSurgrades(cnx);
            request.setAttribute("pLesPompiers", lesPompiers);
            request.setAttribute("pLesSurgrades", lesSurgrades);
            getServletContext().getRequestDispatcher("/vues/pompier/associerSurgrade.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("associerSurgrade".equals(action)) {
            int idPompier = Integer.parseInt(request.getParameter("idPompier"));
            int idSurgrade = Integer.parseInt(request.getParameter("idSurgrade"));
            DaoPompier.associerSurgrade(cnx, idPompier, idSurgrade);
            response.sendRedirect("ServletPompier/lister");
            return;
        }

        // Ajout pompier avec surgrade
        FormPompier form = new FormPompier();
        Pompier p = form.ajouterPompier(request);

        int idSurgrade = Integer.parseInt(request.getParameter("idSurgrade"));
        Surgrade s = new Surgrade();
        s.setId(idSurgrade);
        p.setUnSurgrade(s);

        request.setAttribute("form", form);
        request.setAttribute("pPompier", p);

        if (form.getErreurs().isEmpty()) {
            Pompier pompierInsere = DaoPompier.addPompier(cnx, p);
            if (pompierInsere != null) {
                request.setAttribute("pPompier", pompierInsere);
                getServletContext().getRequestDispatcher("/vues/pompier/consulterPompier.jsp").forward(request, response);
            }
        } else {
            ArrayList<Caserne> lesCasernes = DaoCaserne.getLesCasernes(cnx);
            ArrayList<Surgrade> lesSurgrades = DaoSurgrade.getLesSurgrades(cnx);
            request.setAttribute("pLesCasernes", lesCasernes);
            request.setAttribute("pLesSurgrades", lesSurgrades);
            getServletContext().getRequestDispatcher("/vues/pompier/ajouterPompier.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Gestion des pompiers - Ajout / Consultation / Association de surgrade";
    }
}
