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
    private int minimumZombie;
    private int nourriture;
    private boolean porte;
    private Chantier[] tabChantier;
    
        public Ville(int x_map, int y_map) {
        super(x_map, y_map);
        
        this.fouille = true;
        this.metal = 0;
        this.bois = 0;
        this.boissonEnergisante = 0;
        this.zombie = 0;
        
        this.nourriture = 50;
        
        this.porte = true;
        
        this.nbZombieDefendable = 20;
        this.setZombie(0);
        
        this.minimumZombie = 10;
        
        tabChantier = new Chantier[7];
        
        this.initTabChantier();
    }
    
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
    
    @Override
    public String toString(){
        
        String strMet, strBois, strBE, strRation;

        strMet = String.format("%d", this.metal);
        strBois = String.format("%d", this.bois);
        strBE = String.format("%d", this.boissonEnergisante);
        strRation = String.format("%d", this.nourriture);
        
        return("Il y a "+strMet+" morceaux de métal, "+strBois+" planches de bois, "+strBE+" boissons énergisantes et "+ strRation+" dans l'entrepot.\n");

    }
    
    
    //Getter
    public int getNbZombieDefendable() {
        return this.nbZombieDefendable;
    }
    
    public int getNourriture() {
        return this.nourriture;
    }    
    
    public boolean getPorte() {  
        return this.porte;
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
    
    public void setPorte(){
        this.porte = !(this.porte);
    }
        
    public int genererZombie(){
        Random ra = new Random();
        int nbZombie = this.minimumZombie + ra.nextInt(11);
        this.minimumZombie+=10;
        return nbZombie;
                
    }
    
    public void evaluerActionVille(Joueur player, String action){
        if((player.getPositionx()==this.x)&&(player.getPositiony()==this.y)){
            switch(action){
                case "actionner porte":
                    this.setPorte();
                    if(this.porte){
                        System.out.println("La porte a été ouverte.");
                    }else{
                        System.out.println("La porte a été fermée.");
                    }
                case "boire":
                    if(!(player.getBu())){
                        player.setBu();
                        player.setPa(player.getPa()+6);
                    }else{
                        System.out.println("Vous n'avez plus besoin de boire pour aujourd'hui.");
                    }
                case "prendre objet":
                    //terminer 
                //deposer objets
                //démarrer chantier 
                //allouer des PA pour faire avancer le chantier 
                //voir ce qu'il y a dans l'entrepot
                
                
            }
        }
    }
    
    public void demarerChantier(Joueur player, String chantier){
        if((player.getPositionx()==this.x)&&(player.getPositiony()==this.y)){
            switch(chantier){
                case "Mur d'enceinte":
                    int index=0;
                    for(int i=0;i<this.tabChantier.length;i++){
                        if (this.tabChantier[i].getNomChantier().equals("Mur d'enceinte")){
                            index = i;
                        }
                    }
                    if((tabChantier[index].getNbPlanche() == 0)&&(tabChantier[index].getNbMetal() ==0)){
                        System.out.println("Ce chantier a déjà été commencé");
                    }else if ((tabChantier[index].getNbPlanche()>this.bois)||(tabChantier[index].getNbMetal()>this.metal)){
                        System.out.println("Vous n'avez pas assez de ressources pour construire ce chantier");
                    }else{
                        this.bois-=tabChantier[index].getNbPlanche();
                        this.metal-=tabChantier[index].getNbMetal();
                        
                        tabChantier[index].setNbPlanche(0);
                        tabChantier[index].setNbMetal(0);
                    }
            }
        }
    }
    
    public void autoriserChantier(String chantier){
        
    }
    
    public void deposerObjet(Joueur player, int quantite, String objet){
        switch(objet){
            case "planche":
                if(quantite>this.bois){
                    for(int i=0;i<quantite;i++){
                        this.bois +=1;
                        SacADos sac = player.getSacADos();
                        sac.ajouterObjet(Objet.bois);
                    }
                }
                case "metal":
                    if(quantite>this.metal){
                    for(int i=0;i<quantite;i++){
                        this.metal +=1;
                        SacADos sac = player.getSacADos();
                        sac.ajouterObjet(Objet.metal);
                    }
                }
                case "boisson":
                    if(quantite>this.boissonEnergisante){
                    for(int i=0;i<quantite;i++){
                        this.boissonEnergisante +=1;
                        SacADos sac = player.getSacADos();
                        sac.ajouterObjet(Objet.boisson);
                    }
                }
                    
                case "ration":
                    
                    
                default:
                    System.out.println("Il n'y a pas l'objet que vous souhaitez sur cette case.");
        }
    }
}

