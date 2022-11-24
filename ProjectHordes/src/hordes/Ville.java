/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hordes;

/**
 *
 * @author nomnelle
 */
public class Ville extends Case{
    
    private int nbZombieDefendable;
    private int nourriture;
    private boolean porteOuverte;
    private Chantier[] tabChantier;
    
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
        
        tabChantier = new Chantier[7];
    }
    
    public void tuerJoueur(){
        if(this.n)
            
        }
    }

