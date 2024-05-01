package org.karabalin.timetablebackend.core.services.lessons.interfaces;

import org.karabalin.timetablebackend.core.models.FullLesson;
import org.karabalin.timetablebackend.core.models.Lesson;
import org.karabalin.timetablebackend.core.models.LessonWithAttendance;
import org.karabalin.timetablebackend.core.models.requests.AddLessonInSchedule;

import java.util.List;

public interface ILessonsService {
    List<Lesson> getLessonsByGroupId(String startDate, String endDate, long groupId);

    List<Lesson> getLessonsByTeacherId(String startDate, String endDate, long teacherId);

    LessonWithAttendance getLessonById(long id);

    long addLesson(AddLessonInSchedule addLessonInSchedule);

    void editLesson(FullLesson fullLesson);

    void deleteLessonById(long id);
}
