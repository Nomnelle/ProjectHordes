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
public class Ville extends Case{
    
    private int nbZombieDefendable;
    private int nourriture;
    private boolean porteOuverte;
    private Chantier[] tabChantier;
    
    private void initTabChantier(){
        String[] nom = {"Mur d'enceinte", "Fils barbelés", "Fosses à Zombies","Mines","Porte blindées","Miradors","Abris anti-atomique"};
        int[] pa = {10,20,30,30,40,50,60};
        int[] nbZombieResiste = {20,30,50,50,100,200,500};
        int[] nbPlanche = {20,20,50,10,50,75,100};
        int[] nbMetal = {5,30,25,50,50,75,200};
        for(int i=0;i<7;i++){
            this.tabChantier[i]=new Chantier(nom[i],pa[i],nbZombieResiste[i],nbPlanche[i],nbMetal[i]);
        }
    }
    
    
    //Getter
    public int getNbZombieDefendable() {
        return this.nbZombieDefendable;
    }
    
    public int getNourriture() {
        return this.nourriture;
    }    
    
    public boolean getPorteOuverte() {  
        return this.porteOuverte;
    }  
    
    public Chantier[] getTabChantier(){
        return this.tabChantier;
    }
    
    //Setter
    public void setNbZombieDefendable(int i){
        this.nbZombieDefendable = i;
    }
    
    public void setNourriture(int i){
        this.nourriture = i;
    }
    
    public void setPorteOuverte(boolean porteOuverte){
        this.porteOuverte = true;
    }
    
    public Ville(int x_map, int y_map) {
        super(x_map, y_map);
        
        this.setFouille();
        this.setMetal(0);
        this.setBois(0);
        this.setBoissEner(0);
        
        this.nourriture = 50;
        
        this.porteOuverte = true;
        
        this.nbZombieDefendable = 20;
        this.setZombie(0);
        
        this.minimumZombie = 10;
        
        tabChantier = new Chantier[7];
    }
    
    private int minimumZombie;
    
    
    
    public int genererZombie(){
        Random ra = new Random();
        int nbZombie = this.minimumZombie + ra.nextInt(11);
        this.minimumZombie+=10;
        return nbZombie;
                
    }
    
    
    }

