package org.karabalin.timetablebackend.core.services.teachers.interfaces;

import org.karabalin.timetablebackend.core.models.Teacher;
import org.karabalin.timetablebackend.core.models.requests.AddTeacher;

import java.util.List;
import java.util.Optional;

public interface ITeachersService {
    List<Teacher> getTeachers();

    Optional<Teacher> getTeacherById(long id);

    long addTeacher(AddTeacher addTeacher);

    void editTeacher(Teacher teacher);

    void deleteTeacherById(long id);
}
