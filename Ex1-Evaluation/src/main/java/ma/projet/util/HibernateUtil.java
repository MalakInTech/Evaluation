package ma.projet.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.HibernateException;

public class HibernateUtil {

    // SessionFactory unique pour tout le projet
    private static SessionFactory sessionFactory;

    static {
        try {
            // Création de la SessionFactory à partir du fichier hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            System.err.println("Erreur de création de SessionFactory : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Méthode pour récupérer la SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Méthode pour ouvrir une session
    public static Session openSession() {
        return sessionFactory.openSession();
    }

    // Méthode pour fermer la SessionFactory
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}