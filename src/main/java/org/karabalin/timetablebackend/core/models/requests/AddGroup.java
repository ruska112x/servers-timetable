package org.karabalin.timetablebackend.core.models.requests;

import jakarta.validation.constraints.Size;

import java.util.Objects;

public class AddGroup {
    @Size(min = 3, max = 16, message = "Length of group name must be from 3 to 16 chars")
    private final String name;

    public AddGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AddGroup{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddGroup addGroup = (AddGroup) o;
        return Objects.equals(name, addGroup.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
