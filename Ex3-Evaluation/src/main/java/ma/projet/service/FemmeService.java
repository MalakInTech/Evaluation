package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.Date;
import java.util.List;

public class FemmeService extends AbstractFacade<Femme> {

    public FemmeService() {
        super(Femme.class);
    }

    // Requête native : nombre d’enfants entre deux dates
    public Integer getNombreEnfantsEntreDates(int idFemme, Date d1, Date d2) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        NativeQuery<Integer> query = session.createNativeQuery(
                "SELECT SUM(nombreEnfants) FROM mariage " +
                        "WHERE femme_id = :idFemme AND dateDebut BETWEEN :d1 AND :d2"
        );

        query.setParameter("idFemme", idFemme);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);

        Integer result = (Integer) query.getSingleResult();

        session.close();
        return result != null ? result : 0;
    }

    // Femmes mariées au moins 2 fois
    public List<Femme> getFemmesDeuxFoisOuPlus() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Femme> list = session.createQuery(
                "SELECT m.femme FROM Mariage m GROUP BY m.femme HAVING COUNT(m) >= 2",
                Femme.class
        ).list();

        session.close();
        return list;
    }
}
