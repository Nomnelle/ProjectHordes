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
    
    private ArrayList<String> sac;
    
    public SacADos(){
        sac = new ArrayList();
    }
    
    public ArrayList<String> getSac(){
        return this.sac;
    }
    
    @Override
    public String toString(){
       String result = "";
       
       for(int i=0;i<this.sac.size();i++){
           result+=(this.sac.get(i)+" ");
       }
       
       return result;
       
    }
    
    public boolean ajouterObjet(String objet){
        if(this.sac.size()==10){
            System.out.println("Votre sac est plein, vous ne pouvez plus prendre d'objet.");
            return false;
        }else{
            this.sac.add(objet);
            return true;
        }
    }

    public void retirerObjet(String objet){
        int index = this.sac.lastIndexOf(objet);
        if(index == -1){
            System.out.println("Cet objet ne fait pas partie de votre sac");
        }else{
            this.sac.remove(index);
        }
    }
}
