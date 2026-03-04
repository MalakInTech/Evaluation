package ma.projet.service;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class AbstractFacade<T> implements IDao<T> {

    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public boolean create(T o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            session.save(o); // save pour inserer
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();    // annuler si erreur
            e.printStackTrace();
            return false;
        } finally {
            session.close();                  // fermer session
        }
    }

    @Override
    public boolean delete(T o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();    // annuler si erreur
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }


    }

    @Override
    public boolean update(T o){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();    // annuler si erreur
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public T findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        T obj = null;
        try {
            tx = session.beginTransaction();
            obj = (T) session.get(entityClass, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return obj;

    }

    @Override
    public List<T> findAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<T> list = null;

        try{
            tx = session.beginTransaction();
            list = session.createQuery("from " + entityClass.getSimpleName()).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return list;


    }

}