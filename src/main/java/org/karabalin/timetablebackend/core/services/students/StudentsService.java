package org.karabalin.timetablebackend.core.services.students;

import java.util.List;
import java.util.Optional;

import org.karabalin.timetablebackend.core.models.Student;
import org.karabalin.timetablebackend.core.repositories.students.interfaces.IStudentsRepository;
import org.karabalin.timetablebackend.core.services.students.interfaces.IStudentsService;
import org.springframework.stereotype.Service;

@Service
public class StudentsService implements IStudentsService {

    private final IStudentsRepository studentsRepository;

    public StudentsService(IStudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public long addStudent(Student student) {
        return studentsRepository.addStudent(student);
    }

    @Override
    public void deleteStudentById(long id) {
        studentsRepository.deleteStudentById(id);
    }

    @Override
    public void editStudent(Student student) {
        studentsRepository.editStudent(student);
    }

    @Override
    public Optional<Student> getStudentById(long id) {
        return Optional.of(studentsRepository.getStudentById(id));
    }

    @Override
    public List<Student> getStudentsByGroupId(long id) {
        return studentsRepository.getStudentsByGroupId(id);
    }

}
