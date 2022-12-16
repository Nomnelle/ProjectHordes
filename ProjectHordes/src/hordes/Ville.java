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
    
    private int nbZombieDefendable; //nombre de zombie qu'il est possible résister lors d'une attaque
    private int minimumZombie; //nombre de zombie minimum de zombie 
    private int nourriture; //nombre de nourriture
    private boolean porte; //vrai si la porte est ouverte
    private Chantier[] tabChantier; //tableau des noms des différentes constructions ainsi que le nombre de PA, de zombie, de planche et de métal associé
    
    //constructeur
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
        String[] nom = {"Mur d'enceinte", "Fils barbelés", "Fosses à Zombies","Mines","Porte blindées","Miradors","Abris anti-atomique"}; //tableau "nom" qui contient les différents types de constructions
        int[] pa = {10,20,30,30,40,50,60}; //tableau des PA nécessaire à chaque type de constructions
        int[] nbZombieResiste = {20,30,50,50,100,200,500}; //tableau du nombre de zombie maximum que les constructions peuvent supporter
        int[] nbPlanche = {20,20,50,10,50,75,100}; //tableau du nombre de planche nécessiare aux différentes constructions
        int[] nbMetal = {5,30,25,50,50,75,200}; //tableau du nombre de métal nécessaire aux différentes constructions
        for(int i=0;i<7;i++){
            this.tabChantier[i]=new Chantier(nom[i],pa[i],nbZombieResiste[i],nbPlanche[i],nbMetal[i]); //tableau associant les différentes informations correspondantes entre elles
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
    //fonction qui génère des zombies
    public int genererZombie(){
        Random ra = new Random();
        int nbZombie = this.minimumZombie + ra.nextInt(11);
        this.minimumZombie+=10;
        return nbZombie;
                
    }
    
    public void evaluerActionVille(Joueur player, String action){
        Scanner sc = new Scanner(System.in);
        if((player.getPositionx()==this.x)&&(player.getPositiony()==this.y)){ //condition vérifiant si la position du joueur est bonne
            switch(action){ //les différentes actions que le joueur peut faire
                case "actionner porte":
                    this.setPorte();
                    if(!(this.porte)){ //si la porte n'est pas ouverte
                        System.out.println("La porte a été ouverte.");
                    }else{
                        System.out.println("La porte a été fermée.");
                    }
                    break;
                case "boire":
                    if(!(player.getBu())){ //si le joueur n'a pas bu
                        player.setBu(); 
                        player.setPa(player.getPa()+6); //après avoir bu, le joueur gagne 6 PA
                    }else{ //dans le cas où le joueur a déjà bu
                        System.out.println("Vous n'avez plus besoin de boire pour aujourd'hui.");
                    }
                    break;
                case "deposer objet entrepot":
                    System.out.println("Quel objet voulez-vous déposer ? Entrez son nom.");
                    String objet = sc.nextLine(); //sélectionne l'objet que le joueur souhaite déposer
                    System.out.println("En quelle quantité ? Entrez un numérique.");
                    int quantite = sc.nextInt(); //la quantité de l'objet en question à déposer
                    this.deposerObjet(player, quantite, objet); //le joueur dépose une certaine quantité de l'objet sélectionner
                    break;
                case "prendre objet entrepot":
                    System.out.println("Quel objet voulez-vous déposer ? Entrez son nom.");
                    objet = sc.nextLine(); //sélectionne l'objet que le joueur souhaite prendre
                    System.out.println("En quelle quantité ? Entrez un numérique.");
                    quantite = sc.nextInt(); //la quantité de l'objet en question à prendre
                    this.prendreObjet(player, quantite, objet); //le joueur prend une certaine quantité de l'objet sélectionner
                    break;
                case "demarrer nouveau chantier":
                    System.out.println("Quel chantier voulez-vous démarrer ? Entrez son nom.");
                    String chantier = sc.nextLine(); //sélectionne le chantier à démarer 
                    this.demarrerChantier(player, chantier); //le chantier choisi par le joueur démarre 
                    break;
                case "travailler sur chantier":
                    System.out.println("Sur quel chantier voulez vous travailler ? Entrez son nom.");
                    chantier = sc.nextLine(); //sélectionne le chantier 
                    System.out.println("Durant combien de temps ? Entrez nombre de Pa.");
                    int temps = sc.nextInt(); //définit la durée de travail en fonction des PA
                    this.travaillerChantier(player, chantier, temps); //le joueur travaille sur le chantier choisi pendant la durée choisi
                    break;
                case "consulter entrepot":
                    System.out.println(this.toString()); //permet de consulter les différents objets contenur dans l'entrepot
                    break;
            }
        }
    }
    
    //fonction où le joueur entre le nom du chantier qu'il souhaite démarrer
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
    
    //fonction qui permet d'autoriser la construction d'un chantier
    public void autoriserChantier(String chantier){
        int index=0; //initialisation de la variable local index
        for(int i=0;i<this.tabChantier.length;i++){
            if (this.tabChantier[i].getNomChantier().equals(chantier)){ //condition qui permet de vérifier si le nom saisit correspond à un des noms existant
                index = i; //alors l'indice(index) correspond à l'indice du tableau Chantier
            }
        }
        if((tabChantier[index].getNbPlanche() == 0)&&(tabChantier[index].getNbMetal() ==0)){ //si la valeur à l'indice correspondant au nombre de planche et la valeur à l'indice correspondant au nombre de métal
            System.out.println("Ce chantier a déjà été commencé");
        }else if ((tabChantier[index].getNbPlanche()>this.bois)||(tabChantier[index].getNbMetal()>this.metal)){ //si le nombre nécessaire de métal et de planche de bois est supérieur au nombre de planche de bois et de métal disponible
            System.out.println("Vous n'avez pas assez de ressources pour construire ce chantier");
        }else{
            this.bois-=tabChantier[index].getNbPlanche(); //soustration du nombre de planche de bois nécessaire au nombre de planche de bois diponible
            this.metal-=tabChantier[index].getNbMetal(); //soustraction du nombre de métal nécessiare au nombre de métal disponible
                        
            tabChantier[index].setNbPlanche(0); //on met 0 au nombre de planche nécessaire car un chantier est en cours
            tabChantier[index].setNbMetal(0); //on met 0 au nombre de métal nécessaire car un chantier est en cours
        }
        
    }
    //fonction qui permet au joueur de traviller dans une chantier et une durée choisi
    public void travaillerChantier(Joueur joueur, String chantier, int temps){
        int index=0;
        for(int i=0;i<this.tabChantier.length;i++){
            if (this.tabChantier[i].getNomChantier().equals(chantier)){ //condition qui permet de vérifier si le nom saisit correspond à un des noms existant
                if(this.tabChantier[i].getNbPlanche()==0){ //si un chantier est en cours
                    if(this.tabChantier[i].getNbMetal()==0){ //si un chantier est en cours
                        index = i;
                    }else{ 
                        chantier = "";
                    }
                }   
            }
        }
        switch(chantier){ //un joueur saisit le chantier souhaiter et y travail pendant une durée choisi
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
                default: //dans le cas où le nom saisi ne correspond à aucun des noms disponibles
                    System.out.println("Ce chantier ne fait pas partie des chantiers disponibles pour des travaux.");  
                    break;
            } 
    }
    
    @Override
    public void prendreObjet(Joueur player, int quantite, String objet){ //fonction permet de prendre un objet choisi, en quantité choisi
        switch(objet){ //objet choisi par le joueur
            case "planche":
                if(this.bois >0){ 
                    if(quantite>this.bois){ //si la quantité demandé est supérieur à celle disponible
                        int  pris = this.bois; //équivaut au nombre de planche de bois 
                        for(int i=0;i<this.bois;i++){
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet("bois"); //si booléen vrai=l'objet "bois" a été ajouté à sac à dos
                            if(added){ //si l'objet a été ajouter
                                this.bois -=1; //on l'enlève de la quantité de planche de bois disponible
                            }else{
                                pris-=1; 
                            }
                        }
                        String strBois = String.format("%d", pris); //nombre de planche de bois pris dans l'entrepot
                        System.out.println(strBois+" planche(s) ont été prises de l'entrepôt.");
                    }else{ 
                        int  pris = quantite; //"quantité" correspond à la quantité pris dans l'entrepot
                        for(int i=0;i<quantite;i++){
                            SacADos sac = player.getSacADos();
                            boolean added = sac.ajouterObjet("bois"); 
                            if(added){
                                this.bois -=1; //soustraction des planches de bois jusqu'à la quantité demandé
                            }else{
                                pris-=1;
                            }
                        }
                        String strBois = String.format("%d", pris);
                        System.out.println(strBois+" planche(s) ont été prises de l'entrepôt.");
                    }
                }else{ //si la quantité de bois est inférieur à 0
                    System.out.println("Il n'y a plus de bois dans l'entrepot.");
                }
                break;
            case "metal": //même principe qu'avec les planches de bois
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
            case "boisson": //même principe qu'avec les planches de bois et de métal
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
    //fonction qui permet au joueur de déposer des objets dans l'entrepot
    public void deposerObjet(Joueur player, int quantite, String objet){
        SacADos sac = player.getSacADos();
        int possede = 0;
        switch(objet){
            case "planche":
                for(int i = 0;i<sac.getSac().size();i++){
                    if("bois".equals(sac.getSac().get(i))){ //condition qui vérifie si l'objet "bois" est disponible dans le sac à dos
                        possede +=1; //compeur qui permet de compter les objets disponible dans le sac à dos
                    }
                }
                if(quantite>possede){ //si la quantité demandé est supérieur à celle disponible dans le sac à dos
                    for(int i=0;i<possede;i++){
                        sac.retirerObjet("bois"); //retire la quantité maximale de bois que le sac à dos possède
                        this.bois +=1; //ajout le nombre de planche retirer du sac à dos à l'entrepot
                    }
                    String strQuant = String.format("%d", possede); //quantité de planches de bois déposé dans l'entrepot
                    System.out.println(strQuant+" planche(s) de bois ont été déposée(s) dans l'entrepôt.");
                }else{ //si la quantité demandé est inférieur à la quantité disponible
                    for(int i=0;i<quantite;i++){
                        sac.retirerObjet("bois");
                        this.bois +=1; //ajout le nombre de planche retirer du sac à dos à l'entrepot
                    }
                    String strQuant = String.format("%d", quantite); //quantité de planches de bois déposé dans l'entrepot
                    System.out.println(strQuant+" planche(s) de bois ont été déposée(s) dans l'entrepôt.");
                }
                break;
                case "métal": //même chose qu'avec les planches de bois
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
                case "boisson": //même chose qu'avec les planches de bois et de métal
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
                case "ration" : //même chose que les autres objets
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
                default: //dans le cas où le nom saisit ne correspond à aucun des objets
                    System.out.println("Vous ne possédez pas cet objet.");
                    break;
        }
    }
}

