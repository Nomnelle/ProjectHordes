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
    
    String[][] visuMap; //visualisation la carte avec les communications des joueurs annotées dessus
    
    //constructeur
    public Communication(){
        this.visuMap = new String[Carte.COTE][Carte.COTE];
        this.initComm();
    }
    
    //toString
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
    
    //initalisation du tableau visuMap
    private void initComm(){
        for(int i=0;i<Carte.COTE;i++){
            for(int j=0;j<Carte.COTE;j++){
                if((i==12)&&(j==12)){
                    this.visuMap[i][j] = "Ville"; //si au milieu de la carte, il s'agit de la ville
                }else{
                    this.visuMap[i][j] = "P? M? B?"; //sinon on note que l'on ne connait pas encore les quantité en composant que la map
                }
            }
        }
    }
    
    //méthode pour mettre à jour la carte
    public void maJ(Case position){
        if((position.getX()==12)&&(position.getY()==12)){
            System.out.println("Pas besoin de mettre à jour la carte, tout le monde sait qu'ici, c'est la ville..."); //on empêche le joueur de mettre à jour la ville
        }else{
            if(position.getFouille()){
                String strMetal = String.format("%d", position.getMetal());          
                String strBois = String.format("%d", position.getBois());
                String strBoisson = String.format("%d", position.getBoissEner());
            
                this.visuMap[position.getY()][position.getX()]=("P"+strBois+" M"+strMetal+" B"+strBoisson);  //si le joueur n'est pas en ville, et que la case sur laquelle il est à été fouillée, il communique les composants
            }else{
                System.out.println("Il faut que vous fouillez cette case avant de pouvoir mettre à jour la carte..."); //sinon on lui dit de d'abord fouiller la case
            }
        }
    }
    
}
