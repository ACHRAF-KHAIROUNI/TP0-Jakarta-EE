/* Copier le contenu d'un textarea dans le clipboard */
async function copyToClipboard(idZoneTexte) {
    const elementZoneTexte = document.getElementById("form:" + idZoneTexte);
    if (elementZoneTexte) {
        try {
            await navigator.clipboard.writeText(elementZoneTexte.value);
        } catch (erreur) {
            console.error("Erreur lors de la copie : ", erreur);
        }
    }
}

/* Effacer la dernière question et la dernière réponse */
function toutEffacer() {
    const champQuestion = document.getElementById("form:question");
    const champReponse = document.getElementById("form:reponse");
    if (champQuestion) champQuestion.value = "";
    if (champReponse) champReponse.value = "";
}