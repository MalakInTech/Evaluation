package ma.projet.util;

import ma.projet.service.TacheService;
import ma.projet.classes.EmployeTache;
import java.util.Date;
import java.util.List;

public class TestTache{

    public static void main(String[] args) {

        TacheService ts = new TacheService();

        //  Test findExpensiveTaches
        System.out.println("Tâches > 1000 DH :");
        ts.findExpensiveTaches()
                .forEach(t -> System.out.println(t.getNom() + " : " + t.getPrix()));

        //  Test findTachesBetweenDates
        Date d1 = new Date(System.currentTimeMillis() - 86400000L * 30); // il y a 30 jours
        Date d2 = new Date(); // aujourd'hui

        System.out.println("Tâches réalisées entre deux dates :");
        List<EmployeTache> list = ts.findTachesBetweenDates(d1, d2);
        list.forEach(et -> System.out.println(
                et.getTache().getNom() +
                        " | " + et.getDateDebutReelle() +
                        " - " + et.getDateFinReelle()
        ));
    }
}