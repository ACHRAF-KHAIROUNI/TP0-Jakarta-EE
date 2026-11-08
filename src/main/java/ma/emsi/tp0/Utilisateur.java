package ma.emsi.tp0;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 * Modèle gérant les informations d'un utilisateur durant la requête HTTP.
 * 
 * @author Achraf Khairouni
 */
@Named
@RequestScoped
public class Utilisateur {

    // Nom saisi dans le formulaire
    private String nomUtilisateur;

    public String getNom() {
        return this.nomUtilisateur;
    }

    public void setNom(String nouveauNom) {
        this.nomUtilisateur = nouveauNom;
    }

    /**
     * Déclenche la navigation vers la vue résultat.
     * 
     * @return issue de navigation "hello"
     */
    public String direHello() {
        return "hello";
    }
}
