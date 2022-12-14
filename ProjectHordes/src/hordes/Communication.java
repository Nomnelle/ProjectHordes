/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hordes;

/**
 *
 * @author nomnelle
 */
public class Communication {
    
    String[][] visuMap;
    
    public Communication(){
        this.visuMap = new String[Carte.COTE][Carte.COTE];
        this.initComm();
    }
    
    @Override
    public String toString(){
        
        String carte = "";
        
        for(int i=0;i<Carte.COTE;i++){
            for(int j=0;j<Carte.COTE;j++){
                carte += "|"+this.visuMap[i][j]+"|";
            }
            carte += "\n";
        }
        
        return carte;
    }
    
    private void initComm(){
        for(int i=0;i<Carte.COTE;i++){
            for(int j=0;j<Carte.COTE;j++){
                if((i==12)&&(j==12)){
                    this.visuMap[i][j] = "Ville";
                }else{
                    this.visuMap[i][j] = "P? M? B?";
                }
            }
        }
    }
    
    public void maJ(Case position){
        if((position.getX()==12)&&(position.getY()==12)){
            System.out.println("Pas besoin de mettre à jour la carte, tout le monde sait qu'ici, c'est la ville...");
        }else{
            String strMetal = String.format("%d", position.getMetal());
            String strBois = String.format("%d", position.getBois());
            String strBoisson = String.format("%d", position.getBoissEner());
            
            this.visuMap[position.getY()][position.getX()]=("P"+strBois+" M"+strMetal+" B"+strBoisson);
        }
    }
    
}
