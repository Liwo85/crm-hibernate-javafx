package pl.sda.crm.controller;

import com.neovisionaries.i18n.CountryCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.hibernate.SessionFactory;
import pl.sda.crm.entity.Address;
import pl.sda.crm.entity.Customer;
import pl.sda.crm.service.*;
import pl.sda.crm.util.HibernateUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CompanyListController implements Initializable {
    @FXML
    private TextField companyName;

    @FXML
    private TextField nipNumber;

    @FXML
    private Text customerId;

    @FXML
    private Text errorMessageCompany;

    @FXML
    private TextField street;

    @FXML
    private TextField zipCode;

    @FXML
    private TextField city;

    @FXML
    private ComboBox<CountryCode> country;
    ObservableList<CountryCode> list = FXCollections.observableArrayList(CountryCode.values());


    private final SessionFactory sessionFactory;
    private final SceneLoader sceneLoader;
    private final CompanyCustomerRegistration registration;
    private final AddressRegistration addressRegistration;

    public CompanyListController() {

        this.sceneLoader = new SceneLoader();
        this.registration = new CompanyCustomerRegistration((HibernateUtil.getSessionFactory()));
        this.addressRegistration = new AddressRegistration((HibernateUtil.getSessionFactory()));
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @FXML
    public void onCompanySave(ActionEvent event) {
        customerId.setText("");

        final var companyNameText = companyName.getText();
        final var nipText = nipNumber.getText();
        final var streetText = street.getText();
        final var countryText = country.getSelectionModel().getSelectedItem().toString();
        final var zipCodeText = zipCode.getText();
        final var cityText = city.getText();

        final var form = new RegistrationCompanyForm(companyNameText, nipText);
        final var formA = new AddressForm(streetText, cityText, zipCodeText,countryText);
        try {
            final var registeredCompanyId = registration.registerCompany(form);
            final var addressRegistrationId = addressRegistration.addressRegistration(formA);


            final var session = sessionFactory.openSession();
            final var tx = session.beginTransaction();
            Customer customer = session.get(Customer.class, registeredCompanyId.getId());
            Address address = session.get(Address.class, addressRegistrationId.getId());
            customer.addAddress(address);
            tx.commit();
            session.close();
            customerId.setText(registeredCompanyId.getId().toString());
            errorMessageCompany.setText("");


        } catch (Exception ex) {
            errorMessageCompany.setText(ex.getMessage());
        }


    }

    @FXML
    public void backToMenu(ActionEvent event) throws IOException {
        sceneLoader.loadOnEvent("home.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        country.setItems(list);

    }
}
