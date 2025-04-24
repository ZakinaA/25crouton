/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import model.Engin;
import model.TypeEngin;
import model.Caserne;

/**
 *
 * @author gemini
 */
public class FormEngin {

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }


    public void setErreurs(Map<String, String> erreurs) {
        this.erreurs = erreurs;
    }

    // Méthode de validation du champ de saisie type d'engin (libellé)
    private void validationTypeEngin(String typeEnginLibelle) throws Exception {
        if (typeEnginLibelle == null || typeEnginLibelle.trim().length() < 3) {
            throw new Exception("Le libellé du type d'engin doit contenir au moins 3 caractères.");
        }
    }

    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    private static String getDataForm(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }

    public Engin ajouterEngin(HttpServletRequest request) {

        Engin e = new Engin();

        String typeEnginIdStr = getDataForm(request, "typeEngin");
        String caserneIdStr = getDataForm(request, "caserne");

        if (typeEnginIdStr != null) {
            try {
                int typeEnginId = Integer.parseInt(typeEnginIdStr);
                TypeEngin typeEngin = new TypeEngin(typeEnginId);
                e.setType(typeEngin);
            } catch (NumberFormatException ex) {
                setErreur("typeEngin", "L'ID du type d'engin doit être un nombre.");
            }
        } else {
            setErreur("typeEngin", "Le type d'engin est obligatoire.");
        }

        if (caserneIdStr != null && !caserneIdStr.isEmpty()) {
            try {
                int caserneId = Integer.parseInt(caserneIdStr);
                Caserne caserne = new Caserne(caserneId);
                e.setCasernes(java.util.Collections.singletonList(caserne)); // Puisqu'un seul ID de caserne est récupéré
            } catch (NumberFormatException ex) {
                setErreur("caserne", "L'ID de la caserne doit être un nombre.");
            }
        }

        if (erreurs.isEmpty()) {
            resultat = "Succès de l'ajout de l'engin.";
        } else {
            resultat = "Échec de l'ajout de l'engin.";
        }

        return e;
    }
}