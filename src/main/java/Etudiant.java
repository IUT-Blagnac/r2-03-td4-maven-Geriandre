
/**
 * Enregistrement Etudiant
 */
public class Etudiant
{
    String nom;
    String prenom;
    int td;
    String tp;
    
    /**
     * Créer un Etudiant inconnu
     */
    Etudiant() {
        this.nom = "inconnu";
        this.prenom = "inconnu";
        this.td = 1;
        this.tp = "1A";
    }
    
    /**
     * Créer un Etudiant selon les paramètres fournis
     * 
     * @param pfNom IN nom de l'Etudiant à créer
     * @param pfPrenom IN prenom de l'Etudiant à créer
     * @param pfTd IN groupe de TD de l'Etudiant à créer
     * @param pfNom IN pfTp groupe de TP de l'Etudiant à créer
     */
    Etudiant(String pfNom, String pfPrenom, int pfTd, String pfTp) {
        this.nom = pfNom;
        this.prenom = pfPrenom;
        this.td = pfTd;
        this.tp = pfTp;
    }
}
