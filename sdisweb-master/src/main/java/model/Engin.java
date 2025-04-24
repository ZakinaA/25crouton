package model;

import java.util.ArrayList;
import java.util.List;

public class Engin {

    private int id;
    private TypeEngin type;
    private List<Caserne> casernes; 

    public Engin() {}

    public Engin(int id) {
        this.id = id;
        this.casernes = new ArrayList<>(); 
    }

    public Engin(int id, TypeEngin type) {
        this.id = id;
        this.type = type;
        this.casernes = new ArrayList<>(); 
    }

    public Engin(int id, TypeEngin type, List<Caserne> casernes) {
        this.id = id;
        this.type = type;
        this.casernes = (casernes != null) ? casernes : new ArrayList<>(); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeEngin getType() {
        return type;
    }

    public void setType(TypeEngin type) {
        this.type = type;
    }

    public List<Caserne> getCasernes() {
        return casernes;
    }

    public void setCasernes(List<Caserne> casernes) {
        this.casernes = (casernes != null) ? casernes : new ArrayList<>();
    }


}