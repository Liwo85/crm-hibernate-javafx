package pl.sda.crm.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.*;
import static pl.sda.crm.util.ArgumentValidator.validate;

@Entity
@Table(name = "verification_status")
public class VerificationStatus {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(columnDefinition = "BOOLEAN")
    private boolean isVerified = false;
    private String byWhoVerified;
    private LocalDate whenVerified;

    public VerificationStatus() {
    }

    public VerificationStatus(String byWhoVerified ) {
        this.id = UUID.randomUUID();
        this.byWhoVerified = requireNonNull(byWhoVerified);
        this.whenVerified = LocalDate.now();
        validate(byWhoVerified != null && byWhoVerified.length()>10, " is invalid: " + byWhoVerified);
        this.isVerified = (byWhoVerified != null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationStatus that = (VerificationStatus) o;
        return isVerified == that.isVerified && id.equals(that.id) && byWhoVerified.equals(that.byWhoVerified) && whenVerified.equals(that.whenVerified);
    }

    @Override
    public int hashCode() {
        return hash(id, isVerified, byWhoVerified, whenVerified);
    }

    public UUID getId() {
        return id;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public String getByWhoVerified() {
        return byWhoVerified;
    }

    public LocalDate getWhenVerified() {
        return whenVerified;
    }
}
