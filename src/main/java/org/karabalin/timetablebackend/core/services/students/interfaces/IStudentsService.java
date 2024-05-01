package org.karabalin.timetablebackend.core.services.students.interfaces;

import org.karabalin.timetablebackend.core.models.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentsService {
    List<Student> getStudentsByGroupId(long id);

    Optional<Student> getStudentById(long id);

    long addStudent(Student student);

    void editStudent(Student student);

    void deleteStudentById(long id);
}
