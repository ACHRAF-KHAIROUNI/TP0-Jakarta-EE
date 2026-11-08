package ma.emsi.tp0;

import jakarta.enterprise.context.Dependent;

import java.io.Serializable;
import java.util.Locale;

/**
 * Service métier dédié à la mise en forme et à la simulation des réponses.
 * 
 * @author Achraf Khairouni
 */
@Dependent
public class GenerateurReponse implements Serializable {

    private static final long serialVersionUID = 1L;

    // Retient si l'en-tête du rôle a déjà été injecté dans la réponse initiale
    private boolean roleSystemeDejaAffiche = false;

    /**
     * Traite la question soumise et produit la réponse encadrée de balises.
     * Le rôle système est préfixé en majuscules lors du tout premier message.
     * 
     * @param texteQuestion Texte transmis par l'utilisateur
     * @param roleSysteme Instructions système sélectionnées
     * @return Réponse formatée
     */
    public String genererReponse(String texteQuestion, String roleSysteme) {
        String reponseFormatee = "||";

        // Insertion du rôle système au début de la toute première réponse
        if (!this.roleSystemeDejaAffiche && roleSysteme != null) {
            reponseFormatee += roleSysteme.toUpperCase(Locale.FRENCH) + "\n";
            this.roleSystemeDejaAffiche = true;
        }

        reponseFormatee += texteQuestion.toLowerCase(Locale.FRENCH) + "||";
        return reponseFormatee;
    }
}
