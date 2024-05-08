package org.karabalin.timetablebackend.core.repositories.teachers;

import org.karabalin.timetablebackend.core.exceptions.AddInDatabaseException;
import org.karabalin.timetablebackend.core.exceptions.EditInDatabaseException;
import org.karabalin.timetablebackend.core.exceptions.NotFoundInDatabaseException;
import org.karabalin.timetablebackend.core.models.Teacher;
import org.karabalin.timetablebackend.core.models.requests.AddTeacher;
import org.karabalin.timetablebackend.core.repositories.teachers.interfaces.ITeachersRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
public class TeachersRepository implements ITeachersRepository {

    private final JdbcOperations jdbcOperations;
    private final RowMapper<Teacher> teacherRowMapper;

    public TeachersRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.teacherRowMapper = (rs, rowNum) -> {
            long teacherId = rs.getLong("teacher_id");
            String teacherSurname = rs.getString("teacher_surname");
            String teacherName = rs.getString("teacher_name");
            String teacherPatronymic = rs.getString("teacher_patronymic");
            String teacherPosition = rs.getString("teacher_position");
            return new Teacher(teacherId, teacherSurname, teacherName, teacherPatronymic, teacherPosition);
        };
    }

    @Override
    public List<Teacher> getTeachers() {
        String sql = "select * from \"teachers\"";
        return jdbcOperations.query(sql, teacherRowMapper);
    }

    @Override
    public Teacher getTeacherById(long id) {
        try {
            String sql = "select * from \"teachers\" where \"teacher_id\" = ?";
            return jdbcOperations.queryForObject(sql, teacherRowMapper, id);
        } catch (DataAccessException e) {
            throw new NotFoundInDatabaseException("Teacher with id: " + id + " not found");
        }
    }

    @Override
    public long addTeacher(AddTeacher addTeacher) {
        try {
            String sql = "insert into \"teachers\" (\"teacher_surname\", \"teacher_name\", \"teacher_patronymic\", \"teacher_position\") values (?, ?, ?, ?)";
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator preparedStatementCreator = conn -> {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[]{"teacher_id"});
                preparedStatement.setString(1, addTeacher.getSurname());
                preparedStatement.setString(2, addTeacher.getName());
                preparedStatement.setString(3, addTeacher.getPatronymic());
                preparedStatement.setString(4, addTeacher.getPosition());
                return preparedStatement;
            };
            jdbcOperations.update(preparedStatementCreator, generatedKeyHolder);
            return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
        } catch (DataAccessException e) {
            throw new AddInDatabaseException("Can't add teacher: " + addTeacher);
        }
    }

    @Override
    public void editTeacher(Teacher teacher) {
        try {
            String sql = "update \"teachers\" set \"teacher_surname\" = ?, \"teacher_name\" = ?, \"teacher_patronymic\" = ?, \"teacher_position\" = ? where \"teacher_id\" = ?";
            int rowsChanged = jdbcOperations.update(sql, teacher.getSurname(), teacher.getName(), teacher.getPatronymic(), teacher.getPosition(), teacher.getId());
            if (rowsChanged == 0) {
                throw new EditInDatabaseException("Can't edit teacher: " + teacher);
            }
        } catch (DataAccessException e) {
            throw new EditInDatabaseException("Can't edit teacher: " + teacher);
        }
    }

    @Override
    public void deleteTeacherById(long id) {
        String sql = "delete from \"teachers\" where \"teacher_id\" = ?";
        int rowsChanged = jdbcOperations.update(sql, id);
        if (rowsChanged == 0) {
            throw new EditInDatabaseException("Can't delete teacher with id: " + id);
        }
    }
}
