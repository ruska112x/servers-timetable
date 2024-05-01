package org.karabalin.timetablebackend.core.repositories.lessons.interfaces;

import org.karabalin.timetablebackend.core.models.Lesson;

import java.util.List;

public interface ILessonsRepository {
    List<Lesson> getLessonsByGroupId(String startDate, String endDate, long groupId);

    List<Lesson> getLessonsByTeacherId(String startDate, String endDate, long teacherId);

    Lesson getLessonById(long id);
}
