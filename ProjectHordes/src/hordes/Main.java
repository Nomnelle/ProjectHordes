/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hordes;

import java.util.Random;

/**
 *
 * @author nomnelle
 */
public class Main {

    public static void main(String[] args) {
        Carte carte = new Carte(2);
        
        Case caseTest = carte.getCase(0, 0);
        caseTest.decrireCase();
        
        carte.etreFouillee(0, 0);
        caseTest.decrireCase();
        
        carte.etreFouillee(12, 12);
        carte.etreFouillee(12, 13);
        
        Case caseTest2 = carte.getCase(12, 13);
        caseTest2.decrireCase();
    }
    
}
