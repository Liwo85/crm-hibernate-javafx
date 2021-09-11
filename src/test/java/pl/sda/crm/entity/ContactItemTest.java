package pl.sda.crm.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.crm.util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

class ContactItemTest {

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
        tx.commit();
        session.close();
    }

    @Test
    void shouldSavePhoneContactDatabase() {
        //given
        final var phoneContactItem = new PhoneContactItem("500001001");

        //when
        saveAndFlush(phoneContactItem);

        //then
        final var readPhone = session.get(ContactItem.class, phoneContactItem.getId());
        assertEquals(phoneContactItem, readPhone);
    }
    @Test
    void shouldSaveEmailContactDatabase() {
        //given
        final var emailContactItem = new EmailContactItem("w.liboska@wp.pl");

        //when
        saveAndFlush(emailContactItem);

        //then
        final var readPhone = session.get(ContactItem.class, emailContactItem.getId());
        assertEquals(emailContactItem, readPhone);
    }

    private void saveAndFlush(ContactItem phoneContactItem) {
        session.save(phoneContactItem);
        session.flush();
//        tx.commit();
        session.clear();
    }
}