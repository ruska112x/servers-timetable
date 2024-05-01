package org.karabalin.timetablebackend.core.repositories.students.interfaces;

import org.karabalin.timetablebackend.core.models.Student;
import org.karabalin.timetablebackend.core.models.requests.AddStudent;

import java.util.List;

public interface IStudentsRepository {
    List<Student> getStudentsByGroupId(long id);

    List<Student> getStudentsByLessonId(long id);

    void addStudentsForLesson(List<Object[]> idsList);

    Student getStudentById(long id);

    long addStudent(AddStudent addStudent);

    void editStudent(Student student);

    void deleteStudentById(long id);
}
