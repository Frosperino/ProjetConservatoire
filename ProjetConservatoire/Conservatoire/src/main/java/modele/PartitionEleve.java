/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

/**
 *
 * @author carpentier
 */
public class PartitionEleve {
    private int parnum, numeropageclasseur;
    private String parnom, parauteur;
    

    public PartitionEleve(int parnum, int numeropageclasseur, String parnom, String parauteur) {
        this.parnum = parnum;
        this.numeropageclasseur = numeropageclasseur;
        this.parnom = parnom;
        this.parauteur = parauteur;
    }

    public String getParauteur() {
        return parauteur;
    }

    public void setParauteur(String parauteur) {
        this.parauteur = parauteur;
    }

    public String getParnom() {
        return parnom;
    }

    public void setParnom(String parnom) {
        this.parnom = parnom;
    }

    public int getParnum() {
        return parnum;
    }

    public void setParnum(int parnum) {
        this.parnum = parnum;
    }

    public int getNumeropageclasseur() {
        return numeropageclasseur;
    }

    public void setNumeropageclasseur(int numeropageclasseur) {
        this.numeropageclasseur = numeropageclasseur;
    }

    @Override
    public String toString() {
        return parnum + " - " + parnom + " (" + parauteur +  ") page : " + numeropageclasseur;
    }
    
    
    
}
