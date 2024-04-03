    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

/**
 *
 * @author carpentier
 */
public class Eleve {
    private String elenom, eleprenom, elelogin, elemdp;
    private int elenum, disnum, elecycle, eleanneecycle;

    public Eleve(int elenum, int disnum, String elenom, String eleprenom, int elecycle, int eleanneecycle, String elelogin, String elemdp) {
        this.elenom = elenom;
        this.eleprenom = eleprenom;
        this.elelogin = elelogin;
        this.elemdp = elemdp;
        this.elenum = elenum;
        this.disnum = disnum;
        this.elecycle = elecycle;
        this.eleanneecycle = eleanneecycle;
    }

    public String getElenom() {
        return elenom;
    }

    public void setElenom(String elenom) {
        this.elenom = elenom;
    }

    public String getEleprenom() {
        return eleprenom;
    }

    public void setEleprenom(String eleprenom) {
        this.eleprenom = eleprenom;
    }

    public String getElelogin() {
        return elelogin;
    }

    public void setElelogin(String elelogin) {
        this.elelogin = elelogin;
    }

    public String getElemdp() {
        return elemdp;
    }

    public void setElemdp(String elemdp) {
        this.elemdp = elemdp;
    }

    public int getElenum() {
        return elenum;
    }

    public void setElenum(int elenum) {
        this.elenum = elenum;
    }

    public int getDisnum() {
        return disnum;
    }

    public void setDisnum(int disnum) {
        this.disnum = disnum;
    }

    public int getElecycle() {
        return elecycle;
    }

    public void setElecycle(int elecycle) {
        this.elecycle = elecycle;
    }

    public int getEleanneecycle() {
        return eleanneecycle;
    }

    public void setEleanneecycle(int eleanneecycle) {
        this.eleanneecycle = eleanneecycle;
    }
    
    
    
    
}
