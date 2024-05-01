package org.karabalin.timetablebackend.core.repositories.subjects.interfaces;

import org.karabalin.timetablebackend.core.models.Subject;

import java.util.List;

public interface ISubjectRepository {
    List<Subject> getSubjects();

    Subject getSubjectById(long id);

    long addSubject(Subject subject);

    void editSubject(Subject subject);

    void deleteSubjectById(long id);
}
