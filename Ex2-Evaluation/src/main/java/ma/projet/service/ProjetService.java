package ma.projet.service;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProjetService extends AbstractFacade<Projet> {

    public ProjetService() {
        super(Projet.class);
    }

    //  Liste des tâches planifiées pour un projet
    public List<Tache> getTachesByProjet(int idProjet) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Tache> list = session.createQuery(
                        "FROM Tache t WHERE t.projet.id = :id",
                        Tache.class).setParameter("id", idProjet).list();
        session.close();
        return list;
    }

    //  Liste des tâches réalisées avec dates réelles
    public List<EmployeTache> getTachesRealisees(int idProjet) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<EmployeTache> list = session.createQuery(
                        "SELECT et FROM EmployeTache et " +
                                "WHERE et.tache.projet.id = :id " +
                                "AND et.dateFinReelle IS NOT NULL",
                        EmployeTache.class).setParameter("id", idProjet).list();
        session.close();
        return list;
    }
}