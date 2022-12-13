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
    
    public ArrayList<Objet> getSac(){
        return this.sac;
    }
    
    public boolean ajouterObjet(Objet objet){
        if(this.sac.size()==10){
            System.out.println("Votre sac est plein, vous ne pouvez plus prendre d'objet.");
            return false;
        }else{
            this.sac.add(objet);
            return true;
        }
    }

    public void retirerObjet(Objet objet){
        int index = this.sac.lastIndexOf(objet);
        if(index == -1){
            System.out.println("Cet objet ne fait pas partie de votre sac");
        }else{
            this.sac.remove(index);
        }
    }
}
