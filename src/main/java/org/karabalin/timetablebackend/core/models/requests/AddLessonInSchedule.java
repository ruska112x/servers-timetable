package org.karabalin.timetablebackend.core.models.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

public class AddLessonInSchedule {
    @Size(min = 8, max = 8, message = "Length of lesson date must 8")
    private final String date;

    @Min(1)
    private final long numberInSchedule;

    @Min(1)
    private final long subjectId;

    @Min(1)
    private final long teacherId;
    private final List<Long> groupIdList;
    private final List<Long> studentIdList;

    public AddLessonInSchedule(String date, long numberInSchedule, long subjectId, long teacherId, List<Long> groupIdList, List<Long> studentIdList) {
        this.date = date;
        this.numberInSchedule = numberInSchedule;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.groupIdList = groupIdList;
        this.studentIdList = studentIdList;
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

    public List<Long> getGroupIdList() {
        return groupIdList;
    }

    public List<Long> getStudentIdList() {
        return studentIdList;
    }

    @Override
    public String toString() {
        return "AddLesson{" +
                "date='" + date + '\'' +
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
        AddLessonInSchedule addLessonInSchedule = (AddLessonInSchedule) o;
        return numberInSchedule == addLessonInSchedule.numberInSchedule && subjectId == addLessonInSchedule.subjectId && teacherId == addLessonInSchedule.teacherId && Objects.equals(date, addLessonInSchedule.date) && Objects.equals(groupIdList, addLessonInSchedule.groupIdList) && Objects.equals(studentIdList, addLessonInSchedule.studentIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, numberInSchedule, subjectId, teacherId, groupIdList, studentIdList);
    }
}
