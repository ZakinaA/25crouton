package servlet;

import database.DaoCaserne;
import database.DaoEngin;
import database.DaoPompier; // Import DaoPompier
import database.DaoTypeEngin;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Caserne;
import model.Engin;
import model.Pompier; // Import Pompier
import model.TypeEngin;
import form.FormEngin;
import form.FormPompier; // Import FormPompier
import java.util.ArrayList; // Import ArrayList
import java.util.Map;

public class ServletEngin extends HttpServlet {

    Connection cnx;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection) servletContext.getAttribute("connection");
        if (cnx == null) {
            throw new RuntimeException("La connexion à la base de données n'est pas disponible.");
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = request.getRequestURI();

        if (url.contains("/ServletEngin/lister")) {
            listerEngins(request, response);
        } else if (url.contains("/ServletEngin/consulter")) {
            consulterEngin(request, response);
        }
        // L'affichage est géré dans doGet pour /ServletEngin/ajouterPompier (si tu changes l'URL)
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        if (url.contains("/ServletEngin/ajouterPompier")) { // Modifie l'URL si nécessaire
            List<Caserne> lesCasernes = DaoCaserne.getLesCasernes(cnx);
            request.setAttribute("pLesCasernes", lesCasernes);
            this.getServletContext().getRequestDispatcher("/vues/pompier/ajouterPompier.jsp").forward(request, response);
        } else if (url.contains("/ServletEngin/ajouter")) { // Pour afficher le formulaire d'engin (si tu le gardes)
            try {
                DaoTypeEngin daoTypeEngin = new DaoTypeEngin(cnx);
                List<TypeEngin> lesTypesEngins = daoTypeEngin.getLesTypesEngins();
                request.setAttribute("pLesTypesEngins", lesTypesEngins);

                DaoCaserne daoCaserne = new DaoCaserne(cnx);
                List<Caserne> lesCasernes = daoCaserne.getLesCasernes(cnx);
                request.setAttribute("pLesCasernes", lesCasernes);

                getServletContext().getRequestDispatcher("/vues/engin/ajouterEngin.jsp").forward(request, response);
            } catch (SQLException e) {
                request.setAttribute("erreur", "Erreur lors de la récupération des types d'engins ou des casernes : " + e.getMessage());
                getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
            }
        } else {
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FormPompier form = new FormPompier();

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Pompier p = form.ajouterPompier(request);

        /* Stockage du formulaire et de l'objet dans l'objet request */
        request.setAttribute("form", form);
        request.setAttribute("pPompier", p);

        if (form.getErreurs().isEmpty()) {
            Pompier pompierInsere = DaoPompier.addPompier(cnx, p);
            if (pompierInsere != null) {
                request.setAttribute("pPompier", pompierInsere);
                this.getServletContext().getRequestDispatcher("/vues/pompier/consulterPompier.jsp").forward(request, response);
            } else {
                // Cas où l'insertion en bdd a échoué
                //renvoyer vers une page d'erreur
            }

        } else {
            // il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
            List<Caserne> lesCasernes = DaoCaserne.getLesCasernes(cnx);
            request.setAttribute("pLesCasernes", lesCasernes);
            this.getServletContext().getRequestDispatcher("/vues/pompier/ajouterPompier.jsp").forward(request, response);
        }
    }

    protected void listerEngins(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DaoEngin daoEngin = new DaoEngin(cnx);
            List<Engin> lesEngins = daoEngin.getLesEngins();
            request.setAttribute("pLesEngins", lesEngins);
            getServletContext().getRequestDispatcher("/vues/engin/listerEngins.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("erreur", "Erreur lors de la récupération des engins : " + e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
        }
    }

    protected void consulterEngin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idEnginStr = request.getParameter("idEngin");
        if (idEnginStr != null && !idEnginStr.isEmpty()) {
            try {
                int idEngin = Integer.parseInt(idEnginStr);
                DaoEngin daoEngin = new DaoEngin(cnx);
                Engin engin = daoEngin.getEnginById(idEngin);
                request.setAttribute("pEngin", engin);
                getServletContext().getRequestDispatcher("/vues/engin/consulterEngin.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                request.setAttribute("erreur", "L'ID de l'engin doit être un nombre.");
                getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
            } catch (SQLException e) {
                request.setAttribute("erreur", "Erreur lors de la récupération de l'engin : " + e.getMessage());
                getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("erreur", "L'ID de l'engin est manquant.");
            getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response);
        }
    }
}