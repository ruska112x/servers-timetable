package org.karabalin.timetablebackend.core.models.requests;

import jakarta.validation.constraints.Size;

import java.util.Objects;

public class AddSubject {
    @Size(min = 3, max = 32, message = "Length of group name must be from 3 to 32 chars")
    private final String name;

    public AddSubject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AddSubject{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddSubject that = (AddSubject) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
