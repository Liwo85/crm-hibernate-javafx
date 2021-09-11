package pl.sda.crm.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.sda.crm.entity.Person;
import pl.sda.crm.exception.CustomerAlreadyExistsException;
import static java.util.Objects.requireNonNull;


public class PersonCustomerRegistration {

    private final SessionFactory sessionFactory;

    public PersonCustomerRegistration(SessionFactory sessionFactory) {
        this.sessionFactory = requireNonNull(sessionFactory);
    }

    public RegisteredCustomerId registerPerson(RegisterPersonForm form) {
        // 0. otowrzenie transackji
        final var session = sessionFactory.openSession();
        final var tx = session.beginTransaction();

        // 1. walidacja last name + pesel
        // HQL
        if(personExists(form, session)){
            throw new CustomerAlreadyExistsException("customer exists" + form);
        }

        // 2. zamiana form w encji Person
        final var person = Person.from(form);

        // 3. zapisanie Person do DB
        session.save(person);

        // 4. commit & zwrócenie id
        tx.commit();
        session.close();
        return new RegisteredCustomerId(person.getId());
    }

    private Boolean personExists(RegisterPersonForm form, Session session) {
        return session.createQuery("select count(p) > 0 from Person p where p.lastName =?1 " +
                        "and p.pesel.value = ?2", Boolean.class)
                .setParameter(1, form.getLastName()).setParameter(2, form.getPesel()).getSingleResult();
    }
// count nam mówi o tym czy ktos jest czy nie juz w bazie przy rejestracji jesli da 1 czyli jest czyli trzeba przerwac jesli 0 mozna rejestorwac bo nikt taki nie istnieje

}
