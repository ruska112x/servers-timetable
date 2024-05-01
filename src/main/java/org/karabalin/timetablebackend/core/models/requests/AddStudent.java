package org.karabalin.timetablebackend.core.models.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class AddStudent {
    @Size(min = 3, max = 32, message = "Length of student surname must be from 3 to 32 chars")
    private final String surname;

    @Size(min = 3, max = 32, message = "Length of student name must be from 3 to 32 chars")
    private final String name;

    @Size(min = 3, max = 32, message = "Length of student patronymic must be from 3 to 32 chars")
    private final String patronymic;

    @Size(min = 3, max = 32, message = "Length of student status name must be from 3 to 32 chars")
    private final String status;

    @Min(1)
    private final long groupId;

    public AddStudent(String surname, String name, String patronymic, String status, long groupId) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.status = status;
        this.groupId = groupId;
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

    public String getStatus() {
        return status;
    }

    public long getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "AddStudent{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", status='" + status + '\'' +
                ", groupId=" + groupId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddStudent that = (AddStudent) o;
        return groupId == that.groupId && Objects.equals(surname, that.surname) && Objects.equals(name, that.name) && Objects.equals(patronymic, that.patronymic) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronymic, status, groupId);
    }
}
