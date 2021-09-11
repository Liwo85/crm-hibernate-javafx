package pl.sda.crm.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("EMAIL")
public class EmailContactItem extends ContactItem {

    @Embedded
    private String email;

    public EmailContactItem() {
    }

    public EmailContactItem(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailContactItem that = (EmailContactItem) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }


}
