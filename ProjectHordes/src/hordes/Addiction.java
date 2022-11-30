/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hordes;

/**
 *
 * @author const
 */
public class Addiction {

    private int CompteurDeTour;
    private boolean TestAddiction;

    public Addiction() {
        this.CompteurDeTour = 3;
        this.TestAddiction = false;
    }                                  // Initialisation du compteur de tour avant perte de PV d'addiction; Joueur non addict tant que pas de boisson prise

    //Getter                           
    public int getCompteurDeTour() {
        return this.CompteurDeTour;
    }
    
    public boolean getTestAddiction () {
        return this.TestAddiction;
    }

    //Setter
    public void setCompteurDeTour(int i) {
        this.CompteurDeTour = i;
    }
    
    public void setTestAddiction (boolean Test) {
        this.TestAddiction = true;
    }

    
    public void Addiction(){        // Fonction addiction
    int compteur = 3; // Compteur de 3 à 0
    boolean x = Joueur.getTesaddiction(); // Prendre l'info de si le joueur a bu une boisson
    if (boolean x == True) {  
            compteur = 3;}
    else {
        compteur -=;                 // Décrémenté si booléen f
            }
    if (compteur = 0) {              // Si le compteur arrive à 0, perte de PV du joueur
        this.Nomjoueur PertedePV(5);  // Je ne sais pas comment rédiger la perte de PV si pas de boisson au bout de 3 tours
                                     // Boucler le test jusqu'à pris de boisson; boucle while ?
    }
    }
}

// Addiction : si pas de prise de boisson sous 3 tours après la première, perte de 5 PV
