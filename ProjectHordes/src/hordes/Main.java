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

        jeu(nbJoueurs);
    }

    public static void jeu(int nbJoueurs) {

        Carte carte = new Carte();
        Ville ville =  new Ville(12,12);

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
            if(listeJoueur.size()==1){
                    game = false;
            }
            for(int i = 0;i<nbJoueurs;i++){
                joueur = listeJoueur.get(i);
                String strNom = String.format("%s",joueur.getNomJoueur());
                System.out.println("C'est au tour de "+strNom+".");
                boolean tour = true;
                while(tour){
                    if(joueur.getPa()==0){
                        tour = false;
                    }else if(joueur.getPv()==0){
                        tour = false;
                    }
                    String strPosX = String.format("%d", joueur.getPositionx());
                    String strPosY = String.format("%d", joueur.getPositiony());
                    String nbZombie = String.format("%d", carte.getCase(joueur.getPositionx(), joueur.getPositiony()).getZombie());
                    System.out.println("Vous êtes en ["+strPosX+", "+strPosY+"], et il y a "+nbZombie+" zombie(s) autour de vous.");
                    System.out.println("Que souhaitez-vous faire ?");
                    String action = sc.nextLine();

                    switch (action) {
                        case "éteindre console":
                            game = false;
                            break;
                        case "aller en haut":
                            carte.evaluerDeplacement(joueur, ville, action);
                            break;
                        case "aller en bas":
                            carte.evaluerDeplacement(joueur, ville, action);
                            break;
                        case "aller à gauche":
                            carte.evaluerDeplacement(joueur, ville, action);
                            break;
                        case "aller à droite":
                            carte.evaluerDeplacement(joueur, ville, action);
                            break;
                        case "fouiller":
                            carte.etreFouillee(joueur.getPositionx(), joueur.getPositiony(), joueur);
                            break;
                        case "manger":
                            if (joueur.getNourri()== true){
                                System.out.println("Vous avez déjà mangé.");  // Vérifier que le joueur n'a pas déjà mangé
                            } else {
                                joueur.setPa(joueur.getPa()+6);                 // Gain des PA de la ration
                                joueur.setNourri();                             // Dire que le joueur a mangé
                            }
                            break;
                        case "boire à la gourde":
                            SacADos sac = joueur.getSacADos();
                            int compteur = 0;
                            for(i = 0;i<sac.getSac().size();i++){
                                if(sac.getSac().get(i).equals("gourde")){
                                    compteur +=1;
                                }
                            }
                            if(compteur>0){
                                joueur.setPa(joueur.getPa()+6); // Gain des PA de la gourde
                                sac.retirerObjet("gourde"); // supprimer la gourde
                            }                         
                            break;
                        case "boire boisson énergisante":
                            if (joueur.getAddiction().getTestAddiction() == true) {
                                joueur.getAddiction().setCompteurDeTour(3);          // Si joueur addicte, réinitialisation de son compteur
                            } else {
                                joueur.getAddiction().setTestAddiction(true);              // Si joueur non addicte, début d'addiction et gain de PA
                                joueur.setPa(joueur.getPa()+4);
                            } 
                            break;
                        case "tuer zombie":
                            if(carte.getCase(joueur.getPositionx(),joueur.getPositiony()).getZombie()>0){
                                joueur.setPa(joueur.getPa()-1);
                                Case place = carte.getCase(joueur.getPositionx(),joueur.getPositiony()); // Récupération de la case sur laquelle se situe le joueur
                                place.setZombie(place.getZombie()-1);
                                double perte = (Math.random());              // Une chance sur 10 de perdre des PV dans l'attaque, perte de PA quand attaque une fois
                                if (perte<=0.9) {
                                    System.out.println("Vous vous en sortez bien.");}
                                else{
                                    joueur.setPv(joueur.getPv()-10);
                                    System.out.println("Vous vous êtes pris un coup en retour!");
                                }
                            }else{
                                    System.out.println("Il n'y a aucun zombie autour de vous.");
                            }
                            break;
                        case "observer case":
                            Case emplacement = carte.getCase(joueur.getPositionx(),joueur.getPositiony());
                            System.out.println(emplacement.toString());
                            break;
                        case "MaJ carte":
                            carte.getVisuMap().maJ(carte.getCase(joueur.getPositionx(), joueur.getPositiony()));
                            break;
                        case "voir carte":
                            System.out.println(carte.getVisuMap().toString());
                            break;
                        case "looter":
                            System.out.println("Que souhaitez-vous prendre ?");
                            String objet = sc.nextLine();
                            System.out.println("En quelle quantité ?");
                            int quantite = sc.nextInt();
                            carte.getCase(joueur.getPositionx(), joueur.getPositiony()).prendreObjet(joueur, quantite, objet);
                            break;
                        case "actionner porte":
                            ville.evaluerActionVille(joueur, action);
                            break;
                        case "boire":
                            ville.evaluerActionVille(joueur, action);
                            break;
                        case "prendre objet":
                            ville.evaluerActionVille(joueur, action);
                        case "deposer objet entrepot":
                            ville.evaluerActionVille(joueur, action);
                            break;
                        case "demarrer nouveau chantier":
                            ville.evaluerActionVille(joueur, action);
                            break;
                        case "travailler sur chantier":
                            ville.evaluerActionVille(joueur, action);
                            break;
                        case "consulter entrepot":
                            ville.evaluerActionVille(joueur, action);
                            break;
                        case "fin de tour":
                            tour = false;
                            break;
                        default:
                            System.out.println("Cette action n'est pas possible dans ce jeu. Veuillez réessayer");
                            break;
                    }
                }

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
                    break;
                case 1:
                    journal += "est mort.e à ce tour.";
                    System.out.println(journal);
                    break;
                default:
                    String strCompteur = String.format("%d", compteur);
                    journal += ("sont morts à ce tour. Cela fait un total de "+strCompteur+" morts.");
                    System.out.println(journal);
                    break;
            }
            String gagnant = listeJoueur.get(0).getNomJoueur();
            System.out.println(gagnant+" a gagné !");
        }

    }
        

    public static void addiction(Joueur joueur) {
        if (joueur.getAddiction().getCompteurDeTour() == 0) {
            joueur.setPv(joueur.getPv() - 5);                         
        } else if (joueur.getAddiction().getTestAddiction()) {        // Si le joueur est addicte, on décompte un tour sans boisson énergisante 
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
