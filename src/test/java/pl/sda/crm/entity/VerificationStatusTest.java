package pl.sda.crm.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.crm.util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

class VerificationStatusTest {
    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    private Session session;
    private Transaction tx;

    @BeforeEach
    void before() {
        session = factory.openSession();
        tx = session.beginTransaction();
    }

    @AfterEach
    void after() {
        tx.rollback();
//        tx.commit();
        session.close();
    }

    @Test
    void shouldSavePremiumStatus() {
        //given
        final var customer = new Person("Jan", "Kowala", new Pesel("12345678913"));
        final var verification = new VerificationStatus("Kamil Nowak");
        customer.statusChecker(verification);
        //when
        saveAndFlush(customer);
        //then
        final var customerRead = session.get(Customer.class, customer.getId());
        assertEquals(verification, customerRead.getVerificationStatus());
    }
    private void saveAndFlush(Customer customer) {
        session.save(customer);
        session.flush();
//        tx.commit();
        session.clear();
    }

}