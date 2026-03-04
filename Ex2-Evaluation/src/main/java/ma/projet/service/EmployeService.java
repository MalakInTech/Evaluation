package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.EmployeTache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EmployeService extends AbstractFacade<Employe> {

    public EmployeService() {
        super(Employe.class);
    }

    //  Liste des tâches réalisées par un employé
    public List<EmployeTache> getTachesByEmploye(int idEmploye) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<EmployeTache> list = session.createQuery(
                        "FROM EmployeTache et WHERE et.employe.id = :id",
                        EmployeTache.class)
                .setParameter("id", idEmploye)
                .list();

        session.close();
        return list;
    }

    //  Liste des projets gérés par un employé
    public List<Projet> getProjetsByEmploye(int idEmploye) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Projet> list = session.createQuery(
                        "FROM Projet p WHERE p.manager.id = :id",
                        Projet.class)
                .setParameter("id", idEmploye)
                .list();

        session.close();
        return list;
    }
}
