package org.karabalin.timetablebackend.core.models.requests;

import jakarta.validation.constraints.Size;

import java.util.Objects;

public class AddTeacher {

    @Size(min = 3, max = 32, message = "Length of teacher surname must be from 3 to 32 chars")
    private final String surname;

    @Size(min = 3, max = 32, message = "Length of teacher name must be from 3 to 32 chars")
    private final String name;

    @Size(min = 3, max = 32, message = "Length of teacher patronymic must be from 3 to 32 chars")
    private final String patronymic;

    @Size(min = 3, max = 32, message = "Length of teacher position name must be from 3 to 32 chars")
    private final String position;

    public AddTeacher(String surname, String name, String patronymic, String position) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.position = position;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "AddTeacher{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddTeacher that = (AddTeacher) o;
        return Objects.equals(surname, that.surname) && Objects.equals(name, that.name) && Objects.equals(patronymic, that.patronymic) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronymic, position);
    }
}
