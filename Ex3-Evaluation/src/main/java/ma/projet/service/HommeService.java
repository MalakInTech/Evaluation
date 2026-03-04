package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class HommeService extends AbstractFacade<Homme> {

    public HommeService() {
        super(Homme.class);
    }

    // Épouses d’un homme entre deux dates
    public List<Mariage> getEpousesEntreDeuxDates(int idHomme, Date d1, Date d2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Mariage> list = session.createQuery(
                        "FROM Mariage m WHERE m.homme.id = :id AND m.dateDebut BETWEEN :d1 AND :d2",
                        Mariage.class)
                .setParameter("id", idHomme)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .list();
        session.close();
        return list;
    }

    // Mariages en cours
    public List<Mariage> getMariagesEnCours(int idHomme) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Mariage> list = session.createQuery(
                        "FROM Mariage m WHERE m.homme.id = :id AND m.dateFin IS NULL",
                        Mariage.class)
                .setParameter("id", idHomme)
                .list();
        session.close();
        return list;
    }

    // Tous les mariages avec détails
    public List<Mariage> getMariagesByHomme(int idHomme) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Mariage> list = session.createQuery(
                        "FROM Mariage m WHERE m.homme.id = :id",
                        Mariage.class)
                .setParameter("id", idHomme)
                .list();
        session.close();
        return list;
    }
}