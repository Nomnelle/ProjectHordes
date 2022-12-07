/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hordes;

import java.util.ArrayList;

/**
 *
 * @author nomnelle
 */
public class SacADos {
    
    private ArrayList<Objet> sac;
    public enum Objet{
        bois, metal, boisson, ration, gourde
    }
    
    public void ajouterObjet(Objet objet, int quantite){
        if(this.sac.size()==10){
            System.out.println("Votre sac est plein, vous ne pouvez plus prendre d'objet.");
        }else{
            for(int i=0; i<quantite;i++){
                this.sac.add(objet);
                if(this.sac.size()==10){
                    System.out.println("Vous ne pouvez pas prendre davantage d'objet");
                    break;
                }        
            }
        }
    }
    
    public void retirerObjet(Objet objet, String cas){
        int index = this.sac.lastIndexOf(objet);
        if(index == -1){
            System.out.println("Cet objet ne fait pas partie de votre sac");
        }else{
            this.sac.remove(index);
        }
    }
}
