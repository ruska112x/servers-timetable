package org.karabalin.timetablebackend.core.models;

import java.util.List;
import java.util.Objects;

public class FullLesson {
    private Lesson lesson;
    private List<Long> groupIdList;
    private List<Long> studentIdList;

    public FullLesson(Lesson lesson, List<Long> groupIdList, List<Long> studentIdList) {
        this.lesson = lesson;
        this.groupIdList = groupIdList;
        this.studentIdList = studentIdList;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullLesson that = (FullLesson) o;
        return Objects.equals(lesson, that.lesson) && Objects.equals(groupIdList, that.groupIdList) && Objects.equals(studentIdList, that.studentIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lesson, groupIdList, studentIdList);
    }

    @Override
    public String toString() {
        return "FullLesson{" +
                "lesson=" + lesson +
                ", groupIdList=" + groupIdList +
                ", studentIdList=" + studentIdList +
                '}';
    }
}
