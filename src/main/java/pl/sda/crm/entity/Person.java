package pl.sda.crm.entity;

import pl.sda.crm.service.RegisterPersonForm;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import static java.util.Objects.*;
import static pl.sda.crm.util.ArgumentValidator.validate;

@Entity
@DiscriminatorValue("PERSON")// ustala wartość w kolumnie customer_type
public class Person extends Customer{

    private String firstName;
    private String lastName;
    @Embedded               //Pola z klasy pesel będą zapisane wraz z polami person w jednej tabeli
    private Pesel pesel;

    public Person() { // only for hibernate - don't use
    }

    public Person(String firstName, String lastName, Pesel pesel) {
        validate(firstName != null && !firstName.isBlank(), "first name is invalid: " + firstName);
        validate(lastName != null && !lastName.isBlank(), "last name is invalid: " + lastName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = requireNonNull(pesel, "pesel is null");
    }

    public static Person from(RegisterPersonForm form) {
        return new Person(form.getFirstName(), form.getLastName(),new Pesel(form.getPesel()));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Pesel getPesel() {
        return pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) && lastName.equals(person.lastName) && pesel.equals(person.pesel);
    }

    @Override
    public int hashCode() {
        return hash(super.hashCode(), firstName, lastName, pesel);
    }
}
