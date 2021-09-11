package pl.sda.crm.entity;

import com.neovisionaries.i18n.CountryCode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.crm.util.HibernateUtil;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ContactListTest {
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
    void shouldAddContactToContactList(){
        //given
        final var emailContactItem = new EmailContactItem("w.liboska@wp.pl");
        final var phoneContactItem = new PhoneContactItem("500001001");
        ContactList list = new ContactList();
        list.addContact(emailContactItem);
        list.addContact(phoneContactItem);
        //when

        saveAndFlush(list);

        //then
        final var readContactList = session.get(ContactList.class, list.getId());
        assertTrue(list.getContactLists().containsAll(readContactList.getContactLists()));
    }
    private void saveAndFlush(ContactList list) {
        session.save(list);
        session.flush();
//        tx.commit();
        session.clear();
    }
}