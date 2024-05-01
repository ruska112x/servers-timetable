package org.karabalin.timetablebackend.core.models;

import java.util.Objects;

public class Lesson {
    private long id;
    private String date;
    private long numberInSchedule;
    private long subjectId;
    private long teacherId;

    public Lesson(long id, String date, long numberInSchedule, long subjectId, long teacherId) {
        this.id = id;
        this.date = date;
        this.numberInSchedule = numberInSchedule;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getNumberInSchedule() {
        return numberInSchedule;
    }

    public void setNumberInSchedule(long numberInSchedule) {
        this.numberInSchedule = numberInSchedule;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", date=" + date +
                ", numberInSchedule=" + numberInSchedule +
                ", subjectId=" + subjectId +
                ", teacherId=" + teacherId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id == lesson.id && numberInSchedule == lesson.numberInSchedule && subjectId == lesson.subjectId && teacherId == lesson.teacherId && Objects.equals(date, lesson.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, numberInSchedule, subjectId, teacherId);
    }
}
