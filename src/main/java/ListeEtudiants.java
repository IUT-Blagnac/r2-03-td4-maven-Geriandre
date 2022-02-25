import java.io.*;
import java.util.Scanner;

/**
 * Classe de lecture des fichiers CSV
 */
public class ListeEtudiants {
    /**
     * Permet de calculer le nombre de lignes d'un fichier CSV
     * 
     * @param pfFileName IN nom du fichier CSV a lire
     * 
     * @return le nombre de lignes que contient le fichier
     */
    public static int nbLigneCSV(String pfFileName) throws FileNotFoundException{
        //initialisation
        BufferedReader read = new BufferedReader(new FileReader(pfFileName));
        int nbElt = 0;

        //lecture du CSV
        try {
            while (read.readLine() != null) {
                nbElt++;
            }
            read.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nbElt;
    }
    
    /** Charge dans un tableau la liste des étudiants
     * 
     *  @param pfFileName IN le nom du fichier à lire
     *  @param pfDelimiter IN le délimiteur de champs dans le fichier csv
     *  
     *  @return le tableau
     **/
    public static void getListe(String pfFileName, String pfDelimiter, TNPEtu pfTNP, int pfNbElt) //change return value
        throws FileNotFoundException{
        
        int nbElt = nbLigneCSV("listenomssansaccent.csv");
        
        if (pfNbElt < nbElt) {
            nbElt = pfNbElt;
        }
        
        // lecture du fichier pour récupérer les noms, prénoms, td et tp
        String line = "";
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pfFileName));

            // loops through every line until null found
            for (int i = 0; i < nbElt; i++) {               
                line = reader.readLine();
                // separate every token by comma
                String[] token = line.split(pfDelimiter);    
                
                pfTNP.etudiants[i] = new Etudiant(token[0], token[1], Integer.parseInt(token[2]), token[3]);
            }
            reader.close(); 
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        pfTNP.nbEtu = nbElt;
    }

    /**
     * main
     */
    public static void main(String[] args) {
        try {
            // appel de la fonction de lecture du fichier avec le nom du fichier
            // et le séparateur choisi conforme au contenu du csv
            TNPEtu promo = new TNPEtu(200);
            getListe("listenomssansaccent.csv", ",", promo, 25);
            
            System.out.println(nbLigneCSV("listenomssansaccent.csv"));

            System.out.println("Il y a : " + promo.nbEtu + " personnes."); 
        } catch (Exception e) {  
            System.out.println("Erreur : " + e.getMessage());
        } 
    } 
}

