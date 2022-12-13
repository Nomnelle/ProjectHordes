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
                if(this.bois >0){
                    if(quantite>this.bois){
                        for(int i=0;i<this.bois;i++){
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet(Objet.bois);
                            if(added){
                                this.bois -=1;
                            }
                        }
                    }else{
                        for(int i=0;i<quantite;i++){
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet(Objet.bois);
                            if(added){
                                this.bois -=1;
                            }
                        }
                    }
                }else{
                    System.out.println("Il n'y a plus de bois à cet endroit.");
                }
                case "metal":
                    if(this.metal >0){
                        if(quantite>this.metal){
                            for(int i=0;i<this.metal;i++){
                                SacADos sac = player.getSacADos();
                                boolean added = sac.ajouterObjet(Objet.metal);
                                if(added){
                                    this.metal -=1;
                                }
                            }
                        }else{
                            for(int i=0;i<quantite;i++){
                                SacADos sac = player.getSacADos();
                                boolean added = sac.ajouterObjet(Objet.metal);
                                if(added){
                                    this.metal -=1;
                                }
                            }
                        }
                    }else{
                        System.out.println("Il n'y a plus de métal à cet endroit.");
                    }
                case "boisson":
                    if(this.boissonEnergisante > 0){
                        if(quantite>this.boissonEnergisante){
                            for(int i=0;i<this.boissonEnergisante;i++){
                                SacADos sac = player.getSacADos();
                                boolean added = sac.ajouterObjet(Objet.boisson);
                                if(added){
                                    this.boissonEnergisante -=1;
                                }
                            }
                        }else{
                            for(int i=0;i<quantite;i++){
                                SacADos sac = player.getSacADos();
                                boolean added = sac.ajouterObjet(Objet.boisson);
                                if(added){
                                    this.boissonEnergisante -=1;
                                }
                            }
                        }
                    }else{
                        System.out.println("Il n'y a plus de boisson énergisante à cet endroit.");
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
