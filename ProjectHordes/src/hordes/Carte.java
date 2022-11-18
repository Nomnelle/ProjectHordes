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
    
    private Case[][] map;
    private int[] score;
    private int tour;
    
    private int totMetal;
    private int totBois;
    private int totBoissEner;
    
    int COTE = 25;
    
    public Carte(int n){
        this.map = new Case[COTE][COTE];
        this.score = new int[n];
        
        for(int i = 0;i<n;i++){
            this.score[i]=0;
        }
        
        this.tour = 1;
        
        this.totMetal = 500;
        this.totBois = 1000;
        this.totBoissEner = 100;
        
        initialiseMap();
    }
    
    private void initialiseMap(){
        for(int i=0;i<COTE;i++){
            for(int j=0;j<COTE;j++){
                if(i==12 && j==12){
                   this.map[i][j] = new Ville(i,j);
                } else{
                    this.map[i][j] = new Case(i,j);
                }
            }
        }
    }
    
    public Case getCase(int x, int y){
        return this.map[x][y];
    }
    
    public int[] getScore(){
        return this.score;
    }
    
    public int getTour(){
        return this.tour;
    }
    
    public void setScore(int id){
        int value = this.compterNull();
        this.score[id] = value;
    }
    
    public void setTour(int i){
        this.tour = i;
    }
    
    private int compterFouille(){
        int compteur = 0;
        for(int i=0;i<COTE;i++){
            for(int j=0;j<COTE;j++){
                if(this.map[i][j].getFouille() == false){
                   compteur += 1;
                }
            }
        }
        return compteur;
    }
    
    private int compterNull(){
        
        int compteur=0; 
        
        for(int i=0;i<this.score.length;i++){
            if(this.score[i]==0){
                compteur +=1;
            }
        }
        return compteur;
    }
    
    public int calMetal(){
        Random ra = new Random();
        int nbMetal;
        
        if(this.compterFouille() == 1){
            nbMetal = this.totMetal;
        } else {
            nbMetal = ra.nextInt(this.totMetal) + 1;
            this.totMetal -= nbMetal;
        }
        
        return nbMetal; 
    }
    
    public int calBois(){
        Random ra = new Random();
        int nbBois;
        
        if(this.compterFouille() == 1){
            nbBois = this.totBois;
        } else {
            nbBois = ra.nextInt(this.totBois) + 1;
            this.totBois -= nbBois;
        }
        
        return nbBois; 
    }
    
    public int calcBoissEner(){
        Random ra = new Random();
        int nbBoissEner;
        
        if(this.compterFouille() == 1){
            nbBoissEner = this.totBoissEner;
        } else {
            nbBoissEner = ra.nextInt(this.totBoissEner) + 1;
            this.totBoissEner-= nbBoissEner;
        }
        
        return nbBoissEner; 
    }
    
    
    public void etreFouillee(int x, int y){
        
        boolean town = this.map[x][y] instanceof Ville;
        boolean dejaFouille = this.map[x][y].getFouille();
        
        if((!town)&&(!dejaFouille)){
            this.map[x][y].setMetal(this.calMetal());
            this.map[x][y].setBois(this.calBois());
            this.map[x][y].setBoissEner(this.calcBoissEner());
            this.map[x][y].setFouille();
        } else if(town){
            System.out.println("Il est inutile de fouiller la ville...");
        } else{
            System.out.println("Cette case a déjà été fouillée");
        }
        
    }
}
