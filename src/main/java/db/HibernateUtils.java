package db;

import model.CheckAreaBean;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtils {
    private static final SessionFactory factory;

    static {
        try {
            factory = new Configuration().configure()
                    .setProperty(AvailableSettings.USER,
                            "postgres")
                    .setProperty(AvailableSettings.PASS,
                            "1234")
                    .setProperty(AvailableSettings.DRIVER,
                            "org.postgresql.Driver")
                    .setProperty(AvailableSettings.URL,
                            "jdbc:postgresql://localhost:5432/labWeb3")
                    .addAnnotatedClass(CheckAreaBean.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Something went wrong during initializing Hibernate: " + ex);
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}
