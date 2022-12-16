/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hordes;

/**
 *
 * @author nomnelle
 */
public class Chantier {
    
    private final String nomChantier; //nom du chantier
    private boolean chantierFini; //si boolean=vrai, alors le chantier est finit
    private int paRestant; //nombre de PA 
    private final int nbZombieResiste; //nombre de Zombie pouvant résister
    private int nbPlanche; //nombre de planche
    private int nbMetal; //nombre de métal
    
    //constructeur
    public Chantier (String nomChantier, int paRestant, int nbZombieResiste, int nbPlanche, int nbMetal){
        this.nomChantier = nomChantier;
        this.paRestant = paRestant;
        this.chantierFini = false;
        this.nbZombieResiste = nbZombieResiste;
        this.nbPlanche = nbPlanche;
        this.nbMetal = nbMetal;
    }
    
    //getter
    public String getNomChantier(){
        return this.nomChantier;
    }
   
    public int getPaRestant(){
        return this.paRestant;
    }
    
    public boolean getChantierFini(){
        return this.chantierFini;
    }
    
    public int getNbZombieResiste(){
        return this.nbZombieResiste;
    }
    
    public int getNbPlanche(){
        return this.nbPlanche;
    }
    
    public int getNbMetal(){
        return this.nbMetal;
    }
    
    //setter
    public void setpaRestant(int i){
        this.paRestant = i;
    }
   
    public void setChantierFini(boolean chantierFini){
        this.chantierFini = true;
    }
    
    public void setNbPlanche(int i){
        this.nbPlanche = i;
    }
    
    public void setNbMetal(int i){
        this.nbMetal = i;
    }
    
    public void aiderChantier(Joueur joueur, int quantitePA, Ville ville){
        if(this.paRestant>=quantitePA){ //si le nombre de PA restant est supérieur ou égale à la quantité de PA nécessire à la construction
            if(joueur.getPa()>=quantitePA){ //
                    joueur.setPa(joueur.getPa()-quantitePA);
                    this.paRestant-=quantitePA; //on enlève au nombre de PA, la quantité utlisé pour la construction
            }else{
                this.paRestant-=joueur.getPa();
                joueur.setPa(0);
            }
        }else{
            joueur.setPa(joueur.getPa()-this.paRestant);
            this.paRestant=0;
            
        }
        if(this.paRestant<=0){
            this.chantierFini=true;
            ville.setNbZombieDefendable(ville.getNbZombieDefendable()+this.nbZombieResiste);
        }   
    }
}
