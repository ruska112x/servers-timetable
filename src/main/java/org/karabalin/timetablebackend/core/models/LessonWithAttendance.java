package org.karabalin.timetablebackend.core.models;

import java.util.List;
import java.util.Objects;

public class LessonWithAttendance {
    private Lesson lesson;
    private List<Student> students;

    public LessonWithAttendance(Lesson lesson, List<Student> students) {
        this.lesson = lesson;
        this.students = students;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonWithAttendance that = (LessonWithAttendance) o;
        return Objects.equals(lesson, that.lesson) && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lesson, students);
    }

    @Override
    public String toString() {
        return "LessonWithAttendance{" +
                "lesson=" + lesson +
                ", students=" + students +
                '}';
    }
}
