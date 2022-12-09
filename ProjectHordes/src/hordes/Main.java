/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hordes;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nomnelle
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println(" .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.\n"
                + "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n"
                + "| |  ____  ____  | || |     ____     | || |  _______     | || |  ________    | || |  _________   | || |    _______   | |\n"
                + "| | |_   ||   _| | || |   .'    `.   | || | |_   __ \\    | || | |_   ___ `.  | || | |_   ___  |  | || |   /  ___  |  | |\n"
                + "| |   | |__| |   | || |  /  .--.  \\  | || |   | |__) |   | || |   | |   `. \\ | || |   | |_  \\_|  | || |  |  (__ \\_|  | |\n"
                + "| |   |  __  |   | || |  | |    | |  | || |   |  __ /    | || |   | |    | | | || |   |  _|  _   | || |   '.___`-.   | |\n"
                + "| |  _| |  | |_  | || |  \\  `--'  /  | || |  _| |  \\ \\_  | || |  _| |___.' / | || |  _| |___/ |  | || |  |`\\____) |  | |\n"
                + "| | |____||____| | || |   `.____.'   | || | |____| |___| | || | |________.'  | || | |_________|  | || |  |_______.'  | |\n"
                + "| |              | || |              | || |              | || |              | || |              | || |              | |\n"
                + "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n"
                + " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'");
        int nbJoueurs = 0;
        while (nbJoueurs < 2 || nbJoueurs > 20) {
            System.out.println("Veuillez entrer le nombre de joueurs :");
            nbJoueurs = sc.nextInt();
            if (nbJoueurs > 20) {
                System.out.println("20 joueurs maximum. Rentrez une autre valeur.");
            } else if (nbJoueurs < 2) {
                System.out.println("Il faut au moins 2 joueurs. Rentrez une autre valeur.");
            }
        }

        String gagnant = jeu(nbJoueurs);
        System.out.println(gagnant+" a gagné !");

    }

    public static String jeu(int nbJoueurs) {

        Carte carte = new Carte();
        Ville ville = (Ville) carte.getCase(12, 12);

        ArrayList<Joueur> listeJoueur = new ArrayList();

        for (int i = 0; i < nbJoueurs; i++) {
            Scanner sc = new Scanner(System.in);
            String strId = String.format("%d", i);
            System.out.println("Joueur " + strId + ", rentrez votre nom :");
            String nom = sc.nextLine();
            Joueur j = new Joueur(i, nom);
            listeJoueur.add(j);
        }

        boolean game = true;
        Joueur joueur;
        Scanner sc = new Scanner(System.in);
        
        while(game){
            for(int i = 0;i<nbJoueurs;i++){
                joueur = listeJoueur.get(i);
                System.out.print("\033[H\033[2J");
                String strNom = String.format("%s",joueur.getNomJoueur());
                System.out.println("C'est au tour de "+strNom+".");
                boolean tour = true;
                while(tour){
                    System.out.println("Que souhaitez-vous faire ?");
                    String action = sc.nextLine();

                    switch (action) {
                        case "éteindre console":
                            game = false;
                        case "aller en haut":
                            carte.evaluerDeplacement(joueur, ville, action);
                        case "aller en bas":
                            carte.evaluerDeplacement(joueur, ville, action);
                        case "aller à gauche":
                            carte.evaluerDeplacement(joueur, ville, action);
                        case "aller à droite":
                            carte.evaluerDeplacement(joueur, ville, action);
                        case "fouiller":
                            carte.etreFouillee(joueur.getPositionx(), joueur.getPositiony(), joueur);
                        //manger
                        //boire à la gourde
                        //boire boisson énergisante
                        //tuer un zombie 
                        //observer ce qu'il y a sur une case
                        case "Maj carte":
                            carte.getVisuMap().maJ(carte.getCase(joueur.getPositionx(), joueur.getPositiony()));
                        case "prendre objet":
                            System.out.println("Que souhaitez-vous prendre ?");
                            String objet = sc.nextLine();
                            System.out.println("En quelle quantité ?");
                            int quantite = sc.nextInt();
                            carte.getCase(joueur.getPositionx(), joueur.getPositiony()).prendreObjet(joueur, quantite, objet);
                        //rajouter les commandes de ce qui est vérifié dans la classe ville
                        case "fin de tour":
                            tour = false;
                        default:
                            System.out.println("Cette action n'est pas possible dans ce jeu. Veuillez réessayer");
                            
                        if(joueur.getPa()==0){
                            tour = false;
                        }else if(joueur.getPv()==0){
                            tour = false;
                        }
                        
                    }
                }

            }
            
            if(listeJoueur.size()<=1){
                game = false;
            }
            
            
            carte.setTour(carte.getTour()+1);
            int compteur = 0;
            String journal = "";
            
            if(carte.getTour()==13){
                for(Joueur j:listeJoueur){
                    if((!(j.getPositiony()==12))||(!(j.getPositionx()==12))){
                        if(listeJoueur.size()<=1){
                            game = false;
                            break;
                        }else{
                            journal += j.getNomJoueur();
                        }
                        
                    }
                }
                 if(!(journal.equals(""))){
                    System.out.println(journal+"sont morts car ils étaient hors de la ville.");
                        
                }
                if((ville.getNbZombieDefendable()<ville.genererZombie())||(ville.getPorte())){
                    tuerJoueur(listeJoueur);
                }
            }
   
            
            for(Joueur j:listeJoueur){
                j.setBu();
                j.setNourri();
                j.setPa(j.getPa()+4);
                if(j.getAddiction().getTestAddiction()){
                    addiction(j);
                }
                if(j.getPv()==0){
                    compteur += 1;
                    journal += j.getNomJoueur()+" ";
                    listeJoueur.remove(j);
                }  
            }
            
            switch(compteur){
                case 0:
                    System.out.println("Personne n'est mort à ce tour");
                case 1:
                    journal += "est mort.e à ce tour.";
                    System.out.println(journal);
                default:
                    String strCompteur = String.format("%d", compteur);
                    journal += ("sont morts à ce tour. Cela fait un total de "+strCompteur+" morts.");
                    System.out.println(journal);
            }
    }
    return(listeJoueur.get(0).getNomJoueur());      

}
        

    public static void addiction(Joueur joueur) {
        if (joueur.getAddiction().getCompteurDeTour() == 0) {
            joueur.setPv(joueur.getPv() - 5);
        } else if (joueur.getAddiction().getTestAddiction()) {        // Implicite que == true
            joueur.getAddiction().setCompteurDeTour(joueur.getAddiction().getCompteurDeTour() - 1);
        }
    }
    // Récupérer objet addiction; true = commencer décompte + perte de -5 PV si 0 au décompte 
    
    public static void tuerJoueur(ArrayList <Joueur> listeJoueur){
        int taille = listeJoueur.size();
        int moitie = (int) taille / 2;
        Random ra = new Random();

        for (int i = 0; i < moitie; i++) {
            int indice = ra.nextInt(taille);
            listeJoueur.get(indice).setPv(0);
        }
    }
}
