package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {

        CategorieService cs = new CategorieService();
        ProduitService ps = new ProduitService();
        CommandeService cos = new CommandeService();
        LigneCommandeService lcs = new LigneCommandeService();


        //  Création des catégories


        Categorie c1 = new Categorie("INF", "Informatique");
        Categorie c2 = new Categorie("TEL", "Téléphonie");

        cs.create(c1);
        cs.create(c2);


        //  Création des produits


        Produit p1 = new Produit("ES12", 120, c1);
        Produit p2 = new Produit("ZR85", 100, c1);
        Produit p3 = new Produit("EE85", 200, c2);

        ps.create(p1);
        ps.create(p2);
        ps.create(p3);


        // Création des commandes


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Commande cmd1 = new Commande(sdf.parse("14/03/2013"));
        Commande cmd2 = new Commande(sdf.parse("20/04/2014"));

        cos.create(cmd1);
        cos.create(cmd2);


        //  Création des lignes commande


        LigneCommande lc1 = new LigneCommande(7, p1, cmd1);
        LigneCommande lc2 = new LigneCommande(14, p2, cmd1);
        LigneCommande lc3 = new LigneCommande(5, p3, cmd1);

        lcs.create(lc1);
        lcs.create(lc2);
        lcs.create(lc3);


        //  Produits par catégorie


        System.out.println("Produits catégorie Informatique :");
        List<Produit> produitsCat = ps.findByCategorie(c1);
        for (Produit p : produitsCat) {
            System.out.println(p.getReference() + " - " + p.getPrix());
        }


        //  Produits entre deux dates


        System.out.println("\nProduits entre 01/01/2013 et 31/12/2013 :");
        Date d1 = sdf.parse("01/01/2013");
        Date d2 = sdf.parse("31/12/2013");

        List<Produit> produitsDates = ps.findBetweenDates(d1, d2);
        for (Produit p : produitsDates) {
            System.out.println(p.getReference());
        }


        //  Produits d'une commande


        System.out.println("\nCommande : " + cmd1.getId() + " Date : " + cmd1.getDate());
        System.out.println("Liste des produits :");
        System.out.println("Référence   Prix   Quantité");

        List<LigneCommande> lignes = ps.findProduitsByCommande(cmd1);
        for (LigneCommande lc : lignes) {
            System.out.println(
                    lc.getProduit().getReference() + "     " +
                            lc.getProduit().getPrix() + " DH     " +
                            lc.getQuantite()
            );
        }


        // Produits prix > 100


        System.out.println("\nProduits prix > 100 :");
        List<Produit> produitsCher = ps.prixSuperieur();
        for (Produit p : produitsCher) {
            System.out.println(p.getReference() + " - " + p.getPrix());
        }
    }
}