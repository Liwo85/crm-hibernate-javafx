package pl.sda.crm.service;


import java.util.Objects;

public class AddressForm {

    private final String street;
    private final String zipCode;
    private final String city;
    private final String country;


    public AddressForm(String street, String city, String zipCode, String country) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressForm that = (AddressForm) o;
        return street.equals(that.street) && zipCode.equals(that.zipCode) && city.equals(that.city) && country.equals(that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, zipCode, city, country);
    }

    @Override
    public String toString() {
        return "AddressForm{" +
                "street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

