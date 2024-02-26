
/**
 * Enregistrement TNPEtu
 */
public class TNPEtu
{
    Etudiant[] etudiants;
    int nbEtu;
    
    /**
     * Créer un TNPEtu avec une taille arbitraire de 100
     */
    TNPEtu() {
        this.etudiants = new Etudiant[100];
        this.nbEtu = 0;
    }

    /**
     * Créer un TNPEtu avec une taille de pfNbEtu
     * 
     * @param pfNbEtu IN taille du TNPEtu a créer
     */
    TNPEtu(int pfNbEtu) {
        this.etudiants = new Etudiant[pfNbEtu];
        this.nbEtu = 0;
    }
}
