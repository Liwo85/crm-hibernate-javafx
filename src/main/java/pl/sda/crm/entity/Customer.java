package pl.sda.crm.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   //dziedziczenie (standardowa strategia dziedziczenia)
@DiscriminatorColumn(name = "customer_type")            // ustala nazwę kolumny określającej typ obiektu
public abstract class Customer {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL)   //aktualizuje wszystko
    @JoinColumn(name = "customer_id")
    private List<Address> addresses;


    @OneToOne(cascade = CascadeType.ALL)
    private PremiumStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "verification_id")
    private VerificationStatus verificationStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_list_id")
    private ContactList contactList;

    public Customer() {
        this.id = UUID.randomUUID();
        this.addresses = new ArrayList<>();
        this.contactList = new ContactList();
    }

    public UUID getId() {
        return id;
    }

    public void addAddress(Address address) {
        if (!this.addresses.contains(address)) {
            this.addresses.add(address);
        }
    }

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public PremiumStatus getStatus() {
        return status;
    }

    public List<Address> getAddresses() {
        return new ArrayList<>(addresses); // kopia listy adresów
    }

    public ContactList getList() {
        return contactList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void markPremium(PremiumStatus status) {
        requireNonNull(status);
        this.status = status;
    }

    public void statusChecker(VerificationStatus verificationStatus) {
        requireNonNull(verificationStatus);
        this.verificationStatus = verificationStatus;
    }
}
