package ma.projet.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Mariage {

    @EmbeddedId
    private MariageId id = new MariageId();

    @ManyToOne
    @MapsId("hommeId")
    @JoinColumn(name="homme_id")
    private Homme homme;

    @ManyToOne
    @MapsId("femmeId")
    @JoinColumn(name="femme_id")
    private Femme femme;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private int nombreEnfants;

    public Mariage() {}

    public Mariage(Homme homme, Femme femme, Date dateDebut, Date dateFin, int nombreEnfants) {
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombreEnfants = nombreEnfants;
        this.id = new MariageId(homme.getId(), femme.getId());
    }

    // getters et setters
    public MariageId getId() { return id; }
    public Homme getHomme() { return homme; }
    public Femme getFemme() { return femme; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public int getNombreEnfants() { return nombreEnfants; }
    public void setNombreEnfants(int nombreEnfants) { this.nombreEnfants = nombreEnfants; }
}