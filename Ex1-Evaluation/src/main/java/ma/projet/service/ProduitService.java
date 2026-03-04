package ma.projet.service;

import ma.projet.classes.*;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class ProduitService extends AbstractFacade<Produit> {

    public ProduitService() {
        super(Produit.class);
    }


    // MÉTHODES DEMANDÉES


    // 1️⃣ Produits par catégorie
    public List<Produit> findByCategorie(Categorie c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Produit> produits = session.createQuery("from Produit p where p.categorie = :cat").setParameter("cat", c).list();
        session.close();
        return produits;
    }

    // 2️⃣ Produits commandés entre deux dates
    public List<Produit> findBetweenDates(Date d1, Date d2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Produit> produits = session.createQuery("select distinct lc.produit from LigneCommande lc " + "where lc.commande.date between :d1 and :d2").setParameter("d1", d1).setParameter("d2", d2).list();
        session.close();
        return produits;
    }

    // 3️⃣ Produits d'une commande donnée
    public List<LigneCommande> findProduitsByCommande(Commande c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<LigneCommande> lignes = session.createQuery("from LigneCommande lc where lc.commande = :cmd").setParameter("cmd", c).list();
        session.close();
        return lignes;
    }

    // 4️⃣ Produits prix > 100 (NamedQuery)
    public List<Produit> prixSuperieur() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Produit> produits = session.createNamedQuery("Produit.prixSuperieur").list();
        session.close();
        return produits;
    }
}

