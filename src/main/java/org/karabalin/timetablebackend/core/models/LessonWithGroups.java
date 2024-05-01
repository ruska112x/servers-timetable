package org.karabalin.timetablebackend.core.models;

import java.util.List;
import java.util.Objects;

public class LessonWithGroups {
    private Lesson lesson;
    private List<Group> groups;

    public LessonWithGroups(Lesson lesson, List<Group> groups) {
        this.lesson = lesson;
        this.groups = groups;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "LessonWithGroups{" +
                "lesson=" + lesson +
                ", groups=" + groups +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonWithGroups that = (LessonWithGroups) o;
        return Objects.equals(lesson, that.lesson) && Objects.equals(groups, that.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lesson, groups);
    }
}
