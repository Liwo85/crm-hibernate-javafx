package pl.sda.crm.entity;
import com.neovisionaries.i18n.CountryCode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.crm.util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    private Session session;
    private Transaction tx;

    @BeforeEach
    void before(){
        session = factory.openSession();
        tx= session.beginTransaction();
    }

    @AfterEach
    void after(){
        tx.commit(); // rollback wycofuje poprzednie rzeczy z bazy danych po to by te rzeczy nie wpływały na kolejne testy
        session.close();
    }

    @Test
    void shouldSavePersonInDatabase() {
        //given
        final var person = new Person("Dawid", "Kutaszewicz", new Pesel("12345678912"));

        //when
        saveAndFlush(person);

        //then
        //pobiera person po jego id
        final var readPerson = session.get(Person.class, person.getId());
        assertEquals(person, readPerson);
    }

    @Test
    void shouldAddAddress(){
        //given
        final var customer = new Person("Jan", "Kowalski", new Pesel("12345678913"));
        final var address =  new Address("Szeroka", "Kraków", "11-300", CountryCode.PL);
        customer.addAddress(address);

        //when
//        session.save(address);  //uciekamy od erroru w teście
        saveAndFlush(customer);

        //then
        final var readCustomer = session.get(Customer.class, customer.getId());
        assertEquals(customer.getAddresses(), readCustomer.getAddresses());
    }

    private void saveAndFlush(Person person) {
        session.save(person); //dodanie encji do cache -> Map<ID, ENTITY> = person.id, person
        // w kodzie producyjnym dwóch następnych wierszy nie używamy to jest tylko do testów
        session.flush(); // zapisz encje w bazie danych
        session.clear(); // czyści cache
    }
}