package pl.sda.crm.service;


import java.util.Objects;


public class RegistrationCompanyForm {
    private final String companyName;

    private final String nipNumber;

    public RegistrationCompanyForm(String companyName, String nipNumber) {
        this.companyName = companyName;
        this.nipNumber = nipNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getNipNumber() {
        return nipNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationCompanyForm that = (RegistrationCompanyForm) o;
        return companyName.equals(that.companyName) && nipNumber.equals(that.nipNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, nipNumber);
    }

    @Override
    public String toString() {
        return "RegistrationCompanyForm{" +
                "companyName='" + companyName + '\'' +
                ", nipNumber=" + nipNumber +
                '}';
    }
}
