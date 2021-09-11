package pl.sda.crm.entity;

import com.neovisionaries.i18n.CountryCode;
import pl.sda.crm.service.AddressForm;
import pl.sda.crm.service.RegistrationCompanyForm;

import javax.persistence.*;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static pl.sda.crm.util.ArgumentValidator.*;

@Entity
@Table(name = "adresses")
public class Address {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String street;
    private String city;
    private String zipCode;
    @Enumerated(EnumType.STRING)
    private CountryCode countryCode;


    public Address() {
    }

    public Address(String street, String city, String zipCode, CountryCode countryCode) {
        validate(street != null && !street.isBlank(), "invalid street");
        validate(city != null && !city.isBlank(), "invalid street");
        validate(zipCode != null && zipCode.matches("\\d{2}-\\d{3}"), "invalid zip code");
        this.id = UUID.randomUUID();
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.countryCode = requireNonNull(countryCode);
    }
    public static Address from(AddressForm form) {
        return new Address(form.getStreet(), form.getCity(),form.getZipCode(), CountryCode.valueOf(form.getCountry()));
    }


    public UUID getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id.equals(address.id) && street.equals(address.street) && city.equals(address.city) && zipCode.equals(address.zipCode) && countryCode == address.countryCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, zipCode, countryCode);
    }
}