package org.karabalin.timetablebackend.core.repositories.students.interfaces;

import org.karabalin.timetablebackend.core.models.Student;

import java.util.List;

public interface IStudentsRepository {
    List<Student> getStudentsByGroupId(long id);

    List<Student> getStudentsByLessonId(long id);

    Student getStudentById(long id);

    long addStudent(Student student);

    void editStudent(Student student);

    void deleteStudentById(long id);
}
