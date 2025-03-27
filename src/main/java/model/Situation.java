package model;

import java.util.ArrayList;


public class Situation {

    private int id;
    private String type;
    private ArrayList<Intervention> lesInterventions; 

    public Situation() {
    }

    public Situation(int id, String type) {
        this.id = id;
        this.type = type;
        this.lesInterventions = new ArrayList<>(); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Intervention> getLesInterventions() {
        return lesInterventions;
    }

    public void setLesInterventions(ArrayList<Intervention> lesInterventions) {
        this.lesInterventions = lesInterventions;
    }

    public void addIntervention(Intervention intervention) {
        this.lesInterventions.add(intervention);
    }

}