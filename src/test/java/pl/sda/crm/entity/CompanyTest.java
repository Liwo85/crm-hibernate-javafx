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

class CompanyTest {

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
        session.close();
    }

    @Test
    void shouldSaveCompanyInDatabase() {
        //given
        final var company = new Company("BlBlaBla", new Nip("1234567891"));

        //when
        saveAndFlush(company);

        //then
        final var readCompany = session.get(Company.class, company.getId());
        assertEquals(company, readCompany);
    }

    @Test
    void shouldAddCompanyddress(){
        //given
        final var customer = new Company("BlBlaBla", new Nip("1234567891"));
        final var address =  new Address("Szeroka", "Kraków", "11-300", CountryCode.PL);
        customer.addAddress(address);

        //when
//        session.save(customer);  //uciekamy od erroru w teście
        session.save(address);
        saveAndFlush(customer);

        //then
        final var readCustomer = session.get(Customer.class, customer.getId());
        assertEquals(customer.getAddresses(), readCustomer.getAddresses());
    }
    private void saveAndFlush(Company company) {
        session.save(company);
        session.flush();
//        tx.commit();
        session.clear();
    }
}