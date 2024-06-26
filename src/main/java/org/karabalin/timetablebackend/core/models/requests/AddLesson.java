package org.karabalin.timetablebackend.core.models.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class AddLesson {
    @Size(min = 8, max = 8, message = "Length of lesson date must 8")
    private final String date;

    @Min(1)
    private final long numberInSchedule;

    @Min(1)
    private final long subjectId;

    @Min(1)
    private final long teacherId;

    public AddLesson(String date, long numberInSchedule, long subjectId, long teacherId) {
        this.date = date;
        this.numberInSchedule = numberInSchedule;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
    }

    public String getDate() {
        return date;
    }

    public long getNumberInSchedule() {
        return numberInSchedule;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    @Override
    public String toString() {
        return "AddLesson{" +
                "date='" + date + '\'' +
                ", numberInSchedule=" + numberInSchedule +
                ", subjectId=" + subjectId +
                ", teacherId=" + teacherId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddLesson addLesson = (AddLesson) o;
        return numberInSchedule == addLesson.numberInSchedule && subjectId == addLesson.subjectId && teacherId == addLesson.teacherId && Objects.equals(date, addLesson.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, numberInSchedule, subjectId, teacherId);
    }
}
