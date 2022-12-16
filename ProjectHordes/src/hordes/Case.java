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
        
        if((x==12)&&(y==12)){ //position de la ville : pas de zombie et impossible à fouiller
            this.zombie = 0;
            this.fouille = true;
        }else{               // sinon on calcule un nombre de zombie aléatoire
            this.zombie = calcZombie();
            this.fouille = false;
        }
    }
    
    //getters
    
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
    
    //setters
    
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
    
    //toString 
    @Override
    public String toString(){
        
        if(this.fouille){ //si c'est fouillé, on donne les quantité des différents composants sur la case
            String strMet, strBois, strBE;

            strMet = String.format("%d", this.metal);
            strBois = String.format("%d", this.bois);
            strBE = String.format("%d", this.boissonEnergisante);
            return("Il y a "+strMet+" morceaux de métal, "+strBois+" planches de bois et "+strBE+" boissons énergisantes sur cette case."); 

        }else{          //sinon on retourne un message indiquant que la case n'a pas été fouillée
            return("Cette case n'a pas encore été fouillée.");
        }
    }
    
    public void prendreObjet(Joueur player, int quantite, String objet){
        switch(objet){                   //pour éviter que le joueur essaye de prendre un objet qui n'existe pas dans le jeu
            case "planche": 
                if(this.bois >0){        //s'il reste du bois
                    if(quantite>this.bois){    //si la quantité demandée est supérieure à la quantité actuelle de bois sur la case
                        for(int i=0;i<this.bois;i++){   //alors le joueurs prend tout le bois qu'il peut
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet("bois");
                            if(added){
                                this.bois -=1;  
                            }
                        }
                    }else{
                        for(int i=0;i<quantite;i++){   //sinon il en prends la quantité qu'il a demandé
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet("bois");
                            if(added){
                                this.bois -=1;
                            }
                        }
                    }
                }else{
                    System.out.println("Il n'y a plus de bois à cet endroit."); //s'il n'y a plus de bois, alors on renvoit un message pour en informer le joueur.
                }
                break;
                case "metal":     //même principe pour tous les autres composants
                    if(this.metal >0){
                        if(quantite>this.metal){
                            for(int i=0;i<this.metal;i++){
                                SacADos sac = player.getSacADos();
                                boolean added = sac.ajouterObjet("métal");
                                if(added){
                                    this.metal -=1;
                                }
                            }
                        }else{
                            for(int i=0;i<quantite;i++){
                                SacADos sac = player.getSacADos();
                                boolean added = sac.ajouterObjet("métal");
                                if(added){
                                    this.metal -=1;
                                }
                            }
                        }
                    }else{
                        System.out.println("Il n'y a plus de métal à cet endroit.");
                    }
                    break;
                case "boisson":
                    if(this.boissonEnergisante > 0){
                        if(quantite>this.boissonEnergisante){
                            for(int i=0;i<this.boissonEnergisante;i++){
                                SacADos sac = player.getSacADos();
                                boolean added = sac.ajouterObjet("boisson");
                                if(added){
                                    this.boissonEnergisante -=1;
                                }
                            }
                        }else{
                            for(int i=0;i<quantite;i++){
                                SacADos sac = player.getSacADos();
                                boolean added = sac.ajouterObjet("boisson");
                                if(added){
                                    this.boissonEnergisante -=1;
                                }
                            }
                        }
                    }else{
                        System.out.println("Il n'y a plus de boisson énergisante à cet endroit.");
                    }
                    break;
                default:
                    System.out.println("Il n'y a pas l'objet que vous souhaitez sur cette case.");
                    break;
        }
    }
    
    private static int calcZombie(){
        
        int nbZombie;
        
        double stat = Math.random(); //on génère un random
        
        if(stat < 0.3){      //en fonction de la valeur pruse par le random, le nombre de zombie sur la case ne sera pas le même.
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
