package model;

public class Engin {

    private int id;
    private TypeEngin type;
    private Caserne caserne; // Ajout de la relation avec Caserne

    public Engin() {}

    public Engin(int id) {
        this.id = id;
    }

    public Engin(int id, TypeEngin type) {
        this.id = id;
        this.type = type;
    }

    public Engin(int id, TypeEngin type, Caserne caserne) {
        this.id = id;
        this.type = type;
        this.caserne = caserne;
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

    public Caserne getCaserne() {
        return caserne;
    }

    public void setCaserne(Caserne caserne) {
        this.caserne = caserne;
    }
}
