package pl.sda.crm.service;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class AddressRegistrationId implements Serializable {
    private final UUID id;

    public AddressRegistrationId(UUID id) {
        this.id = requireNonNull(id);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressRegistrationId that = (AddressRegistrationId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
