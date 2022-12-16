/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hordes;

import java.util.Random;

/**
 *
 * @author nomnelle
 */
public class Carte {
    
    private final Case[][] map; //la carte de 25*25 cases
    private Communication visuMap;   //les communications entre les joueurs
    private int tour;              //nombre de tour actuel
    
    private int totMetal;          //nombre total de morceaux de métal sur la carte
    private int totBois;           //nombre de total de planches de bois sur la case
    private int totBoissEner;
    
    final static int COTE = 25;   //nombre de case par ligne et nombre de case par colonne
    
    //constructeur
    public Carte(){
        this.map = new Case[COTE][COTE];
        this.tour = 1;
        
        this.totMetal = 500;
        this.totBois = 1000;
        this.totBoissEner = 100;
        this.visuMap = new Communication();
        
        initialiseMap();
    }
    
    //initialisation de chaque case de la carte
    private void initialiseMap(){
        for(int i=0;i<COTE;i++){
            for(int j=0;j<COTE;j++){
                this.map[i][j] = new Case(i,j);
            }
        }
    }
    
    //getters
    public Case getCase(int x, int y){
        return this.map[x][y];
    }
    
    public int getTour(){
        return this.tour;
    }
    
    public Communication getVisuMap(){
        return this.visuMap;
    }

    
    //setters
    public void setTour(int i){
        this.tour = i;
    }
    
    //méthode pour compter le nombre de cases fouillées 
    private int compterFouille(){
        int compteur = 0;
        for(int i=0;i<COTE;i++){
            for(int j=0;j<COTE;j++){
                if(this.map[i][j].getFouille() == false){ //si la case est pas fouillée, le compteur est indenté
                   compteur += 1;
                }
            }
        }
        return compteur;
    }
    
    //méthode pour calculer le nombre de morceaux de métal qui sont présent sur une case
    public int calMetal(){
        Random ra = new Random(); //on crée un objet random
        int nbMetal;
        int compteur = this.compterFouille(); //on compte le nombre de case non fouillées
        
        if((compteur == 1)||(this.totMetal==0)){ 
            nbMetal = this.totMetal; //si tout le métal a déjà été alloué ou si c'est la dernière case à être fouillée, on donne tout le métal restant à cette case
        } else {
            double result = ra.nextGaussian()+(this.totMetal/compteur); //on génère un nombre aléatoire de morceaux de métal
            if(result<0){ //si le nombre généré est inférieur à 0, alors la case n'a pas de morceaux de métal
                nbMetal = 0;
            }else{
                nbMetal = (int) Math.round(result); //on arrondie le résultat
                this.totMetal -= nbMetal;             //on enlève le nombre de morceaux de métal ajouté à la case au total de métal de la carte
            }
        }
        
        return nbMetal; 
    }
    
    //même principe pour les autres calculs
    //méthode pour calculer le nombre de planches de bois qui seront alloués à une case
    public int calBois(){
        Random ra = new Random();
        int nbBois;
        int compteur = this.compterFouille();
        
        if((compteur == 1)||(this.totBois==0)){
            nbBois = this.totBois;
        } else {
            double result = ra.nextGaussian()+(this.totBois/compteur);
            if(result<0){
                nbBois = 0;
            }else{
                nbBois = (int) Math.round(result);
                this.totBois -= nbBois;
            }
        }
        
        return nbBois; 
    }
    
    //méthode pour calculer le nombre de boissons énergisante qui seront alloués à une case
    public int calcBoissEner(){
        Random ra = new Random();
        int nbBoissEner;
        int compteur = this.compterFouille();
        
        if((compteur == 1)||(this.totBoissEner==0)){
            nbBoissEner = this.totBoissEner;
        } else {
            double result = ra.nextGaussian()+(this.totBoissEner/compteur);
            if(result<0){
                nbBoissEner = 0;
            }else{
                nbBoissEner = (int) Math.round(result);
                this.totBoissEner -= nbBoissEner;
            }
        }
        
        return nbBoissEner; 
    }
    
    //méthode pour fouiller une case
    public void etreFouillee(int x, int y, Joueur joueur){
        
        boolean town = ((x == 12)&&(y==12));
        boolean dejaFouille = this.map[x][y].getFouille();
        if((!town)&&(!dejaFouille)){   //si la case n'est pas la ville et si elle n'a pas déjà été fouillée
            this.map[x][y].setMetal(this.calMetal());            //on cacule des valeurs pour chaque composant 
            this.map[x][y].setBois(this.calBois());
            this.map[x][y].setBoissEner(this.calcBoissEner());
            this.map[x][y].setFouille();                           //fouille passe à true
            System.out.println("Vous fouillez la zone.");
            joueur.setPa(joueur.getPa()-1);
        } else if(town){
            System.out.println("Il est inutile de fouiller la ville...");      //si c'est la ville, on prévient le joueur
        } else{
            System.out.println("Cette case a déjà été fouillée.");             //si la case a déjà été fouillée, on prévient le joueur
        }
        
    }
    
    public void evaluerDeplacement(Joueur player, Ville ville, String direction){
        if((ville.getPorte() == true)&&(player.getPositiony()==12)&&(player.getPositionx()==12)){
            System.out.println("Vous ne pouvez pas sortir de la ville, les portes sont fermées."); //si le joueur est dans la ville, et que les portes sont fermées il ne peut pas sortir
        }else if(this.getCase(player.getPositionx(), player.getPositiony()).getZombie()>0){
            System.out.print("Vous êtes cerné ! Vous ne pouvez pas quitter cet endroit tant que des zombies sont présents."); //si y'a des zombies sur la case où se trouve le joueur, il ne peut pas partir
        }else{
            switch(direction){ //sinon le joueur peut se déplacer
                case "aller en haut":
                    player.deplacementHaut();
                     if((player.getPositionx()==12)&&(player.getPositiony()==12)){
                        if(ville.getPorte() == true){
                            System.out.println("Les portes de la ville sont fermées, vous ne pouvez pas rentrer."); //si le joueur arrive dans la ville et que les portes sont fermées
                            player.setPa(player.getPa()+2); //le joueur récupère 2PA pour rembouser ses déplacements
                            player.deplacementBas();        //il retourne à sa position initiale
                        }
                    }
                    break;
                case "aller en bas": //même principe pour chaque déplacement
                    player.deplacementBas();
                     if((player.getPositionx()==12)&&(player.getPositiony()==12)){
                        if(ville.getPorte() == true){
                            System.out.println("Les portes de la ville sont fermées, vous ne pouvez pas rentrer.");
                            player.setPa(player.getPa()+2);
                            player.deplacementHaut();
                        }
                    }
                    break;
                case "aller à gauche":
                    player.deplacementGauche();
                     if((player.getPositionx()==12)&&(player.getPositiony()==12)){
                        if(ville.getPorte() == true){
                            System.out.println("Les portes de la ville sont fermées, vous ne pouvez pas rentrer.");
                            player.setPa(player.getPa()+2);
                            player.deplacementDroit();
                        }
                    }
                    break;
                case "aller à droite":
                    player.deplacementDroit();
                     if((player.getPositionx()==12)&&(player.getPositiony()==12)){
                        if(ville.getPorte() == true){
                            System.out.println("Les portes de la ville sont fermées, vous ne pouvez pas rentrer.");
                            player.setPa(player.getPa()+2);
                            player.deplacementGauche();
                        }
                    }
                    break;
            }
        }
        
    }
    
}
