package pl.sda.crm.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "contact_list")

public class ContactList {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ContactItem> contactLists;

    public ContactList() {
        this.id = UUID.randomUUID();
        this.contactLists = new ArrayList<>();
    }
    public void addContact(ContactItem contactItem) {
        if (!this.contactLists.contains(contactItem)) {
            this.contactLists.add(contactItem);
        }
    }

    public UUID getId() {
        return id;
    }

    public List<ContactItem> getContactLists() {
        return  new ArrayList<>(contactLists);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactList that = (ContactList) o;
        return id.equals(that.id) && contactLists.equals(that.contactLists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactLists);
    }
}
