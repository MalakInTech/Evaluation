package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

public class MariageService extends AbstractFacade<Mariage> {

    public MariageService() {
        super(Mariage.class);
    }

    // Criteria API : hommes mariés à 4 femmes entre deux dates
    public List<Homme> hommesMariesQuatreFemmes(Date d1, Date d2) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Homme> cq = cb.createQuery(Homme.class);
        Root<Mariage> root = cq.from(Mariage.class);

        cq.select(root.get("homme"))
                .where(cb.between(root.get("dateDebut"), d1, d2))
                .groupBy(root.get("homme"))
                .having(cb.equal(cb.count(root), 4));

        List<Homme> result = session.createQuery(cq).getResultList();

        session.close();
        return result;
    }
}
