package servlet;

import database.DaoIntervention;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;
import model.Intervention;
import model.Pompier;

/**
 * Servlet pour gérer les interventions.
 */
public class ServletIntervention extends HttpServlet {

    Connection cnx;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection) servletContext.getAttribute("connection");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletIntervention</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletIntervention at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getRequestURI();

        
        if (url.equals("/sdisweb/ServletIntervention/listerInterventions")) {
            ArrayList<Intervention> lesInterventions = DaoIntervention.getLesInterventions(cnx);
            request.setAttribute("pLesInterventions", lesInterventions);
            getServletContext().getRequestDispatcher("/vues/intervention/listerInterventions.jsp").forward(request, response);
        }

        
       if (url.equals("/sdisweb/ServletIntervention/consulterIntervention")) {
        int idIntervention = Integer.parseInt(request.getParameter("idIntervention"));
        System.out.println("Intervention à afficher = " + idIntervention);

        // Récupère l'intervention
        Intervention i = DaoIntervention.getInterventionById(cnx, idIntervention);

    // Récupère et associe les pompiers à l'intervention
        i.setLesPompiers(DaoIntervention.getLesPompiersIntervention(cnx, idIntervention));

    // Passe l'intervention à la JSP
        request.setAttribute("pIntervention", i);

    // Redirige vers la JSP
        getServletContext().getRequestDispatcher("/vues/intervention/consulterIntervention.jsp").forward(request, response);
}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Pas de traitement POST pour le moment
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour gérer les interventions.";
    }
}