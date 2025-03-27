/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author zakina
 */
public class Caserne {
    
    private int id;
    private String nom ;
    private String rue ;
    private String cps ;
    private String ville ;
    private ArrayList<Pompier> lesPompiers ;
    
    
    public Caserne(){
    
    }

    public Caserne(int id, String nom, String rue, String cps, String ville, ArrayList<Pompier> lesPompiers) {
        this.id = id;
        this.nom = nom;
        this.rue = rue;
        this.cps = cps;
        this.ville = ville;
        this.lesPompiers = lesPompiers;
    }


    public Caserne(int id) {
        this.id = id;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCps() {
        return cps;
    }

    public void setCps(String cps) {
        this.cps = cps;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    
}
