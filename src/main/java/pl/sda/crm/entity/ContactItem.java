package pl.sda.crm.entity;
import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ContactItem")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   //dziedziczenie (standardowa strategia dziedziczenia)
@DiscriminatorColumn(name = "contact_type")
public abstract class ContactItem {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    public ContactItem() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactItem that = (ContactItem) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
