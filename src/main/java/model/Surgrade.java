/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.ArrayList;
/**
 *
 * @author TS1SIO
 */
public class Surgrade {
    
    private int id ;
     
    private String libelle; 
    private Grade Ungrade;
    private int grade_id;

    public Surgrade() {
    }

    public Surgrade(int id, String libelle, Grade Ungrade) {
        this.id = id;
        this.libelle = libelle;
        this.Ungrade = Ungrade;
    }

    public Surgrade(int id, String libelle, Grade Ungrade, int grade_id) {
        this.id = id;
        this.libelle = libelle;
        this.Ungrade = Ungrade;
        this.grade_id = grade_id;
    }
    

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Grade getUngrade() {
        return Ungrade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setUngrade(Grade Ungrade) {
        this.Ungrade = Ungrade;
    }

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }
    
    
    
    
    
}
