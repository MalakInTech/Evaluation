package ma.projet.util;

import ma.projet.service.EmployeService;
import ma.projet.service.ProjetService;
import ma.projet.service.EmployeTacheService;
import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.service.TacheService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestEmploye {

    public static void main(String[] args) throws ParseException {

        EmployeService es = new EmployeService();
        ProjetService ps = new ProjetService();
        TacheService ts = new TacheService();
        EmployeTacheService ets = new EmployeTacheService();

        // Création d’un employé
        Employe e1 = new Employe("Ali", "Rami", "0645678901");
        es.create(e1);

        // Création projet
        Projet p1 = new Projet("Gestion de Stock", new Date(), new Date(), e1);
        ps.create(p1);

        // Création taches
        Tache t1 = new Tache("Analyse", 1200, p1);
        Tache t2 = new Tache("Conception", 900, p1);
        Tache t3 = new Tache("Développement", 2000, p1);
        ts.create(t1);
        ts.create(t2);
        ts.create(t3);


        // Creer les dates
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date debut1 = sdf.parse("01/02/2026");
        Date fin1   = sdf.parse("05/02/2026");

        Date debut2 = sdf.parse("10/02/2026");
        Date fin2   = sdf.parse("15/02/2026");

        Date debut3 = sdf.parse("28/02/2026");
        Date fin3 = sdf.parse("03/03/2026");


        // Créer les tâches
        EmployeTache et1 = new EmployeTache(e1, t1, debut1, fin1);
        EmployeTache et2 = new EmployeTache(e1, t2, debut2, fin2);
        EmployeTache et3 = new EmployeTache(e1, t3, debut3, fin3);
        ets.create(et1);
        ets.create(et2);
        ets.create(et3);


        //  Test getTachesByEmploye
        List<EmployeTache> taches = es.getTachesByEmploye(e1.getId());
        System.out.println("Tâches de l’employé " + e1.getNom() + " :");
        taches.forEach(et -> System.out.println(et.getTache().getNom()));

        //  Test getProjetsByEmploye
        List<Projet> projets = es.getProjetsByEmploye(e1.getId());
        System.out.println("Projets gérés par " + e1.getNom() + " :");
        projets.forEach(pr -> System.out.println(pr.getNom()));
    }
}