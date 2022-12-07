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

    private final String nomJoueur;
    private final int numJoueur;                        // Pour systeme de score dans Classe Carte
    private String[] SacADos;
    private int pa;                               // Point d'actions, 6 au début, max 10, récupère 4 chaque tours
    private int pv;                               // Points de vie, de 0 à 100, 100 = PV maximum
    private int score;                            // Score du joueur, plus élevé = mort en premier, plus bas = mort en dernier
    private int positionx, positiony;             // Position x et y du joueur
    private boolean nourri, bu;
    private Addiction addiction;

    public Joueur(int n, String nom) {

        this.nomJoueur = nom;
        this.numJoueur = n;
        this.SacADos = new String[10];
        this.pv = 100;
        this.pa = 6;
        this.score = 0;

        this.positionx = 12;
        this.positiony = 12;   
        
        this.nourri = false;
        this.bu = false;
        
        this.addiction = new Addiction();

    }

    //Getter
    public int getNumJoueur() {
        return this.numJoueur;
    }
    
    public String getNomJoueur(){
        return this.nomJoueur;
    }

    public String[] getSacADos() {
        return this.SacADos;
    }

    public int getPv() {
        return this.pv;
    }

    public int getPa() {
        return this.pa;
    }

    public int getScore() {
        return this.score;
    }

    public int getPositionx() {
        return this.positionx;
    }

    public int getPositiony() {
        return this.positiony;
    }
    
    public boolean getNourri(){
        return this.nourri;
    }
    
    public boolean getBu(){
        return this.bu;
    }
    
    public Addiction getAddiction(){
        return this.addiction;
    }

    // Setter
    public void setPv(int i) {
        this.pv = i;
        if(this.pv==0){
            String strNom = String.format("%s", this.nomJoueur);
            System.out.println(strNom+" a été tué!");
        }
    }

    public void setPa(int i) {
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

    private void setPositionx(int x) {
        this.positionx = x;
    }

    private void setPositiony(int y) {
        this.positiony = y;
    }
    
    public void setNourri(){
        this.nourri = !(this.nourri);
    }
    
    public void setBu(){
        this.bu = !(this.bu);
    }

    public void deplacementHaut() {
        if (this.positiony == 0) {                 // Limites de cartes
            System.out.println("Avancer plus serait dangereux, vous devriez revenir sur vos pas");
        }else{
            this.setPositiony(this.positiony - 1);
            this.pa -=1;
        }
    }

    public void deplacementBas() {
        if (this.positiony == 24) {
            System.out.println("Avancer plus loin serait risqué, vous feriez mieux de choisir une autre direction.");
        }else{
            this.setPositiony(this.positiony + 1);
            this.pa -=1;
        }
    }

    public void deplacementDroit() {
        if (this.positionx == 24) {
            System.out.println("Nous vous déconseillons d'aller plus loin, allez ailleurs.");
        }else{
            this.setPositionx(this.positionx + 1);
            this.pa -=1;
        }
    }

    public void deplacementGauche() {
        if (this.positionx == 0) {
            System.out.println("N'allez pas plus loin, les contrées avoisinantes sont trop inquiétantes.");
        }else{
            this.setPositionx(this.positionx - 1);
            this.pa -=1;
        }
    }
    
}
