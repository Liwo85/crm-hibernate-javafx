package pl.sda.crm.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("PHONE")
public class PhoneContactItem extends ContactItem{
    @Embedded
    private String phone;

    public PhoneContactItem() {
    }

    public PhoneContactItem(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PhoneContactItem that = (PhoneContactItem) o;
        return phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phone);
    }


//    public String getPhone() {
//        return phone;
//    }
}
