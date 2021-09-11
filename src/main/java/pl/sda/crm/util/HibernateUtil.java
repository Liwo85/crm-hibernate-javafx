package pl.sda.crm.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.crm.entity.*;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            final var configuration = new Configuration();
            configuration.setProperties(loadHibernateProperties());
            configureEntities(configuration);
            sessionFactory = configuration.buildSessionFactory();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }

    private HibernateUtil() {}

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static void configureEntities(Configuration configuration) {
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(PremiumStatus.class);
        configuration.addAnnotatedClass(VerificationStatus.class);
        configuration.addAnnotatedClass(EmailContactItem.class);
        configuration.addAnnotatedClass(PhoneContactItem.class);
        configuration.addAnnotatedClass(ContactItem.class);
        configuration.addAnnotatedClass(ContactList.class);
    }

    private static Properties loadHibernateProperties() throws IOException {
        final var properties = new Properties();
        properties.load(HibernateUtil.class.getClassLoader()
                .getResourceAsStream("hibernate.properties"));
        return properties;
    }
}