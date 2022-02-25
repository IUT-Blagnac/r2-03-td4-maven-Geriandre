
/**
 * Classe de controle du programme
 */
public class Menu
{
    /**
     * main
     */
    public static void main(String[] args) {
        try {
            TNPEtu promo = new TNPEtu(ListeEtudiants.nbLigneCSV("listenomssansaccent.csv"));
            ListeEtudiants.getListe("listenomssansaccent.csv", ",", promo, 200);
            
            System.out.println("----------------------------------------");
            System.out.println("-----------< Tri du tableau >-----------");
            System.out.println("----------------------------------------");
            triSimple(promo);
            
            /* Affichage des test */
            System.out.println("----------------------------------");
            System.out.println("----< Recherche sans rupture >----");
            System.out.println("----------------------------------");
            testSansRupture();
            
            System.out.println("----------------------------------");
            System.out.println("----< Recherche avec rupture >----");
            System.out.println("----------------------------------");
            testAvecRupture();
            
            System.out.println("----------------------------------");
            System.out.println("---< Recherche par dichotomie >---");
            System.out.println("----------------------------------");
            testDichotomie();
            
        } catch (Exception e) {  
            System.out.println("Erreur : " + e.getMessage());
        } 
    }

    /**
     * Recherche un Etudiant dans une liste d'Etudiants par recherche sans rupture
     *
     * @param : TNPEtu pfTabEtu : La liste des Etudiants
     * @param : Etudiant pfEtuCherche : l'Etudiant rechercher
     *
     * @return l'indice de l'Etudiant trouvé dans la liste et -1 si l'Etudiant n'est pas dans la liste
     */
    public static int[] sansRupt(TNPEtu pftab, Etudiant etu){
        //initialisation
        int indice=-1;
        int cpt=0;
        
        //parcours du tableau
        for (int i = 0; i<pftab.nbEtu; i++){
            cpt++;
            if((pftab.etudiants[i].nom).compareTo(etu.nom)==0 &&(pftab.etudiants[i].prenom).compareTo(etu.prenom)==0 ){
                indice=i; 
            } 
        }

        //return
        int[] res = {indice, cpt};
        return res;
    }

    /**
     * Recherche un Etudiant dans une liste d'Etudiants par recherche avec rupture
     *
     * @param : TNPEtu pfTabEtu : La liste des Etudiants
     * @param : Etudiant pfEtuCherche : l'Etudiant rechercher
     *
     * @return l'indice de l'Etudiant trouvé dans la liste et -1 si l'Etudiant n'est pas dans la liste
     */  
    public static int[] avecRupt(TNPEtu pftab, Etudiant etu){
        //initialisation
        int cpt=0;
        
        //parcours du tableau
        for (int i = 0; i<pftab.nbEtu; i++){
            cpt++;
            if((pftab.etudiants[i].nom).compareTo(etu.nom)==0 &&(pftab.etudiants[i].prenom).compareTo(etu.prenom)==0 ){
                int[] res = {i, cpt};
                return res;
            } 
        }   
        
        //return
        int[] res = {-1, cpt};
        return res;
    }
    
    /**
     * Recherche un Etudiant dans une liste d'Etudiants par dichotomie
     *
     * @param : TNPEtu pfTabEtu : La liste des Etudiants
     * @param : Etudiant pfEtuCherche : l'Etudiant rechercher
     *
     * @return l'indice de l'Etudiant trouvé dans la liste et -1 si l'Etudiant n'est pas dans la liste
     */
    public static int[] dichotomie(TNPEtu pfTab, Etudiant etu) {
        //initialisation
        boolean trouve = false;
        int idMax = pfTab.nbEtu - 1;
        int idMin = 0;
        int idMilieu = (idMin + idMax)/2;
        int cpt = 0;
        
        //parcour du tableau
        while (idMax >= idMin && !trouve) {
            idMilieu = (idMin + idMax)/2;
            if (pfTab.etudiants[idMilieu].nom.compareTo(etu.nom) == 0) {
                if (pfTab.etudiants[idMilieu].prenom.compareTo(etu.prenom) == 0) {
                    cpt += 2;
                    trouve = true;
                } else {
                    if (pfTab.etudiants[idMilieu].prenom.compareTo(etu.prenom) < 0) {
                        cpt += 3;
                        idMin = idMilieu + 1;
                    } else {
                        cpt += 3;
                        idMax = idMilieu -1;
                    }
                }
            } else {
                if (pfTab.etudiants[idMilieu].nom.compareTo(etu.nom) < 0) {
                    cpt += 2;
                    idMin = idMilieu + 1;
                } else {
                    cpt += 2;
                    idMax = idMilieu - 1;
                }
            }
        }
        
        //return
        if (trouve) {
            int[] res = {idMilieu, cpt};
            return res;
        }
        
        int[] res = {-1, cpt};
        return res;
    }

