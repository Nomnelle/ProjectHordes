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
    
    @Override
    public String toString(){
        
        if(this.fouille){
            String strMet, strBois, strBE;

            strMet = String.format("%d", this.metal);
            strBois = String.format("%d", this.bois);
            strBE = String.format("%d", this.boissonEnergisante);
            return("Il y a "+strMet+" morceaux de métal, "+strBois+" planches de bois et "+strBE+" boissons énergisantes sur cette case.");

        }else{
            return("Cette case n'a pas encore été fouillée.");
        }
    }
    
    public void prendreObjet(Joueur player, int quantite, String objet){
        switch(objet){
            case "planche":
                if(quantite>this.bois){
                    for(int i=0;i<this.bois;i++){
                        this.bois -=1;
                        SacADos sac = player.getSacADos();
                        sac.ajouterObjet(Objet.bois);
                    }
                }else{
                    for(int i=0;i<quantite;i++){
                        this.bois -=1;
                        SacADos sac = player.getSacADos();
                        sac.ajouterObjet(Objet.bois);
                    }
                }
                case "metal":
                    if(quantite>this.metal){
                    for(int i=0;i<this.bois;i++){
                        this.metal -=1;
                        SacADos sac = player.getSacADos();
                        sac.ajouterObjet(Objet.metal);
                    }
                }else{
                    for(int i=0;i<quantite;i++){
                        this.metal -=1;
                        SacADos sac = player.getSacADos();
                        sac.ajouterObjet(Objet.metal);
                    }
                }
                case "boisson":
                    if(quantite>this.boissonEnergisante){
                    for(int i=0;i<this.boissonEnergisante;i++){
                        this.boissonEnergisante -=1;
                        SacADos sac = player.getSacADos();
                        sac.ajouterObjet(Objet.boisson);
                    }
                }else{
                    for(int i=0;i<quantite;i++){
                        this.boissonEnergisante -=1;
                        SacADos sac = player.getSacADos();
                        sac.ajouterObjet(Objet.boisson);
                    }
                }
                default:
                    System.out.println("Il n'y a pas l'objet que vous souhaitez sur cette case.");   
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
