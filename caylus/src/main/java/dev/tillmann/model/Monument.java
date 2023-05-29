package dev.tillmann.model;

/**
 * Represent monument.
 */
public abstract class Monument {
    private Residence residence;
    public void setResidence(Residence residence) { this.residence = residence; }

    /**
     * Builds the monument for the {@link residence} owner.
     */
    public abstract void build();

    public abstract Resources cost();

    public abstract String name();

    public final Player owner() {
        return residence.owner();
    }
}
