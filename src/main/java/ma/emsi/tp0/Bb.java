package ma.emsi.tp0;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur JSF (Backing Bean) pour la gestion de l'interface conversationnelle.
 * Maintient l'état du chat sur plusieurs requêtes HTTP grâce à la portée ViewScoped.
 * 
 * @author Achraf Khairouni
 */
@Named
@ViewScoped
public class Bb implements Serializable {

    private static final long serialVersionUID = 1L;

    // Rôle attribué au modèle conversationnel
    private String roleSysteme;

    // Indicateur de verrouillage du rôle système une fois la discussion entamée
    private boolean roleSystemeChangeable = true;

    // Rôles prédéfinis proposés dans la liste déroulante
    private List<SelectItem> itemsRolesSysteme;

    // Message saisi par l'utilisateur
    private String texteQuestion;

    // Réponse produite par le générateur
    private String texteReponse;

    // Historique cumulé de l'échange
    private StringBuilder historiqueConversation = new StringBuilder();

    // Injection du service de génération de réponses
    @Inject
    private GenerateurReponse generateurReponse;

    // Contexte JSF pour la gestion des notifications et erreurs
    @Inject
    private FacesContext facesContext;

    // Constructeur par défaut requis par CDI
    public Bb() {
    }

    public String getRoleSysteme() {
        return this.roleSysteme;
    }

    public void setRoleSysteme(String roleSysteme) {
        this.roleSysteme = roleSysteme;
    }

    public boolean isRoleSystemeChangeable() {
        return this.roleSystemeChangeable;
    }

    public String getQuestion() {
        return this.texteQuestion;
    }

    public void setQuestion(String question) {
        this.texteQuestion = question;
    }

    public String getReponse() {
        return this.texteReponse;
    }

    public void setReponse(String reponse) {
        this.texteReponse = reponse;
    }

    public String getConversation() {
        return this.historiqueConversation.toString();
    }

    public void setConversation(String conversation) {
        this.historiqueConversation = new StringBuilder(conversation);
    }

    /**
     * Traite et transmet la question saisie par l'utilisateur.
     * En cas de champ vide, un message d'erreur est renvoyé à la vue.
     * 
     * @return null pour conserver la même vue JSF
     */
    public String envoyer() {
        if (this.texteQuestion == null || this.texteQuestion.isBlank()) {
            FacesMessage msgErreur = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Texte question vide", "Veuillez renseigner le texte de la question.");
            facesContext.addMessage(null, msgErreur);
            return null;
        }

        // Figer le rôle dès la première interaction
        if (this.historiqueConversation.isEmpty()) {
            this.roleSystemeChangeable = false;
        }

        // Génération du retour simulé
        this.texteReponse = this.generateurReponse.genererReponse(this.texteQuestion, this.roleSysteme);

        // Mise à jour de l'historique complet
        afficherConversation();
        return null;
    }

    /**
     * Réinitialise la conversation et ouvre une nouvelle session.
     * Redirige vers la vue index pour réinstancier le bean ViewScoped.
     * 
     * @return nom de la vue d'accueil ("index")
     */
    public String nouveauChat() {
        return "index";
    }

    /**
     * Ajoute le dernier échange utilisateur / serveur dans le journal de conversation.
     */
    private void afficherConversation() {
        this.historiqueConversation
                .append("== User:\n")
                .append(this.texteQuestion)
                .append("\n== Serveur:\n")
                .append(this.texteReponse)
                .append("\n");
    }

    /**
     * Fournit les options de rôles système préconfigurés pour le menu déroulant.
     */
    public List<SelectItem> getRolesSysteme() {
        if (this.itemsRolesSysteme == null) {
            this.itemsRolesSysteme = new ArrayList<>();

            String promptRole = """
                    You are a helpful assistant. You help the user to find the information they need.
                    If the user type a question, you answer it.
                    """;
            this.itemsRolesSysteme.add(new SelectItem(promptRole, "Assistant"));

            promptRole = """
                    You are an interpreter. You translate from English to French and from French to English.
                    If the user type a French text, you translate it into English.
                    If the user type an English text, you translate it into French.
                    If the text contains only one to three words, give some examples of usage of these words in English.
                    """;
            this.itemsRolesSysteme.add(new SelectItem(promptRole, "Traducteur Anglais-Français"));

            promptRole = """
                    Your are a travel guide. If the user type the name of a country or of a town,
                    you tell them what are the main places to visit in the country or the town
                    are you tell them the average price of a meal.
                    """;
            this.itemsRolesSysteme.add(new SelectItem(promptRole, "Guide touristique"));
        }

        return this.itemsRolesSysteme;
    }
}
