package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class TacheService extends AbstractFacade<Tache> {

    public TacheService() {
        super(Tache.class);
    }

    //  Tâches avec prix > 1000 DH
    public List<Tache> findExpensiveTaches() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Tache> list = session.createNamedQuery("Tache.findExpensive", Tache.class).list();
        session.close();
        return list;
    }

    //  Tâches réalisées entre deux dates
    public List<EmployeTache> findTachesBetweenDates(Date d1, Date d2) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<EmployeTache> list = session.createQuery(
                        "FROM EmployeTache et " +
                                "WHERE et.dateDebutReelle BETWEEN :d1 AND :d2",
                        EmployeTache.class).setParameter("d1", d1).setParameter("d2", d2).list();
        session.close();
        return list;
    }
}
