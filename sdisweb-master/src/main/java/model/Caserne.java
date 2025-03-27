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
    private ArrayList<Pompier> lesPompiers ;
    private ArrayList<Engin> lesEngins; 

    

    public Caserne() {
    }

    public Caserne(int id) {
        this.id = id;
    }

     
    public Caserne(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.lesPompiers = new ArrayList<>();
        this.lesEngins = new ArrayList<>();
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

    public ArrayList<Pompier> getLesPompiers() {
        return lesPompiers;
    }

    public void setLesPompiers(ArrayList<Pompier> lesPompiers) {
        this.lesPompiers = lesPompiers;
    }
    
    public void addPompier(Pompier p){
        if (lesPompiers == null){
            lesPompiers = new ArrayList<Pompier>();
        }
        lesPompiers.add(p);
    }
    
    public ArrayList<Engin> getLesEngins() {
    return lesEngins;
}

     public void setLesEngins(ArrayList<Engin> lesEngins) {
        this.lesEngins = lesEngins;
    }
    public void addEngin(Engin e) {
    if (lesEngins == null) {
        lesEngins = new ArrayList<>();
    }
    lesEngins.add(e);
    e.setCaserne(this);
}

}
