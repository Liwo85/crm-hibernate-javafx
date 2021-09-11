package pl.sda.crm.entity;
import javax.persistence.Column;
import java.util.Objects;
import static pl.sda.crm.util.ArgumentValidator.validate;

public class Nip {
    @Column(name = "Nip")
    private String value;

    public Nip() {
    }

    public Nip(String nip) {
        validate(nip != null && nip.matches("\\d{10}"), "nip is invalid: " + nip);
        this.value = nip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nip nip = (Nip) o;
        return value.equals(nip.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
