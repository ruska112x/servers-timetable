package org.karabalin.timetablebackend.core.services.teachers.interfaces;

import org.karabalin.timetablebackend.core.models.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeachersService {
    List<Teacher> getTeachers();

    Optional<Teacher> getTeacherById(long id);

    long addTeacher(Teacher teacher);

    void editTeacher(Teacher teacher);

    void deleteTeacherById(long id);
}
