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

    
    //public void Addiction(){        // Fonction addiction
    // Compteur de 3 à 0, décrémenté si booléen v ou f (à def), si arrive à 0, perte de PV
    //}
}

// Addiction : si pas de prise de boisson sous 3 tours après la première, perte de 5 PV
