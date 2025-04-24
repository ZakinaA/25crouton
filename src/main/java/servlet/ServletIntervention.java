package servlet;

import database.DaoIntervention;
import form.FormIntervention;
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
            Intervention i = DaoIntervention.getInterventionById(cnx, idIntervention);
            i.setLesPompiers(DaoIntervention.getLesPompiersIntervention(cnx, idIntervention));
            request.setAttribute("pIntervention", i);
            getServletContext().getRequestDispatcher("/vues/intervention/consulterIntervention.jsp").forward(request, response);
        }
        if(url.equals("/sdisweb/ServletIntervention/ajouterIntervention")){                   
            ArrayList<Intervention> lesInterventions = DaoIntervention.getLesInterventions(cnx);
            request.setAttribute("pLesInterventions", lesInterventions);
            this.getServletContext().getRequestDispatcher("/vues/intervention/ajouterIntervention.jsp" ).forward( request, response );
        }
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

  
    FormIntervention form = new FormIntervention();

    Intervention intervention = form.ajouterIntervention(request);

   
    request.setAttribute("form", form);
    request.setAttribute("pIntervention", intervention);

    
    if (form.getErreurs().isEmpty()) {
       
        Intervention interventionInseree = DaoIntervention.addIntervention(cnx, intervention);

        if (interventionInseree != null) {
           
            request.setAttribute("pIntervention", interventionInseree);
            this.getServletContext().getRequestDispatcher("/vues/intervention/consulterIntervention.jsp").forward(request, response);
        } else {
            
           
            request.setAttribute("error", "Erreur lors de l'insertion de l'intervention.");
            this.getServletContext().getRequestDispatcher("/vues/intervention/erreur.jsp").forward(request, response);
        }
    } else {
        
        this.getServletContext().getRequestDispatcher("/vues/intervention/ajouterIntervention.jsp").forward(request, response);
    }
}



    @Override
    public String getServletInfo() {
        return "Servlet pour gérer les interventions.";
    }
}