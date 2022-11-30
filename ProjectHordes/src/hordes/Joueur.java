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
        if (i > 10) {
            this.pa = 10;
        } else if (i < 0) {
            this.pa = 0;
        } else {
            this.pa = i;
        }
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
}

// Completer plus tard avec info sur l'addiction
