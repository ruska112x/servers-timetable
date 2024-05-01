package org.karabalin.timetablebackend.core.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

public class FullLesson {

    @Min(1)
    private long id;

    @Size(min = 8, max = 8, message = "Length of lesson date must 8")
    private String date;

    @Min(1)
    private long numberInSchedule;

    @Min(1)
    private long subjectId;

    @Min(1)
    private long teacherId;

    private List<Long> groupIdList;
    private List<Long> studentIdList;

    public FullLesson(long id, String date, long numberInSchedule, long subjectId, long teacherId, List<Long> groupIdList, List<Long> studentIdList) {
        this.id = id;
        this.date = date;
        this.numberInSchedule = numberInSchedule;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.groupIdList = groupIdList;
        this.studentIdList = studentIdList;
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

    public List<Long> getGroupIdList() {
        return groupIdList;
    }

    public void setGroupIdList(List<Long> groupIdList) {
        this.groupIdList = groupIdList;
    }

    public List<Long> getStudentIdList() {
        return studentIdList;
    }

    public void setStudentIdList(List<Long> studentIdList) {
        this.studentIdList = studentIdList;
    }

    @Override
    public String toString() {
        return "FullLesson{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", numberInSchedule=" + numberInSchedule +
                ", subjectId=" + subjectId +
                ", teacherId=" + teacherId +
                ", groupIdList=" + groupIdList +
                ", studentIdList=" + studentIdList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullLesson that = (FullLesson) o;
        return id == that.id && numberInSchedule == that.numberInSchedule && subjectId == that.subjectId && teacherId == that.teacherId && Objects.equals(date, that.date) && Objects.equals(groupIdList, that.groupIdList) && Objects.equals(studentIdList, that.studentIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, numberInSchedule, subjectId, teacherId, groupIdList, studentIdList);
    }
}
