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
    
    private final int x; //position de la case en x
    private final int y; //position de la case en y
    
    private boolean fouille; //faux si la case n'a pas été fouillée, vrai sinon
    
    private int bois; //nombre de planches de bois sur la case
    private int metal; //nombre de morceaux de métal sur la case
    private int boissonEnergisante; //nombre de boissons energisantes sur la case
    private int zombie; //nombres de zombies sur la case
    
    public Case(int x_map, int y_map) {
        this.x = x_map;
        this.y = y_map;
        
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
            String strMet, strBois, strBE, strZomb;

            strMet = String.format("%d", this.metal);
            strBois = String.format("%d", this.bois);
            strBE = String.format("%d", this.boissonEnergisante);
            strZomb = String.format("%d", this.zombie);

            System.out.println("Il y a "+strMet+" morceaux de métal, "+strBois+" planches de bois et "+strBE+" boissons énergisantes sur cette case.");
            
            switch (this.zombie) {
                case 0:
                    System.out.println("Il n'y a pas de danger.");
                    break;
                case 1:
                    System.out.println("Vous avez de la compagnie ! Un zombie vous attaque !");
                    break;
                default:
                    System.out.println("Les bruits que vous avez fait en fouillant ne sont pas passés inaperçus... Une horde de "+strZomb+" zombies vous attaquent !");
                    break;
            }
        }else{
            System.out.println("Cette case n'a pas encore été fouillée.");
        }
    }
    
}
