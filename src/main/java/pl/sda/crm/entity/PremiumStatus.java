package pl.sda.crm.entity;

import pl.sda.crm.util.ArgumentValidator;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "premium_status")
public class PremiumStatus {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private boolean isActive = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "premium_status")
    private Status status;

    private LocalDate expireDate;

    public PremiumStatus() {
    }

    public PremiumStatus(Status status) {
        this.id=UUID.randomUUID();
        this.expireDate = LocalDate.now().plusDays(status.getPremiumTime());
        isPremium_status_is_not_activated();
        this.isActive = (expireDate.compareTo(LocalDate.now()) > 0);
        this.status = status;
    }

    private void isPremium_status_is_not_activated() {
        ArgumentValidator.validate(getExpireDate().compareTo(LocalDate.now()) > 0, "premium status is not activated");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PremiumStatus that = (PremiumStatus) o;
        return isActive == that.isActive && id.equals(that.id) && expireDate.equals(that.expireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, expireDate);
    }

    public UUID getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public Status getStatus() {
        return status;
    }
}

