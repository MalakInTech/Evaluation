package ma.projet.util;

import ma.projet.beans.*;
import ma.projet.service.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {

        HommeService hs = new HommeService();
        FemmeService fs = new FemmeService();
        MariageService ms = new MariageService();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        //  Créer 10 femmes

        Femme f1 = new Femme("Safi", "Salima", "0601", "Casa", sdf.parse("01/01/1970"));
        Femme f2 = new Femme("Ali", "Amal", "0602", "Rabat", sdf.parse("02/02/1975"));
        Femme f3 = new Femme("Alaoui", "Wafa", "0603", "Fes", sdf.parse("03/03/1980"));
        Femme f4 = new Femme("Alami", "Karima", "0604", "Meknes", sdf.parse("04/04/1965"));
        Femme f5 = new Femme("Rami", "Hiba", "0605", "Agadir", sdf.parse("05/05/1990"));
        Femme f6 = new Femme("Zahra", "Nadia", "0606", "Tanger", sdf.parse("06/06/1985"));
        Femme f7 = new Femme("Lahlou", "Sara", "0607", "Oujda", sdf.parse("07/07/1992"));
        Femme f8 = new Femme("Benali", "Meryem", "0608", "Kenitra", sdf.parse("08/08/1988"));
        Femme f9 = new Femme("Idrissi", "Imane", "0609", "Tetouan", sdf.parse("09/09/1978"));
        Femme f10 = new Femme("Tahiri", "Soukaina", "0610", "El Jadida", sdf.parse("10/10/1995"));

        fs.create(f1); fs.create(f2); fs.create(f3); fs.create(f4); fs.create(f5);
        fs.create(f6); fs.create(f7); fs.create(f8); fs.create(f9); fs.create(f10);


        //  Créer 5 hommes

        Homme h1 = new Homme("Safi", "Said", "0701", "Casa", sdf.parse("01/01/1960"));
        Homme h2 = new Homme("Ali", "Youssef", "0702", "Rabat", sdf.parse("02/02/1970"));
        Homme h3 = new Homme("Rami", "Hassan", "0703", "Fes", sdf.parse("03/03/1980"));
        Homme h4 = new Homme("Karim", "Omar", "0704", "Marrakech", sdf.parse("04/04/1975"));
        Homme h5 = new Homme("Lahlou", "Anas", "0705", "Agadir", sdf.parse("05/05/1985"));

        hs.create(h1); hs.create(h2); hs.create(h3); hs.create(h4); hs.create(h5);


        //  Créer des mariages

        ms.create(new Mariage(h1, f1, sdf.parse("03/09/1990"), null, 4));
        ms.create(new Mariage(h1, f2, sdf.parse("03/09/1995"), null, 2));
        ms.create(new Mariage(h1, f3, sdf.parse("04/11/2000"), null, 3));
        ms.create(new Mariage(h1, f4, sdf.parse("03/09/1989"), sdf.parse("03/09/1990"), 0));


        //  Afficher liste des femmes

        System.out.println("Liste des femmes :");
        for (Femme f : fs.findAll()) {
            System.out.println(f.getNom() + " " + f.getPrenom());
        }


        //  Femme la plus âgée

        Femme oldest = fs.findAll()
                .stream()
                .min((a, b) -> a.getDateNaissance().compareTo(b.getDateNaissance()))
                .orElse(null);

        System.out.println("\nFemme la plus âgée : "
                + oldest.getNom() + " " + oldest.getPrenom());


        //  Épouses entre deux dates

        System.out.println("\nÉpouses de " + h1.getNom());

        List<Mariage> mariages = hs.getEpousesEntreDeuxDates(
                h1.getId(),
                sdf.parse("01/01/1980"),
                sdf.parse("01/01/2025")
        );

        for (Mariage m : mariages) {
            System.out.println(m.getFemme().getNom() + " "
                    + m.getFemme().getPrenom());
        }


        //  Nombre d’enfants d’une femme

        int nb = fs.getNombreEnfantsEntreDates(
                f1.getId(),
                sdf.parse("01/01/1980"),
                sdf.parse("01/01/2025")
        );

        System.out.println("\nNombre d'enfants de "
                + f1.getNom() + " : " + nb);


        //  Femmes mariées au moins 2 fois

        System.out.println("\nFemmes mariées au moins deux fois :");

        List<Femme> femmes2 = fs.getFemmesDeuxFoisOuPlus();
        for (Femme f : femmes2) {
            System.out.println(f.getNom() + " " + f.getPrenom());
        }


        //  Hommes mariés à 4 femmes

        System.out.println("\nHommes mariés à 4 femmes :");

        List<Homme> hommes4 = ms.hommesMariesQuatreFemmes(
                sdf.parse("01/01/1980"),
                sdf.parse("01/01/2025")
        );

        for (Homme h : hommes4) {
            System.out.println(h.getNom() + " " + h.getPrenom());
        }


        //  Détails des mariages d’un homme

        System.out.println("\nDétails des mariages de " + h1.getNom());

        List<Mariage> all = hs.getMariagesByHomme(h1.getId());

        System.out.println("Mariages en cours :");
        for (Mariage m : all) {
            if (m.getDateFin() == null) {
                System.out.println("Femme : "
                        + m.getFemme().getNom()
                        + " | Date début : " + sdf.format(m.getDateDebut())
                        + " | Nb enfants : " + m.getNombreEnfants());
            }
        }

        System.out.println("\nMariages échoués :");
        for (Mariage m : all) {
            if (m.getDateFin() != null) {
                System.out.println("Femme : "
                        + m.getFemme().getNom()
                        + " | Date début : " + sdf.format(m.getDateDebut())
                        + " | Date fin : " + sdf.format(m.getDateFin())
                        + " | Nb enfants : " + m.getNombreEnfants());
            }
        }
    }
}
