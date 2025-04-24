package form;

import jakarta.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import model.Intervention;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import model.Situation;

/**
 *
 * @author TS1SIO
 */
public class FormIntervention {
    
    private String resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

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
    
    
    private void validationLieu( String nom ) throws Exception {
        if ( nom != null && nom.length() < 3 ) {
        throw new Exception( "Le lieu d'utilisateur doit contenir au moins 3 caractères." );
        }
    }

    private void setErreur( String champ, String message ) {
    erreurs.put(champ, message );
    }    
    
    private static String getDataForm( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }   
    }

    public Intervention ajouterIntervention(HttpServletRequest request) {
    Intervention i = new Intervention();

    String lieu = getDataForm(request, "lieu");
    String dateStr = getDataForm(request, "date");
    String heureAppelStr = getDataForm(request, "heure_appel");
    String heureArriveeStr = getDataForm(request, "heure_arrivee");
    String dureeStr = getDataForm(request, "duree");
    String situationIdStr = getDataForm(request, "situation_id");

    Date date = null;
    Time heureAppel = null;
    Time heureArrivee = null;
    int duree = 0;
    int situationId = 0;

    try {
        validationLieu(lieu);
    } catch (Exception e) {
        setErreur("lieu", e.getMessage());
    }

    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = (Date) sdf.parse(dateStr);
    } catch (ParseException | NullPointerException e) {
        setErreur("date", "Format de date invalide");
    }

    try {
        if (heureAppelStr != null && !heureAppelStr.isEmpty()) {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            heureAppel = new Time(timeFormat.parse(heureAppelStr).getTime());
        }
    } catch (ParseException e) {
        setErreur("heure_appel", "Format d'heure d'appel invalide");
    }

    try {
        if (heureArriveeStr != null && !heureArriveeStr.isEmpty()) {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            heureArrivee = new Time(timeFormat.parse(heureArriveeStr).getTime());
        }
    } catch (ParseException e) {
        setErreur("heure_arrivee", "Format d'heure d'arrivée invalide");
    }

    try {
        duree = Integer.parseInt(dureeStr);
    } catch (NumberFormatException e) {
        setErreur("duree", "Format de durée invalide");
    }

    try {
        situationId = Integer.parseInt(situationIdStr);
    } catch (NumberFormatException e) {
        setErreur("situation_id", "Format de situation_id invalide");
    }

    // Créer et associer une Situation (tu peux la récupérer depuis la base de données ici si nécessaire)
    Situation situation = new Situation();
    situation.setId(situationId); // Tu peux la récupérer depuis la base de données si nécessaire

    // Assigner la situation à l'intervention
    i.setSituation(situation);

    i.setLieu(lieu);
    i.setDate(date);
    i.setHeureAppel(heureAppel);
    i.setHeureArrivee(heureArrivee);
    i.setDuree(duree);

    if (erreurs.isEmpty()) {
        resultat = "Succès de l'ajout.";
    } else {
        resultat = "Échec de l'ajout.";
    }

    return i;
}




}
