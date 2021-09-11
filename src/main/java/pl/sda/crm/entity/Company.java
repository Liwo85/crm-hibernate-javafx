package pl.sda.crm.entity;

import pl.sda.crm.service.RegisterPersonForm;
import pl.sda.crm.service.RegistrationCompanyForm;
import pl.sda.crm.util.ArgumentValidator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import java.util.Objects;

import static java.util.Objects.*;
import static pl.sda.crm.util.ArgumentValidator.validate;

@Entity
@DiscriminatorValue("COMPANY")
public class Company extends Customer{

    private String companyName;
    @Embedded
    private Nip nipNumber;

    private Company() {
    }

    public Company(String companyName, Nip nipNumber) {
        validate(companyName != null && !companyName.isBlank(), "Company name is invalid: " + companyName);

        this.companyName = requireNonNull(companyName);
        this.nipNumber = requireNonNull(nipNumber,"nip is null");
    }

    public static Company from(RegistrationCompanyForm form) {
        return new Company(form.getCompanyName(), new Nip(form.getNipNumber()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return companyName.equals(company.companyName) && nipNumber.equals(company.nipNumber);
    }

    @Override
    public int hashCode() {
        return hash(super.hashCode(), companyName, nipNumber);
    }

    public Nip getNipNumber() {
        return nipNumber;
    }
}

