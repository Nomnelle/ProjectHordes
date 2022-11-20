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
    private String position[][];

    public Joueur() {                              // A vérifier, éléments de retour ? Qu'est ce que je manipule exactement xD ?

        // this.numJoueur =                       // Comment lier à classe Carte ?
        this.SacADos = new String[10];
        this.pv = 100;
        this.pa = 6;
        // this.position =                        // Position au début de la partie ?

        // Comme pour la carte, préparer créations joueur ici via appel de fonction création ?
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

    public String[][] Position() {
        return this.position;
    }

    // Setter
    public void setnumJoueur(int i) {
        this.numJoueur = i;
    }

    //  public void setSacADos() {                          // Je ne sais pas ce que je manipule, besoin questions
    //      this.SacADos = ;
    // }
    public void setpv(int i) {
        this.pv = i;
    }

    public void setpa(int i) {
        this.pa = i;
    }

    //  public void setposition(String [i][j]){
    //      this.position= String[i][j];                    // Même problème que sac à dos
    // }
    // public int PerteDePA(){                             // Fonctions de gain et perte de PA
    // }
    // public int GainDePA (){
    //}
}


// Completer plus tard avec info sur l'addiction
