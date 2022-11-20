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
    private int pv;                               // Points de vie, de 0 à 100
    private int score;                            //score du joueur, plus élevé = mort en premier, plus bas = mort en dernier
    private String position[][];   

    public Joueur() {                              // A vérifier, éléments de retour ? Qu'est ce que je manipule exactement xD ? c'est un constructeur, jamais de type de retour

        // this.numJoueur =                       // Comment lier à classe Carte ? ça se lit tout seul, l'id du joueur sera l'indice dans le tableau tabjoueur. là, tu vas mettre en entrée int n, et String nom, ils seront rentrés comme paramètre dans ton constructeur
        this.SacADos = new String[10];
        this.pv = 100;
        this.pa = 6;
        this.score = 0;
        
        // this.position =                        // Position au début de la partie ? Dans la ville je pense, donc x=12 et y=12

        // Comme pour la carte, préparer créations joueur ici via appel de fonction création ? Nope, je ne pense pas. ça se fera dans le main, après qu'on ait demandé combien de joueurs sont crées, on demande autant de fois qu'il y a de joueur un nom, et on appelle le constructeur 
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
    
    public int getScore(){
        return this.score;
    }

    public String[][] Position() {
        return this.position;
    }

    // Setter
    public void setnumJoueur(int i) {
        this.numJoueur = i;
    }

    //  public void setSacADos() {                          Un tableau de taille 10, soit ["Planche", "Métal", "", "", "", "", "", "", "", ""]
    //      this.SacADos = ;                                ça n'a probablement pas de sens de faire des setters pour le sac à dos, enlever/rajouter élément ds sac suffira
    // }
    public void setpv(int i) {
        this.pv = i;
    }

    public void setpa(int i) {
        this.pa = i;
    }
    
    public void setScore(int i){
        this.score = i;
    }

    //  public void setposition(String [i][j]){
    //      this.position= String[i][j];                    // Même problème que sac à dos Alors, surtout pas un tableau de deux dimension, si non tu vas avoir l'équivalent d'une feuille excel à remplir, ça n'a pas de sens 
    // }                                                    //puis la position, c'est des int ; tu peux les coder comme j'ai fait pour les cases, avec un int x pour donner la position en absisse, et un int y pour donner la position en ordonnée
    // public int PerteDePA(){                             // Fonctions de gain et perte de PA
    // }
    // public int GainDePA (){
    //}
}


// Completer plus tard avec info sur l'addiction
