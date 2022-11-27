/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hordes;

/**
 *
 * @author nomnelle
 */
public class Joueur {

    public String nomJoueur;
    private int numJoueur;                        // Pour systeme de score dans Classe Carte
    private String[] SacADos;
    private int pa;                               // Point d'actions, 6 au début, max 10, récupère 4 chaque tours
    private int pv;                               // Points de vie, de 0 à 100, 100 = PV maximum
    private int score;                            // Score du joueur, plus élevé = mort en premier, plus bas = mort en dernier
    private int positionx, positiony;             // Position x et y du joueur

    public void Joueur(int n, String nom) {       // Note pour Elodie : constructeur

        this.nomJoueur = nom;
        this.numJoueur = n;
        this.SacADos = new String[10];
        this.pv = 100;
        this.pa = 6;
        this.score = 0;

        this.positionx = 12;
        this.positiony = 12;                      // Position au début de la partie ? Dans la ville je pense, donc x=12 et y=12

    }

    //Getter
    public int getnumJoueur() {
        return this.numJoueur;
    }

    public String[] getSacADos() {
        return this.SacADos;
    }

    public int getpv() {
        return this.pv;
    }

    public int getpa() {
        return this.pa;
    }

    public int getScore() {
        return this.score;
    }

    public int getpositionx() {
        return this.positionx;
    }

    public int getpositiony() {
        return this.positiony;
    }

    // Setter
    public void setnumJoueur(int i) {
        this.numJoueur = i;
    }

    public void setpv(int i) {
        this.pv = i;
    }

    public void setpa(int i) {
        this.pa = i;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public void setpositionx(int x) {
        this.positionx = x;                     
    }

    public void setpositiony(int y) {
        this.positiony = y;
    }

    
    // Fonctions de gain et perte de PA
    public int PerteDePA(){
        // dire que le minimum de PA = 0
        // Si le joueur s'est déplacé -1
        // Si le joueur a fouillé -1
        // Si le joueur a tué un zombie -1 //  Je ne sais pas le rédiger sans les info de la fonction de dessous niveau syntaxe
    }
     

     public int GainDePA (){
         if (nomJoueur.PA = 10) {
             System.exit(0); // Sortir de la fonction si le joueur à déjà 10 PA; Dire que le maximum de PA = 10
         }
         int x = new Carte.getTour(); // Je ne sais pas faire venir un getteur
         for (int i=0; i<20; i++) { // 20 symbolise le nombre de tour maximum de la partie; je ne savais pas trop quoi mettre pour finir la boucle
             if i && x { // Si un tour est passé, donner 4 PA; comment remonter pour faire le test du haut après ?
                 nomJoueur.PA = nomJoueur.Pa + 4; // Je ne sais pas comment attribuer les PA au joueur jouant précisément et comment limiter à 10 PA max; paramètre à mettre hors fonction de manière générale pour les PA?
             }
             // if pour savoir si le joueur a pris ou veut prendre une boisson et gain de PA en conséquence si le nombre de PA pas suppérieur à 10 (ou limité à 10)
             // lien avec fonction addiction pour début de dépendance
         }
    }
}


// Completer plus tard avec info sur l'addiction
