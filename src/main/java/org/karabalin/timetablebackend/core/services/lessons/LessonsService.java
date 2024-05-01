package org.karabalin.timetablebackend.core.services.lessons;

import org.karabalin.timetablebackend.core.models.Group;
import org.karabalin.timetablebackend.core.models.Lesson;
import org.karabalin.timetablebackend.core.models.LessonWithAttendance;
import org.karabalin.timetablebackend.core.models.Student;
import org.karabalin.timetablebackend.core.repositories.groups.interfaces.IGroupsRepository;
import org.karabalin.timetablebackend.core.repositories.lessons.interfaces.ILessonsRepository;
import org.karabalin.timetablebackend.core.repositories.students.interfaces.IStudentsRepository;
import org.karabalin.timetablebackend.core.services.lessons.interfaces.ILessonsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonsService implements ILessonsService {

    private final ILessonsRepository lessonsRepository;
    private final IGroupsRepository groupsRepository;
    private final IStudentsRepository studentsRepository;

    public LessonsService(ILessonsRepository lessonsRepository, IGroupsRepository groupsRepository, IStudentsRepository studentsRepository) {
        this.lessonsRepository = lessonsRepository;
        this.groupsRepository = groupsRepository;
        this.studentsRepository = studentsRepository;
    }

    @Override
    public List<Lesson> getLessonsByGroupId(String startDate, String endDate, long groupId) {
        return lessonsRepository.getLessonsByGroupId(startDate, endDate, groupId);
    }

    @Override
    public List<Lesson> getLessonsByTeacherId(String startDate, String endDate, long teacherId) {
        return lessonsRepository.getLessonsByTeacherId(startDate, endDate, teacherId);
    }

    @Override
    public LessonWithAttendance getLessonById(long id) {
        Lesson lesson = lessonsRepository.getLessonById(id);
        List<Group> groups = groupsRepository.getGroupsByLessonId(id);
        List<Student> students = studentsRepository.getStudentsByLessonId(id);
        return new LessonWithAttendance(lesson, students);
    }
}
