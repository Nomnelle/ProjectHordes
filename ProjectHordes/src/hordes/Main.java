/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hordes;

import java.util.ArrayList;
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

        jeu(nbJoueurs);

    }

    public static void jeu(int nbJoueurs) {

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
        
        while(game){
            for(int i = 0;i<nbJoueurs;i++){
                joueur = listeJoueur.get(i);
                System.out.print("\033[H\033[2J");
                String strNom = String.format("%s",joueur.getNomJoueur());
                System.out.println("C'est au tour de "+strNom+".");
                for(int j=5;j>0;j--){
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Que souhaitez-vous faire ?");
                    String action = sc.nextLine();
                    
                    switch(action){
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
                        default:
                            System.out.println("Cette action n'est pas possible dans le jeu. Veuillez réessayer");
                    }
                }
                
            }
        }

    }

    public static void addiction(boolean testAddiction, int compteur) {
        compteur = 3;
        while (compteur != 0) {
            if (testAddiction == true) {         // Si joueur addicte, commencer à décrémenter
                compteur = compteur - 1;
            }
        }
        while (testAddiction == false) {         // Si arrivé à 0, perte de PV jusque prise de boisson; Comment éviter boucle infinie ? Risque ici non ?
            // rédiger perte de PV help
        }
    }
    // Réccupérer objet addiction; true = commencer décompte + perte de -5 PV si 0 au décompte 
}
