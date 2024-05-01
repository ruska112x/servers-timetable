package org.karabalin.timetablebackend.core.repositories.lessons;

import org.karabalin.timetablebackend.core.exceptions.NotFoundInDatabaseException;
import org.karabalin.timetablebackend.core.models.Lesson;
import org.karabalin.timetablebackend.core.repositories.lessons.interfaces.ILessonsRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LessonsRepository implements ILessonsRepository {

    private final JdbcOperations jdbcOperations;
    private final RowMapper<Lesson> lessonRowMapper;

    public LessonsRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.lessonRowMapper = (rs, rowNum) -> {
            long lessonId = rs.getLong("lesson_id");
            String lessonDate = rs.getDate("lesson_date").toString();
            long lessonNumberInSchedule = rs.getLong("lesson_number_in_schedule");
            long subjectId = rs.getLong("subject_id");
            long teacherId = rs.getLong("teacher_id");
            return new Lesson(lessonId, lessonDate, lessonNumberInSchedule, subjectId, teacherId);
        };
    }

    @Override
    public List<Lesson> getLessonsByGroupId(String startDate, String endDate, long groupId) {
        String sql = "select l.\"lesson_id\", l.\"lesson_date\", l.\"lesson_number_in_schedule\", l.\"subject_id\", l.\"teacher_id\" from \"lessons\" as l left join lessons_for_groups as lfg on l.\"lesson_id\" = lfg.\"lesson_id\" where l.\"lesson_date\" >= TO_DATE(?, 'DDMMYYYY') and l.\"lesson_date\" < TO_DATE(?, 'DDMMYYYY') and lfg.\"group_id\" = ?";
        return jdbcOperations.query(sql, lessonRowMapper, startDate, endDate, groupId);
    }

    @Override
    public List<Lesson> getLessonsByTeacherId(String startDate, String endDate, long teacherId) {
        String sql = "select * from \"lessons\" where \"lesson_date\" >= TO_DATE(?, 'DDMMYYYY') and \"lesson_date\" < TO_DATE(?, 'DDMMYYYY') and \"teacher_id\" = ?";
        return jdbcOperations.query(sql, lessonRowMapper, startDate, endDate, teacherId);
    }

    @Override
    public Lesson getLessonById(long id) {
        try {
            String sql = "select * from \"lessons\" where \"lesson_id\" = ?";
            return jdbcOperations.queryForObject(sql, lessonRowMapper, id);
        } catch (DataAccessException e) {
            throw new NotFoundInDatabaseException("Lesson with id: " + id + " not found");
        }
    }
}
