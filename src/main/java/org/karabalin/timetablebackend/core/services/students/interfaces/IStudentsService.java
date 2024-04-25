package org.karabalin.timetablebackend.core.services.students.interfaces;

import java.util.List;
import java.util.Optional;

import org.karabalin.timetablebackend.core.models.Student;

public interface IStudentsService {
    List<Student> getStudentsByGroupId(long id);

    Optional<Student> getStudentById(long id);

    long addStudent(Student student);

    void editStudent(Student student);

    void deleteStudentById(long id);
}
