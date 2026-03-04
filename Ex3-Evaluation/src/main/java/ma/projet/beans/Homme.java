package ma.projet.beans;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Homme extends Personne {

    @OneToMany(mappedBy = "homme")
    private List<Mariage> mariages;

    public Homme(){}

    public Homme(String nom, String prenom, String telephone, String adresse, Date d){

        super(nom, prenom, telephone, adresse, d);

    }

}