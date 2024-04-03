/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

/**
 *
 * @author carpentier
 */
public class Partitions {
    private int parnum;
    private String parnom;
    private String parauteur;

    public Partitions(int parnum, String parnom, String parauteur) {
        this.parnum = parnum;
        this.parnom = parnom;
        this.parauteur = parauteur;
    }

    public int getParnum() {
        return parnum;
    }

    public void setParnum(int parnum) {
        this.parnum = parnum;
    }

    public String getParnom() {
        return parnom;
    }

    public void setParnom(String parnom) {
        this.parnom = parnom;
    }

    public String getParauteur() {
        return parauteur;
    }

    public void setParauteur(String parauteur) {
        this.parauteur = parauteur;
    }

    @Override
    public String toString() {
        return parnum + " - "+ parnom + " (" + parauteur + ") ";
    }
    
    
}
