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
    
    private String nomChantier;
    
    public void setPa(int pa){
        this.pa = pa;
    }
    
    public Chantier (String nomChantier, int pa){
        this.nomChantier = nomChantier;
        this.pa = pa;
        this.chantierFini = true;
    }
    
    
   
    
    private boolean chantierFini;
    
    //getter
    public boolean getChantierFini(){
        return this.chantierFini;
    }
    
    //setter
    public void setChantierFini(boolean chantierFini){
        this.chantierFini = true;
    }
}
