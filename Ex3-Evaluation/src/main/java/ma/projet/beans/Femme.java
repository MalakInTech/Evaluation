package ma.projet.beans;

import javax.persistence.Entity;

@Entity
public class Femme extends Personne {

    public Femme() {}

    public Femme(String nom, String prenom, String telephone, String adresse, java.util.Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }
}
