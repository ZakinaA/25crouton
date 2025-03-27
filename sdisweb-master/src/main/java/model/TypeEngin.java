
package model;

import java.util.ArrayList;
import java.util.List;

public class TypeEngin {
    private int id;
    private String libelle;
    private int numeroOrdre;
    private List<Engin> engins; 

    public TypeEngin() {
    }

    public TypeEngin(int id) {
        this.id = id;
    }
    

    public TypeEngin(int id, String libelle, int numeroOrdre) {
        this.id = id;
        this.libelle = libelle;
        this.numeroOrdre = numeroOrdre;
        this.engins = new ArrayList<>(); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(int numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public List<Engin> getEngins() {
        return engins;
    }

    public void ajouterEngin(Engin engin) {
        this.engins.add(engin);
    }
}