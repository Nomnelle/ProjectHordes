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
    
    public static int compteurMorts = 0;
    public static ArrayList<String> listeMort = new ArrayList();

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
        while (nbJoueurs < 2 || nbJoueurs > 20) {   //obligation de déclarer minimum 2 joueurs, et maximum 20 joueurs
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

        ArrayList<Joueur> listeJoueur = new ArrayList();  //sauvegarde des différents joueurs dans une liste

        for (int i = 0; i < nbJoueurs; i++) {
            Scanner sc = new Scanner(System.in);
            String strId = String.format("%d", i);
            System.out.println("Joueur " + strId + ", rentrez votre nom :");
            String nom = sc.nextLine();                  //on récupère le nom de chaque joueurs les uns après les autres
            Joueur j = new Joueur(i, nom);             //on crée le joueur avec le nom et l'ID
            listeJoueur.add(j);                        //on ajoute le joueur dans la liste
        }

        boolean game = true;
        Joueur joueur;
        Scanner sc = new Scanner(System.in);
        String strPa = "";
        
        while(game){                                   //tant que le jeu est en marche
            for(int i = 0;i<listeJoueur.size();i++){            //on fait joueur chaque joueur chacun son tour
                joueur = listeJoueur.get(i);
                String strNom = String.format("%s",joueur.getNomJoueur());
                System.out.println("C'est au tour de "+strNom+".");
                journalPosition(joueur, carte);
                boolean tour = true;
                while(tour){                          //tant que c'est au tour du joueur
                    strPa = String.format("%d", joueur.getPa());
                    System.out.println("Il vous reste "+strPa+" actions à réaliser.");
                    System.out.println("Que souhaitez-vous faire ?");
                    String action = sc.nextLine();    //on récupère les actions du joueur

                    switch (action) {
                        case "regarder sac":
                            System.out.println(joueur.getSacADos().toString()); 
                            break;
                        case "aller en haut":
                            carte.evaluerDeplacement(joueur, ville, action);
                            journalPosition(joueur, carte);
                            break;
                        case "aller en bas":
                            carte.evaluerDeplacement(joueur, ville, action);
                            journalPosition(joueur, carte);
                            break;
                        case "aller à gauche":
                            carte.evaluerDeplacement(joueur, ville, action);
                            journalPosition(joueur, carte);
                            break;
                        case "aller à droite":
                            carte.evaluerDeplacement(joueur, ville, action);
                            journalPosition(joueur, carte);
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
                                if(sac.getSac().get(i).equals("gourde")){ //si jamais l'utilisateur a une gourde dans son sac
                                    compteur +=1;                                      //on augmente le compteur de 1
                                }
                            }
                            if(compteur>0){
                                joueur.setPa(joueur.getPa()+6); // Gain des PA de la gourde
                                sac.retirerObjet("gourde"); // supprimer la gourde
                                System.out.println("*Glou, glou, glou*");
                            }else{
                                System.out.println("Vous n'avez pas de gourde dans votre sac.");
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
                                double perte = Math.random()+1;              // Une chance sur 10 de perdre des PV dans l'attaque, perte de PA quand attaque une fois
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
                            if((joueur.getPositionx()==12)&&(joueur.getPositiony()==12)){
                                System.out.println("C'est une très jolie ville, bien qu'un peu en ruine.");
                            }else{
                                Case emplacement = carte.getCase(joueur.getPositionx(),joueur.getPositiony()); //si le joueur n'est pas en ville, il peut observer la case sur laquelle il se trouve
                                System.out.println(emplacement.toString());
                            }
                            break;
                        case "MaJ carte":
                            carte.getVisuMap().maJ(carte.getCase(joueur.getPositionx(), joueur.getPositiony()));
                            break;
                        case "voir carte":
                            System.out.println(carte.getVisuMap().toString());
                            break;
                        case "looter":
                            if((joueur.getPositionx()==12)&&(joueur.getPositiony()==12)){
                                System.out.println("Le sol de la ville est propre.");
                            }else{                                                        //si le joueur n'est pas en ville
                                System.out.println("Que souhaitez-vous prendre ?");    
                                String objet = sc.nextLine();                             //Le joueur entre l'objet qu'il souhaite récupérer
                                System.out.println("En quelle quantité ?");           
                                int quantite = sc.nextInt();                              //Ainsi que la quantité qu'il souhaite récupérer
                                carte.getCase(joueur.getPositionx(), joueur.getPositiony()).prendreObjet(joueur, quantite, objet);
                            }
                            break;
                        case "actionner porte":
                            ville.evaluerActionVille(joueur, action);
                            break;
                        case "boire":
                            ville.evaluerActionVille(joueur, action);
                            break;
                        case "prendre objet entrepot":
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
                    if(joueur.getPa()==0){
                        tour = false;            // si le joueur n'a plus de PA, fin de tour
                    }else if(joueur.getPv()==0){
                        tour = false;            // si le joueur a plus de pv, fin de tour
                        updateMorts(listeJoueur);
                    }      
                }
                if(listeJoueur.size()<=1){      // si la liste de joueur ne contient au plus qu'un joueur, le jeu se termine
                    game = false;
                    break;
                } 

            }   
            
            carte.setTour(carte.getTour()+1);     //on incrémente le nombre de tour de 1
            String journal = "";
            
            for(Joueur j:listeJoueur){
                j.setPa(j.getPa()+4);           //les joueurs récupèrent 4 PA
                if(j.getAddiction().getTestAddiction()){
                    addiction(j);
                }
            }
            
            if(carte.getTour()==13){          //à la fin du 12e tour
                for(Joueur j:listeJoueur){
                    if(j.getBu())
                        j.setBu();            //joueurs peuvent de nouveau boire s'ils avaient bu
                    if(j.getNourri())
                        j.setNourri();            //joueurs peuvent de nouveau manger s'ils avaient mangé
                    if((!(j.getPositiony()==12))||(!(j.getPositionx()==12))){
                        j.setPv(0);             //s'ils sont en dehors de la ville, les joueurs meurent
                        updateMorts(listeJoueur);
                        if(listeJoueur.size()<=1){      // si la liste de joueur ne contient au plus qu'un joueur, le jeu se termine
                            game = false;
                            break;
                        }
                    }
                }
                 if(!(journal.equals(""))){
                    System.out.println(journal+"sont morts car ils étaient hors de la ville.");
                        
                }
                if(!(ville.getPorte())||(ville.getNbZombieDefendable()<ville.genererZombie())){
                    tuerJoueur(listeJoueur);
                    updateMorts(listeJoueur);
                    if(listeJoueur.size()<=1){      // si la liste de joueur ne contient au plus qu'un joueur, le jeu se termine
                            game = false;
                            break;
                    }
                }
                
                switch(compteurMorts){                                     //en fonction du nombre de mort à ce tour, le message change
                case 0:
                    System.out.println("Personne n'est mort à ce tour");
                    break;
                case 1:
                    journal += listeMort.get(0);
                    journal += " est mort.e à ce tour.";
                    System.out.println(journal);
                    break;
                default:
                    String strCompteur = String.format("%d", compteurMorts);
                    journal += (strCompteur+" personnes sont mortes à ce tour.");
                    System.out.println(journal);
                    break;
                }
                compteurMorts = 0;
            }
            
        }
        if(listeJoueur.size()==1){                                     //s'il reste un joueur en vie, il est déclaré gagnant
            String gagnant = listeJoueur.get(0).getNomJoueur();
            System.out.println(gagnant+" a gagné !");
        }else{                                                        //si les derniers joueurs sont morts en même temps (ex:hors ville), pas de gagnant
            System.out.println("Tout le monde est mort. Better luck next time :)");
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
    
    public static void updateMorts(ArrayList <Joueur> listeJoueur){
        for(Joueur j:listeJoueur){
                if(j.getPv()==0){                                   //si un joueur n'a plus de pv, on sauvegarde son nom, et on le retire de la liste des joueurs en jeu
                    compteurMorts += 1;
                    listeMort.add(j.getNomJoueur());
                    listeJoueur.remove(j);
                }  
            }
    }
    
    public static void journalPosition(Joueur joueur, Carte carte){
        String strPosX = String.format("%d", joueur.getPositionx());
        String strPosY = String.format("%d", joueur.getPositiony());
        String nbZombie = String.format("%d", carte.getCase(joueur.getPositionx(), joueur.getPositiony()).getZombie());
        System.out.println("Vous êtes en ["+strPosX+", "+strPosY+"], et il y a "+nbZombie+" zombie(s) autour de vous.");    //affiche position et nb de zombies autour du joueur
    }
}
