package org.karabalin.timetablebackend.core.services.subjects.interfaces;

import org.karabalin.timetablebackend.core.models.Subject;

import java.util.List;
import java.util.Optional;

public interface ISubjectsService {
    List<Subject> getSubjects();

    Optional<Subject> getSubjectById(long id);

    long addSubject(Subject subject);

    void editSubject(Subject subject);

    void deleteSubjectById(long id);
}
