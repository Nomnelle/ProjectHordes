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
    
    private ArrayList<String> sac; //liste pour les objets du sac
    
    //constructeur
    public SacADos(){
        sac = new ArrayList(); 
    }
    
    //getter
    public ArrayList<String> getSac(){
        return this.sac;
    }
    
    //toString
    @Override
    public String toString(){
       String result = "";
       
       for(int i=0;i<this.sac.size();i++){
           result+=(this.sac.get(i)+" ");
       }
       
       return result;
       
    }
    
    //méthode pour ajouter des objets au sac
    public boolean ajouterObjet(String objet){
        if(this.sac.size()==10){ //s'il y a déjà 10 objets dans le sac, on ne peut pas en rajouter
            System.out.println("Votre sac est plein, vous ne pouvez plus prendre d'objet.");
            return false;        //on renvoie false pour signifier l'echec de l'ajout de l'objet au sac
        }else{
            this.sac.add(objet);
            return true;
        }
    }

    public void retirerObjet(String objet){
        int index = this.sac.lastIndexOf(objet);
        if(index == -1){
            System.out.println("Cet objet ne fait pas partie de votre sac"); //si l'objet n'a pas été trouvé dans la liste, l'index vaut -1 eton informe le joueur qu'il ne possède pas cet objet 
        }else{
            this.sac.remove(index); //sinon, on retire l'objet du sac
        }
    }
}
