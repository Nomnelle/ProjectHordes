/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hordesTest;

import hordes.Addiction;
import hordes.Chantier;
import hordes.Joueur;
import hordes.Ville;
import java.util.Random;

/**
 *
 * @author nomnelle
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(testDemarrerChantier()){
            System.out.println("Les projets démarrent correctement.");
        }else{
            System.out.println("Debug nécessaire pour le démarrage des projets.");
        }
        
        if(testTerminerChantier()){
            System.out.println("Les chantiers fonctionnent.");
        }else{
            System.out.println("Debug nécessaire pour que les chantiers fonctionnent.");
        }
        
        testGardeFousDemarrageChantier(); //works fine
        
        Chantier chantier = testGardeFousChantier();
        Joueur j = testGardeFousPAJoueur();
        
        assert(chantier.getPaRestant()>0);
        assert(j.getPa()!=0);
        
        Joueur[] tabJoueur = testVagueZombie1();
        
        int compteur = 0;
        
        for (Joueur tabJoueur1 : tabJoueur) {
            if (tabJoueur1.getPv() == 0) {
                compteur+=1;
            }
        }
        
        assert(compteur==2);
        
        tabJoueur = testVagueZombie1();
        
        compteur = 0;
        
        for (Joueur tabJoueur1 : tabJoueur) {
            if (tabJoueur1.getPv() == 0) {
                compteur+=1;
            }
        }
        
    }
    
    public static boolean testDemarrerChantier(){
        Joueur j1 = new Joueur(0, "Bob");
        Joueur j2 = new Joueur(0, "Tom");
        
        Ville ville = new Ville(12, 12);
        
        for(int i =0;i<10;i++){
            j1.getSacADos().ajouterObjet("bois");
            j2.getSacADos().ajouterObjet("bois");
        }
        
        ville.deposerObjet(j2, 10, "planche");
        ville.deposerObjet(j1, 10, "planche");
        
        for(int i =0;i<5;i++){
            j1.getSacADos().ajouterObjet("métal");
        }
        ville.deposerObjet(j1, 5, "métal");
        ville.evaluerActionVille(j2, "consulter entrepot");
        
        ville.demarrerChantier(j2, "Mur d'enceinte");
        Chantier chantier = ville.getTabChantier()[0];
        
        return (chantier.getNbMetal()==0)&&(chantier.getNbPlanche()==0);
    }
    
   public static boolean testTerminerChantier(){
       
        Joueur j1 = new Joueur(0, "Bob");
        Joueur j2 = new Joueur(0, "Tom");
        
        Ville ville = new Ville(12, 12);
        
        for(int i =0;i<10;i++){
            j1.getSacADos().ajouterObjet("bois");
            j2.getSacADos().ajouterObjet("bois");
        }
        
        ville.deposerObjet(j2, 10, "planche");
        ville.deposerObjet(j1, 10, "planche");
        
        for(int i =0;i<5;i++){
            j1.getSacADos().ajouterObjet("métal");
        }
        ville.deposerObjet(j1, 5, "métal");
        ville.evaluerActionVille(j2, "consulter entrepot");
        
        ville.demarrerChantier(j2, "Mur d'enceinte");
        Chantier chantier = ville.getTabChantier()[0];
        
        chantier.aiderChantier(j2, 6, ville);
        chantier.aiderChantier(j1, 6, ville);
        
        return(ville.getNbZombieDefendable()==40);
   }
   
   public static void testGardeFousDemarrageChantier(){
       
        Joueur j1 = new Joueur(0, "Bob");
        Joueur j2 = new Joueur(0, "Tom");
        
        Ville ville = new Ville(12, 12);
       
       for(int i =0;i<10;i++){
            j1.getSacADos().ajouterObjet("bois");
            j2.getSacADos().ajouterObjet("bois");
        }
        
        ville.deposerObjet(j2, 10, "planche");
        ville.deposerObjet(j1, 10, "planche");
        
        for(int i =0;i<5;i++){
            j1.getSacADos().ajouterObjet("métal");
        }
        ville.deposerObjet(j1, 5, "métal");
        ville.evaluerActionVille(j2, "consulter entrepot");
        
        ville.demarrerChantier(j2, "Mur d'enceinte");
        Chantier chantier = ville.getTabChantier()[0];
        
        ville.demarrerChantier(j2, "Mur d'enceinte");
       
   }
   
   public static Chantier testGardeFousChantier(){
        Joueur j1 = new Joueur(0, "Bob");
        Joueur j2 = new Joueur(0, "Tom");
        
        Ville ville = new Ville(12, 12);
        
        for(int i =0;i<10;i++){
            j1.getSacADos().ajouterObjet("bois");
            j2.getSacADos().ajouterObjet("bois");
        }
        
        ville.deposerObjet(j2, 10, "planche");
        ville.deposerObjet(j1, 10, "planche");
        
        for(int i =0;i<5;i++){
            j1.getSacADos().ajouterObjet("métal");
        }
        ville.deposerObjet(j1, 5, "métal");
        ville.evaluerActionVille(j2, "consulter entrepot");
        
        ville.demarrerChantier(j2, "Mur d'enceinte");
        Chantier chantier = ville.getTabChantier()[0];
        
        chantier.aiderChantier(j2, 6, ville);
        chantier.aiderChantier(j1, 6, ville);
        
        ville.evaluerActionVille(j2, "boire");
        
        chantier.aiderChantier(j2, 6, ville);
        
        return(chantier);
   }
   
   public static Joueur testGardeFousPAJoueur(){
        Joueur j1 = new Joueur(0, "Bob");
        Joueur j2 = new Joueur(0, "Tom");
        
        Ville ville = new Ville(12, 12);
        
        for(int i =0;i<10;i++){
            j1.getSacADos().ajouterObjet("bois");
            j2.getSacADos().ajouterObjet("bois");
        }
        
        ville.deposerObjet(j2, 10, "planche");
        ville.deposerObjet(j1, 10, "planche");
        
        for(int i =0;i<5;i++){
            j1.getSacADos().ajouterObjet("métal");
        }
        ville.deposerObjet(j1, 5, "métal");
        ville.evaluerActionVille(j2, "consulter entrepot");
        
        ville.demarrerChantier(j2, "Mur d'enceinte");
        Chantier chantier = ville.getTabChantier()[0];
        
        chantier.aiderChantier(j2, 6, ville);
        chantier.aiderChantier(j1, 6, ville);
        
        ville.evaluerActionVille(j2, "boire");
        
        chantier.aiderChantier(j2, 6, ville);
        
        return(j2);
   }
   
   public static Joueur[] testVagueZombie1(){
       
        Joueur j1 = new Joueur(0, "Bob");
        Joueur j2 = new Joueur(1, "Tom");
        Joueur j3 = new Joueur(2, "Tim");
        Joueur j4 = new Joueur(3, "Liz");
        
        Joueur[] joueurs = new Joueur[4];
        
        joueurs[0] = j1;
        joueurs[1] = j2;
        joueurs[2] = j3;
        joueurs[3] = j4;
        
        Ville ville = new Ville(12, 12);
        ville.setPorte();
        ville.setNbZombieDefendable(0);
        
        
        if((ville.getNbZombieDefendable()<ville.genererZombie())||!(ville.getPorte())){
            tuerJoueur(joueurs);
        }
        return(joueurs);   
   }
   
    public static Joueur[] testVagueZombie2(){
       
        Joueur j1 = new Joueur(0, "Bob");
        Joueur j2 = new Joueur(1, "Tom");
        Joueur j3 = new Joueur(2, "Tim");
        Joueur j4 = new Joueur(3, "Liz");
        
        Joueur[] joueurs = new Joueur[4];
        
        joueurs[0] = j1;
        joueurs[1] = j2;
        joueurs[2] = j3;
        joueurs[3] = j4;
        
        Ville ville = new Ville(12, 12);
        
        
        if((ville.getNbZombieDefendable()<ville.genererZombie())||!(ville.getPorte())){
            tuerJoueur(joueurs);
        }
        return(joueurs);   
   }
   
    public static void tuerJoueur(Joueur[] joueurs){
        int taille = joueurs.length;
        int moitie = (int) taille / 2;
        Random ra = new Random();

        for (int i = 0; i < moitie; i++) {
            int indice = ra.nextInt(taille);
            joueurs[indice].setPv(0);
        }
    }
}
