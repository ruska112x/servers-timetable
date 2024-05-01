package org.karabalin.timetablebackend.core.services.subjects.interfaces;

import org.karabalin.timetablebackend.core.models.Subject;
import org.karabalin.timetablebackend.core.models.requests.AddSubject;

import java.util.List;
import java.util.Optional;

public interface ISubjectsService {
    List<Subject> getSubjects();

    Optional<Subject> getSubjectById(long id);

    long addSubject(AddSubject addSubject);

    void editSubject(Subject subject);

    void deleteSubjectById(long id);
}
