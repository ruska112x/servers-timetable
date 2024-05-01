package org.karabalin.timetablebackend.core.services.subjects;

import org.karabalin.timetablebackend.core.models.Subject;
import org.karabalin.timetablebackend.core.models.requests.AddSubject;
import org.karabalin.timetablebackend.core.repositories.subjects.interfaces.ISubjectRepository;
import org.karabalin.timetablebackend.core.services.subjects.interfaces.ISubjectsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectsService implements ISubjectsService {

    private final ISubjectRepository subjectRepository;

    public SubjectsService(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getSubjects() {
        return subjectRepository.getSubjects();
    }

    @Override
    public Optional<Subject> getSubjectById(long id) {
        return Optional.of(subjectRepository.getSubjectById(id));
    }

    @Override
    public long addSubject(AddSubject addSubject) {
        return subjectRepository.addSubject(addSubject);
    }

    @Override
    public void editSubject(Subject subject) {
        subjectRepository.editSubject(subject);
    }

    @Override
    public void deleteSubjectById(long id) {
        subjectRepository.deleteSubjectById(id);
    }
}
