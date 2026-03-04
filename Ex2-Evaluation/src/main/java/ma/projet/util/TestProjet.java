package ma.projet.util;

import ma.projet.service.ProjetService;
import ma.projet.service.EmployeTacheService;
import ma.projet.classes.Projet;
import ma.projet.classes.EmployeTache;
import java.util.List;

public class TestProjet {

    public static void main(String[] args) {

        ProjetService ps = new ProjetService();
        EmployeTacheService ets = new EmployeTacheService();

        int idProjet = 1;

        //  Test getTachesByProjet
        System.out.println("Tâches planifiées pour le projet :");
        ps.getTachesByProjet(idProjet)
                .forEach(t -> System.out.println(t.getNom()));

        //  Test getTachesRealisees
        System.out.println("Tâches réalisées avec dates réelles :");
        List<EmployeTache> list = ps.getTachesRealisees(idProjet);
        list.forEach(et -> System.out.println(
                et.getTache().getNom() +
                        " | " + et.getDateDebutReelle() +
                        " - " + et.getDateFinReelle()
        ));
    }
}