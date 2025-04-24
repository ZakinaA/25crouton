package model;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;

public class Intervention {

    private int id;
    private String lieu;
    private Date date;
    private Time heureAppel;
    private Time heureArrivee;
    private int duree;
    private int situationId;

    public int getSituationId() {
        return situationId;
    }

    
    public void setSituationId(int situationId) {
        this.situationId = situationId;
    }

    private Situation situation;
    private ArrayList<Pompier> lesPompiers;

    public Intervention() {
        this.lesPompiers = new ArrayList<>(); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeureAppel() {
        return heureAppel;
    }

    public void setHeureAppel(Time heureAppel) {
        this.heureAppel = heureAppel;
    }

    public Time getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Time heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    public ArrayList<Pompier> getLesPompiers() {
        return lesPompiers;
    }

    public void setLesPompiers(ArrayList<Pompier> lesPompiers) {
        this.lesPompiers = lesPompiers;
    }

    public void addPompier(Pompier pompier) {
        this.lesPompiers.add(pompier);
    }
}
