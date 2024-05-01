package org.karabalin.timetablebackend.core.repositories.subjects.interfaces;

import org.karabalin.timetablebackend.core.models.Subject;
import org.karabalin.timetablebackend.core.models.requests.AddSubject;

import java.util.List;

public interface ISubjectRepository {
    List<Subject> getSubjects();

    Subject getSubjectById(long id);

    long addSubject(AddSubject addSubject);

    void editSubject(Subject subject);

    void deleteSubjectById(long id);
}