    /**
     * Tri un tableau non plein d'Etudiants par ordre alphabétique des noms puis des prénoms
     * 
     * @param : TNPEtu pfTab : tableau a trié
     */
    public static void triSimple (TNPEtu pfTab){
        //init
        int cptI=0;
        int cptJ=0;

        //parcours + tri du tableau
        for (int i = 0; i<pfTab.nbEtu-1; i++){
            cptI++;

            for (int j = i + 1; j<pfTab.nbEtu; j++){
                if ((pfTab.etudiants[i].nom).compareTo(pfTab.etudiants[j].nom)>0){
                    Etudiant echange = pfTab.etudiants[i];
                    pfTab.etudiants[i] = pfTab.etudiants[j];
                    pfTab.etudiants[j]=echange;

                }else if ((pfTab.etudiants[i].nom).compareTo(pfTab.etudiants[j].nom)==0){
                    if((pfTab.etudiants[i].prenom).compareTo(pfTab.etudiants[j].prenom)>0){
                        Etudiant echange = pfTab.etudiants[i];
                        pfTab.etudiants[i] = pfTab.etudiants[j];
                        pfTab.etudiants[j]=echange;

                    }
                }
            }
            cptJ += cptI;
        }
        
        //affichage des résultats
        System.out.println("la boucle a parcouru " + cptI + " fois le tableau");
        System.out.println("Et a réalisé " + cptJ + " tests sur les éléments\n");
    }

    /**
     * Teste le sous programme de recherche sans rupture
     */
    public static void testSansRupture() throws Exception{
        TNPEtu promo = new TNPEtu(ListeEtudiants.nbLigneCSV("listenomssansaccent.csv"));
        
        //on teste avec 5 tailles différentes (1, 50, 100, 150 ,200)
        for (int i = 0; i < 5; i++) {
            int taille = i * 50;
            if(taille == 0){taille++;}
            int nbSubTest = 100;
            int som = 0;
            
            ListeEtudiants.getListe("listenomssansaccent.csv", ",", promo, taille);
            //on effectue 100 tests par taille d'entrée
            for (int j = 0; j < nbSubTest; j++) {
                int indiceCherch = (int) (Math.random() * promo.nbEtu);
                int[] resTest = sansRupt(promo, promo.etudiants[indiceCherch]);
                som += resTest[1];
            }
            int[] resNonPres = sansRupt(promo, new Etudiant());
            //on calcul la moyenne du nombre d'opération élémentaires pour les 100 tests
            int moy = som / nbSubTest;
            
            //affichage des résultats
            System.out.println("Pour une taille de " + taille + " Etudiants:\n -" + moy + " OE en moyennes\n -" + resNonPres[1] + " OE si l'étudiant n'existe pas\n");
        }
    }
    
    /**
     * Teste le sous programme de recherche avec rupture
     */
    public static void testAvecRupture() throws Exception{
        TNPEtu promo = new TNPEtu(ListeEtudiants.nbLigneCSV("listenomssansaccent.csv"));
        
        //on teste avec 5 tailles différentes (1, 50, 100, 150 ,200)
        for (int i = 0; i < 5; i++) {
            int taille = i * 50;
            if(taille == 0){taille++;}
            int nbSubTest = 100;
            int som = 0;
            
            ListeEtudiants.getListe("listenomssansaccent.csv", ",", promo, taille);
            //on effectue 100 tests par taille d'entrée
            for (int j = 0; j < nbSubTest; j++) {
                int indiceCherch = (int) (Math.random() * promo.nbEtu);
                int[] resTest = avecRupt(promo, promo.etudiants[indiceCherch]);
                som += resTest[1];
            }
            int[] resNonPres = avecRupt(promo, new Etudiant());
            //on calcul la moyenne du nombre d'opération élémentaires pour les 100 tests
            int moy = som / nbSubTest;
            
            //affichage des résultats
            System.out.println("Pour une taille de " + taille + " Etudiants:\n -" + moy + " OE en moyennes\n -" + resNonPres[1] + " OE si l'étudiant n'existe pas\n");
        }
    }
    
    /**
     * Teste le sous programme de recherche par dichotomie
     */
    public static void testDichotomie() throws Exception{
        TNPEtu promo = new TNPEtu(ListeEtudiants.nbLigneCSV("listenomssansaccent.csv"));
        
        //on teste avec 5 tailles différentes (1, 50, 100, 150 ,200)
        for (int i = 0; i < 5; i++) {
            int taille = i * 50;
            if(taille == 0){taille++;}
            int nbSubTest = 100;
            int som = 0;
            
            ListeEtudiants.getListe("listenomssansaccent.csv", ",", promo, taille);
            //on effectue 100 tests par taille d'entrée
            for (int j = 0; j < nbSubTest; j++) {
                int indiceCherch = (int) (Math.random() * promo.nbEtu);
                int[] resTest = dichotomie(promo, promo.etudiants[indiceCherch]);
                som += resTest[1];
            }
            int[] resNonPres = dichotomie(promo, new Etudiant());
            //on calcul la moyenne du nombre d'opération élémentaires pour les 100 tests
            int moy = som / nbSubTest;
            
            //affichage des résultats
            System.out.println("Pour une taille de " + taille + " Etudiants:\n -" + moy + " OE en moyennes\n -" + resNonPres[1] + " OE si l'étudiant n'existe pas\n");
        }
    }
    
    /**
     * Affiche un tableau non plein d'Etudiants
     * 
     * @param : TNPEtu pfTab : tableau à afficher
     */
    public static void Affichage (TNPEtu pfTab){
        //parcour et affichage des éléments du tableau
        for (int i = 0; i<pfTab.nbEtu; i++){
            System.out.println(pfTab.etudiants[i].nom + " "+  pfTab.etudiants[i].prenom + " " + pfTab.etudiants[i].td + " " +pfTab.etudiants[i].tp);   
        }
    }
}

