package org.karabalin.timetablebackend.core.services.teachers;

import org.karabalin.timetablebackend.core.models.Teacher;
import org.karabalin.timetablebackend.core.repositories.teachers.interfaces.ITeachersRepository;
import org.karabalin.timetablebackend.core.services.teachers.interfaces.ITeachersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeachersService implements ITeachersService {

    private final ITeachersRepository teachersRepository;

    public TeachersService(ITeachersRepository teachersRepository) {
        this.teachersRepository = teachersRepository;
    }

    @Override
    public List<Teacher> getTeachers() {
        return teachersRepository.getTeachers();
    }

    @Override
    public Optional<Teacher> getTeacherById(long id) {
        return Optional.of(teachersRepository.getTeacherById(id));
    }

    @Override
    public long addTeacher(Teacher teacher) {
        return teachersRepository.addTeacher(teacher);
    }

    @Override
    public void editTeacher(Teacher teacher) {
        teachersRepository.editTeacher(teacher);
    }

    @Override
    public void deleteTeacherById(long id) {
        teachersRepository.deleteTeacherById(id);
    }
}
