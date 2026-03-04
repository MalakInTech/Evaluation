package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(
        name = "Tache.findExpensive",
        query = "SELECT t FROM Tache t WHERE t.prix > 1000"
)
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private double prix;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;


    @ManyToOne
    private Projet projet;

    @OneToMany(mappedBy = "tache")
    private List<EmployeTache> employeTaches;

    public Tache() {}

    public Tache(String nom, double prix, Projet projet) {
        this.nom = nom;
        this.prix = prix;
        this.projet = projet;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
