package servlet;

import database.DaoEngin;
import database.DaoTypeEngin;
import model.Engin;
import model.TypeEngin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;

@WebServlet(name = "ServletEngin", urlPatterns = {"/ServletEngin/listerEngins"})
public class ServletEngin extends HttpServlet {

    private Connection connection;

    @Override
    public void init() throws ServletException {
        super.init();
        // La connexion est initialisée dans InitConnexion (listener)
        connection = (Connection) getServletContext().getAttribute("connection");
        if (connection == null) {
            throw new ServletException("La connexion à la base de données n'est pas disponible.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "listerEngins"; // Action par défaut
        }

        try {
            switch (action) {
                case "listerEngins":
                    listerEngins(request, response);
                    break;
                case "consulterEngin":
                    consulterEngin(request, response);
                    break;
                default:
                    listerEngins(request, response);
            }
        } catch (SQLException e) {
            // Gestion des erreurs SQL
            System.err.println("Erreur SQL : " + e.getMessage());
            request.setAttribute("erreur", "Erreur de base de données : " + e.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp"); //TODO Créer la page erreur.jsp
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw e;
        }
    }

    private void listerEngins(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        DaoEngin daoEngin = new DaoEngin(connection);
        List<Engin> lesEngins = daoEngin.getLesEngins();

        request.setAttribute("pLesEngins", lesEngins);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vues/engin/listerEngins.jsp");
        dispatcher.forward(request, response);
    }


    private void consulterEngin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int idEngin = Integer.parseInt(request.getParameter("id"));
        DaoEngin daoEngin = new DaoEngin(connection);
        Engin engin = daoEngin.getEnginById(idEngin);

        request.setAttribute("pEngin", engin);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vues/engin/consulterEngin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        // La connexion est fermée par le listener InitConnexion
    }
}
