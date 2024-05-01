package org.karabalin.timetablebackend.core.repositories.teachers.interfaces;

import org.karabalin.timetablebackend.core.models.Teacher;
import org.karabalin.timetablebackend.core.models.requests.AddTeacher;

import java.util.List;

public interface ITeachersRepository {
    List<Teacher> getTeachers();

    Teacher getTeacherById(long id);

    long addTeacher(AddTeacher addTeacher);

    void editTeacher(Teacher teacher);

    void deleteTeacherById(long id);
}
