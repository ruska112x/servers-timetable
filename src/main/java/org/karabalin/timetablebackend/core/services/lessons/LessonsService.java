package org.karabalin.timetablebackend.core.services.lessons;

import org.karabalin.timetablebackend.core.models.*;
import org.karabalin.timetablebackend.core.models.requests.AddLesson;
import org.karabalin.timetablebackend.core.models.requests.AddLessonInSchedule;
import org.karabalin.timetablebackend.core.repositories.groups.interfaces.IGroupsRepository;
import org.karabalin.timetablebackend.core.repositories.lessons.interfaces.ILessonsRepository;
import org.karabalin.timetablebackend.core.repositories.students.interfaces.IStudentsRepository;
import org.karabalin.timetablebackend.core.services.lessons.interfaces.ILessonsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Transactional
    public LessonWithAttendance getLessonById(long id) {
        Lesson lesson = lessonsRepository.getLessonById(id);
        List<Group> groups = groupsRepository.getGroupsByLessonId(id);
        List<Student> students = studentsRepository.getStudentsByLessonId(id);
        return new LessonWithAttendance(lesson, students);
    }

    @Override
    @Transactional
    public long addLesson(AddLessonInSchedule addLessonInSchedule) {
        AddLesson addLesson = new AddLesson(addLessonInSchedule.getDate(), addLessonInSchedule.getNumberInSchedule(), addLessonInSchedule.getSubjectId(), addLessonInSchedule.getTeacherId());
        long lessonId = lessonsRepository.addLesson(addLesson);
        List<Object[]> groups = new ArrayList<>();
        List<Object[]> students = new ArrayList<>();
        for (long groupId : addLessonInSchedule.getGroupIdList()) {
            Long[] ids = new Long[]{lessonId, groupId};
            groups.add(ids);
        }
        for (long studentId : addLessonInSchedule.getStudentIdList()) {
            Long[] ids = new Long[]{lessonId, studentId};
            students.add(ids);
        }
        groupsRepository.addGroupsForLesson(groups);
        studentsRepository.addStudentsForLesson(students);
        return lessonId;
    }

    @Override
    @Transactional
    public void editLesson(FullLesson fullLesson) {
        Lesson lesson = new Lesson(fullLesson.getId(), fullLesson.getDate(), fullLesson.getNumberInSchedule(), fullLesson.getSubjectId(), fullLesson.getTeacherId());
        List<Object[]> groups = new ArrayList<>();
        List<Object[]> students = new ArrayList<>();
        for (long groupId : fullLesson.getGroupIdList()) {
            Long[] ids = new Long[]{lesson.getId(), groupId};
            groups.add(ids);
        }
        for (long studentId : fullLesson.getStudentIdList()) {
            Long[] ids = new Long[]{lesson.getId(), studentId};
            students.add(ids);
        }
        groupsRepository.addGroupsForLesson(groups);
        studentsRepository.addStudentsForLesson(students);
        lessonsRepository.editLesson(lesson);
    }

    @Override
    public void deleteLessonById(long id) {
        lessonsRepository.deleteLessonById(id);
    }
}
