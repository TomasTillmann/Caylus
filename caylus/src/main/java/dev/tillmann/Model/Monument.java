package dev.tillmann.model;

public abstract class Monument {
    private Residence residence;
    public void setResidence(Residence residence) { this.residence = residence; }

    public abstract void build();

    public final Player owner() {
        return residence.owner();
    }
}