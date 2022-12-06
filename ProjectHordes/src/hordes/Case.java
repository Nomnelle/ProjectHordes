/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hordes;

/**
 *
 * @author nomnelle
 */
public class Case {
    
    protected final int x; //position de la case en x
    protected final int y; //position de la case en y
    
    protected boolean fouille; //faux si la case n'a pas été fouillée, vrai sinon
    
    protected int bois; //nombre de planches de bois sur la case
    protected int metal; //nombre de morceaux de métal sur la case
    protected int boissonEnergisante; //nombre de boissons energisantes sur la case
    protected int zombie; //nombres de zombies sur la case
    
    public Case(int x_map, int y_map) {
        this.x = x_map;
        this.y = y_map;
        this.zombie = calcZombie();
        
        this.fouille = false;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public boolean getFouille(){
        return this.fouille;
    }
    
    public int getBois(){
        return this.bois;
    }
    
    public int getMetal(){
        return this.metal;
    }
    
    public int getBoissEner(){
        return this.boissonEnergisante;
    }
    
    public int getZombie(){
        return this.zombie;
    }
    
    public void setFouille(){
        this.fouille = true;
    }
    
    public void setBois(int i){
        this.bois = i;
    }
    
    public void setMetal(int i){
        this.metal = i;
    }
    
    public void setBoissEner(int i){
        this.boissonEnergisante = i;
    }
    
    public void setZombie(int i){
        this.zombie = i;
    }
    
    public void decrireCase(){
        
        if(this.fouille){
            String strMet, strBois, strBE;

            strMet = String.format("%d", this.metal);
            strBois = String.format("%d", this.bois);
            strBE = String.format("%d", this.boissonEnergisante);
            System.out.println("Il y a "+strMet+" morceaux de métal, "+strBois+" planches de bois et "+strBE+" boissons énergisantes sur cette case.");

        }else{
            System.out.println("Cette case n'a pas encore été fouillée.");
        }
    }
    
    private static int calcZombie(){
        
        int nbZombie;
        
        double stat = Math.random();
        
        if(stat < 0.3){
            nbZombie = 0;
        }else if(stat<0.4){
            nbZombie = 1;
        }else if(stat<0.5){
            nbZombie = 2;
        }else if(stat<0.6){
            nbZombie=3;
        }else if(stat<0.7){
            nbZombie=4;
        }else if(stat<0.8){
            nbZombie=5;
        }else if(stat<0.9){
            nbZombie=6;
        }else{
            nbZombie=7;
        } 
        return nbZombie;  
    }
    
}
