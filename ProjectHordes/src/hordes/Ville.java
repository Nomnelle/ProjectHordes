/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hordes;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nomnelle
 */
public class Ville extends Case{ //Ville hérite de Case pour avoir des fonctions en commun avec cette dernière + ses fonctions particulières
    
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
        
        this.porte = false;
        
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
        
        return("Il y a "+strMet+" morceaux de métal, "+strBois+" planches de bois, "+strBE+" boissons énergisantes et "+ strRation+" rations dans l'entrepot.\n");

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
        Scanner sc = new Scanner(System.in);
        if((player.getPositionx()==this.x)&&(player.getPositiony()==this.y)){
            switch(action){
                case "actionner porte":
                    this.setPorte();
                    if(!(this.porte)){
                        System.out.println("La porte a été ouverte.");
                    }else{
                        System.out.println("La porte a été fermée.");
                    }
                    break;
                case "boire":
                    if(!(player.getBu())){
                        player.setBu();
                        player.setPa(player.getPa()+6);
                    }else{
                        System.out.println("Vous n'avez plus besoin de boire pour aujourd'hui.");
                    }
                    break;
                case "deposer objet entrepot":
                    System.out.println("Quel objet voulez-vous déposer ? Entrez son nom.");
                    String objet = sc.nextLine();
                    System.out.println("En quelle quantité ? Entrez un numérique.");
                    int quantite = sc.nextInt();
                    this.deposerObjet(player, quantite, objet);
                    break;
                case "prendre objet entrepot":
                    System.out.println("Quel objet voulez-vous déposer ? Entrez son nom.");
                    objet = sc.nextLine();
                    System.out.println("En quelle quantité ? Entrez un numérique.");
                    quantite = sc.nextInt();
                    this.prendreObjet(player, quantite, objet);
                    break;
                case "demarrer nouveau chantier":
                    System.out.println("Quel chantier voulez-vous démarrer ? Entrez son nom.");
                    String chantier = sc.nextLine();
                    this.demarrerChantier(player, chantier);
                    break;
                case "travailler sur chantier":
                    System.out.println("Sur quel chantier voulez vous travailler ? Entrez son nom.");
                    chantier = sc.nextLine();
                    System.out.println("Durant combien de temps ? Entrez nombre de Pa.");
                    int temps = sc.nextInt();
                    this.travaillerChantier(player, chantier, temps);
                    break;
                case "consulter entrepot":
                    System.out.println(this.toString());
                    break;
            }
        }
    }
    
    public void demarrerChantier(Joueur player, String chantier){
        switch(chantier){
            case "Mur d'enceinte":
                this.autoriserChantier(chantier);
                break;
            case "Fils barbelés":
                this.autoriserChantier(chantier);
                break;
            case "Fosses à Zombies":
                this.autoriserChantier(chantier);
                break;
            case "Mines":
                this.autoriserChantier(chantier);
                break;
            case "Porte blindées":
                this.autoriserChantier(chantier);
                break;
            case "Miradors":
                this.autoriserChantier(chantier);
                break;
            case "Abris anti-atomique": 
                this.autoriserChantier(chantier);
                break;
            default:
                System.out.println("Ce chantier ne fait pas partie des chantiers disponibles.");  
                break;
        }
    }
    
    
    public void autoriserChantier(String chantier){
        int index=0;
        for(int i=0;i<this.tabChantier.length;i++){
            if (this.tabChantier[i].getNomChantier().equals(chantier)){
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
    
    public void travaillerChantier(Joueur joueur, String chantier, int temps){
        int index=0;
        for(int i=0;i<this.tabChantier.length;i++){
            if (this.tabChantier[i].getNomChantier().equals(chantier)){
                if(this.tabChantier[i].getNbPlanche()==0){
                    if(this.tabChantier[i].getNbMetal()==0){
                        index = i;
                    }else{
                        chantier = "";
                    }
                }   
            }
        }
        switch(chantier){
                case "Mur d'enceinte":
                    this.tabChantier[index].aiderChantier(joueur, temps, this);
                    break;
                case "Fils barbelés":
                    this.tabChantier[index].aiderChantier(joueur, temps, this);
                    break;
                case "Fosses à Zombies":
                    this.tabChantier[index].aiderChantier(joueur, temps, this);
                    break;
                case "Mines":
                    this.tabChantier[index].aiderChantier(joueur, temps, this);
                    break;
                case "Porte blindées":
                    this.tabChantier[index].aiderChantier(joueur, temps, this);
                    break;
                case "Miradors":
                    this.tabChantier[index].aiderChantier(joueur, temps, this);
                    break;
                case "Abris anti-atomique": 
                    this.tabChantier[index].aiderChantier(joueur, temps, this);
                    break;
                default:
                    System.out.println("Ce chantier ne fait pas partie des chantiers disponibles pour des travaux.");  
                    break;
            } 
    }
    
    @Override
    public void prendreObjet(Joueur player, int quantite, String objet){
        switch(objet){
            case "planche":
                if(this.bois >0){
                    if(quantite>this.bois){
                        int  pris = this.bois;
                        for(int i=0;i<this.bois;i++){
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet("bois");
                            if(added){
                                this.bois -=1;
                            }else{
                                pris-=1;
                            }
                        }
                        String strBois = String.format("%d", pris);
                        System.out.println(strBois+" planche(s) ont été prises de l'entrepôt.");
                    }else{
                        int  pris = quantite;
                        for(int i=0;i<quantite;i++){
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet("bois");
                            if(added){
                                this.bois -=1;
                            }else{
                                pris-=1;
                            }
                        }
                        String strBois = String.format("%d", pris);
                        System.out.println(strBois+" planche(s) ont été prises de l'entrepôt.");
                    }
                }else{
                    System.out.println("Il n'y a plus de bois dans l'entrepot.");
                }
                break;
            case "métal":
                if(this.metal >0){
                    if(quantite>this.metal){
                        int pris = this.metal;
                        for(int i=0;i<this.metal;i++){
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet("métal");
                            if(added){
                                this.metal -=1;
                            }else{
                                pris-=1;
                            }
                        }
                        String strMetal = String.format("%d", pris);
                        System.out.println(strMetal+" morceau(x) de métal ont été pris de l'entrepôt.");
                    }else{
                        int pris = quantite;
                        for(int i=0;i<quantite;i++){
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet("métal");
                            if(added){
                                this.metal -=1;
                            }else{
                                pris-=1;
                            }
                        }
                        String strMetal = String.format("%d", pris);
                        System.out.println(strMetal+" morceau(x) de métal ont été pris de l'entrepôt.");
                    }
                }else{
                    System.out.println("Il n'y a plus de métal dans l'entrepot.");
                }
                break;
            case "boisson":
                if(this.boissonEnergisante > 0){
                    if(quantite>this.boissonEnergisante){
                        int pris = quantite;
                        for(int i=0;i<this.boissonEnergisante;i++){
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet("boisson");
                            if(added){
                                this.boissonEnergisante -=1;
                            }else{
                                pris-=1;
                            }
                        }
                        String strBoisson = String.format("%d", pris);
                        System.out.println(strBoisson+" boisson(s) énergisante(s) ont été pris de l'entrepôt.");
                    }else{
                        int pris = quantite;
                        for(int i=0;i<quantite;i++){
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet("boisson");
                            if(added){
                                this.boissonEnergisante -=1;
                            }else{
                                pris-=1;
                            }
                        }
                        String strBoisson = String.format("%d", pris);
                        System.out.println(strBoisson+" boisson(s) énergisante(s) ont été pris de l'entrepôt.");
                    }
                }else{
                    System.out.println("Il n'y a plus de boisson énergisante dans l'entrepot.");
                }
                break;
            case "ration":
                if(this.nourriture > 0){
                    if(quantite>this.nourriture){
                        for(int i=0;i<this.nourriture;i++){
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet("ration");
                            if(added){
                                this.nourriture -=1;
                            }
                        }
                    }else{
                        for(int i=0;i<quantite;i++){
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet("ration");
                            if(added){
                                this.nourriture -=1;
                            }
                        }
                    }
                }else{
                    System.out.println("Il n'y a plus de ration dans l'entrepot.");
                }
                break;
            case "gourde":
                if(player.getBu()){
                    System.out.println("Vous avez déjà eu votre ration d'eau aujourd'hui.");
                }else{
                    SacADos sac = player.getSacADos();
                    boolean added = sac.ajouterObjet("gourde");
                    if(added){
                        player.setBu();
                        System.out.println("Vous avez pris une gourde.");
                    }else{
                        System.out.println("Votre sac est plein, vous n'avez pas pu prendre la gourde.");
                    }
                }
                break;
            default:
                System.out.println("Il n'y a pas l'objet que vous souhaitez sur cette case."); 
                break;
        }
    }
    
    public void deposerObjet(Joueur player, int quantite, String objet){
        SacADos sac = player.getSacADos();
        int possede = 0;
        switch(objet){
            case "planche":
                for(int i = 0;i<sac.getSac().size();i++){
                    if("bois".equals(sac.getSac().get(i))){
                        possede +=1;
                    }
                }
                if(quantite>possede){
                    for(int i=0;i<possede;i++){
                        sac.retirerObjet("bois");
                        this.bois +=1;
                    }
                    String strQuant = String.format("%d", possede);
                    System.out.println(strQuant+" planche(s) de bois ont été déposée(s) dans l'entrepôt.");
                }else{
                    for(int i=0;i<quantite;i++){
                        sac.retirerObjet("bois");
                        this.bois +=1;
                    }
                    String strQuant = String.format("%d", quantite);
                    System.out.println(strQuant+" planche(s) de bois ont été déposée(s) dans l'entrepôt.");
                }
                break;
                case "métal":
                    for(int i = 0;i<sac.getSac().size();i++){
                        if("métal".equals(sac.getSac().get(i))){
                            possede +=1;
                        }
                    }
                    if(quantite>possede){
                        for(int i=0;i<possede;i++){
                            sac.retirerObjet("métal");
                            this.metal +=1;
                            String strQuant = String.format("%d", possede);
                            System.out.println(strQuant+" morceau(x) de métal ont été déposé(s) dans l'entrepôt.");
                        }
                    }else{
                        for(int i=0;i<quantite;i++){
                            sac.retirerObjet("métal");
                            this.metal +=1;
                        }
                        String strQuant = String.format("%d", quantite);
                        System.out.println(strQuant+" morceau(x) de métal ont été déposé(s) dans l'entrepôt.");
                    }
                    break;
                case "boisson":
                    for(int i = 0;i<sac.getSac().size();i++){
                        if("boisson".equals(sac.getSac().get(i))){
                            possede +=1;
                        }
                    }
                    if(quantite>possede){
                        for(int i=0;i<possede;i++){
                            sac.retirerObjet("boisson");
                            this.boissonEnergisante +=1;
                        }
                        String strQuant = String.format("%d", possede);
                        System.out.println(strQuant+" boisson(s) énergisante(s) ont été déposée(s) dans l'entrepôt.");
                    }else{
                        for(int i=0;i<quantite;i++){
                            sac.retirerObjet("boisson");
                            this.boissonEnergisante +=1;
                        }
                        String strQuant = String.format("%d", quantite);
                        System.out.println(strQuant+" boisson(s) énergisante(s) ont été déposée(s) dans l'entrepôt.");
                    }
                    break;
                case "ration" :
                    for(int i = 0;i<sac.getSac().size();i++){
                        if("ration".equals(sac.getSac().get(i))){
                            possede +=1;
                        }
                    }
                    if(quantite>possede){
                        for(int i=0;i<possede;i++){
                            sac.retirerObjet("ration");
                            this.nourriture +=1;
                        }
                        String strQuant = String.format("%d", possede);
                        System.out.println(strQuant+" ration(s) ont été déposée(s) dans l'entrepôt.");
                    }else{
                        for(int i=0;i<quantite;i++){
                            sac.retirerObjet("ration");
                            this.nourriture +=1;
                        }
                        String strQuant = String.format("%d", quantite);
                        System.out.println(strQuant+" ration(s) ont été déposée(s) dans l'entrepôt.");
                    }
                    break;
                default:
                    System.out.println("Vous ne possédez pas cet objet.");
                    break;
        }
    }
}

