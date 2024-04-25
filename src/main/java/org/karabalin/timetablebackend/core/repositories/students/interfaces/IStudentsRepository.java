package org.karabalin.timetablebackend.core.repositories.students.interfaces;

import java.util.List;

import org.karabalin.timetablebackend.core.models.Student;

public interface IStudentsRepository {
    List<Student> getStudentsByGroupId(long id);

    Student getStudentById(long id);

    long addStudent(Student student);

    void editStudent(Student student);

    void deleteStudentById(long id);
}
