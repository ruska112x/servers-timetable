package org.karabalin.timetablebackend.core.repositories.students;

import org.karabalin.timetablebackend.core.exceptions.AddInDatabaseException;
import org.karabalin.timetablebackend.core.exceptions.EditInDatabaseException;
import org.karabalin.timetablebackend.core.exceptions.NotFoundInDatabaseException;
import org.karabalin.timetablebackend.core.models.Student;
import org.karabalin.timetablebackend.core.models.requests.AddStudent;
import org.karabalin.timetablebackend.core.repositories.students.interfaces.IStudentsRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class StudentsRepository implements IStudentsRepository {

    private final JdbcOperations jdbcOperations;
    private final RowMapper<Student> studentRowMapper;

    public StudentsRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.studentRowMapper = (rs, rowNum) -> {
            long studentId = rs.getLong("student_id");
            String studentSurname = rs.getString("student_surname");
            String studentName = rs.getString("student_name");
            String studentPatronymic = rs.getString("student_patronymic");
            String studentStatus = rs.getString("student_status");
            long groupId = rs.getLong("group_id");
            return new Student(studentId, studentSurname, studentName, studentPatronymic, studentStatus, groupId);
        };
    }

    @Override
    public long addStudent(AddStudent addStudent) {
        try {
            String sql = "insert into \"students\" (\"student_surname\", \"student_name\", \"student_patronymic\", \"student_status\", \"group_id\") values (?, ?, ?, ?, ?)";
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator preparedStatementCreator = conn -> {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[]{"student_id"});
                preparedStatement.setString(1, addStudent.getSurname());
                preparedStatement.setString(2, addStudent.getName());
                preparedStatement.setString(3, addStudent.getPatronymic());
                preparedStatement.setString(4, addStudent.getStatus());
                preparedStatement.setLong(5, addStudent.getGroupId());
                return preparedStatement;
            };
            jdbcOperations.update(preparedStatementCreator, generatedKeyHolder);
            return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
        } catch (DataAccessException e) {
            throw new AddInDatabaseException("Can't add student: " + addStudent);
        }
    }

    @Override
    public void addStudentsForLesson(List<Object[]> idsList) {
        try {
            String sql = "insert into \"attendance\" (\"lesson_id\", \"student_id\") values (?, ?)";
            BatchPreparedStatementSetter batchPreparedStatementSetter = new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setLong(1, (Long) idsList.get(i)[0]);
                    ps.setLong(2, (Long) idsList.get(i)[1]);
                }

                @Override
                public int getBatchSize() {
                    return idsList.size();
                }
            };
            jdbcOperations.batchUpdate(sql, batchPreparedStatementSetter);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            throw new AddInDatabaseException("Can't add students for lesson :: " + idsList);
        }
    }

    @Override
    public void deleteStudentById(long id) {
        String sql = "delete from \"students\" where \"student_id\" = ?";
        jdbcOperations.update(sql, id);
    }

    @Override
    public void editStudent(Student student) {
        try {
            String sql = "update \"students\" set \"student_surname\" = ?, \"student_name\" = ?, \"student_patronymic\" = ?, \"student_status\" = ?, \"group_id\" = ? where \"student_id\" = ?";
            jdbcOperations.update(sql, student.getSurname(), student.getName(), student.getPatronymic(), student.getStatus(), student.getGroupId(), student.getId());
        } catch (DataAccessException e) {
            throw new EditInDatabaseException("Can't edit student: " + student);
        }
    }

    @Override
    public Student getStudentById(long id) {
        try {
            String sql = "select * from \"students\" where \"student_id\" = ?";
            return jdbcOperations.queryForObject(sql, studentRowMapper, id);
        } catch (DataAccessException e) {
            throw new NotFoundInDatabaseException("Student with id: " + id + " not found");
        }
    }

    @Override
    public List<Student> getStudentsByGroupId(long id) {
        String sql = "select * from \"students\" where \"group_id\" = ?";
        return jdbcOperations.query(sql, studentRowMapper, id);
    }

    @Override
    public List<Student> getStudentsByLessonId(long id) {
        String sql = "select s.\"student_id\", s.\"student_surname\", s.\"student_name\", s.\"student_patronymic\", s.\"student_status\", s.\"group_id\" from \"attendance\" as a left join \"students\" as s on a.\"student_id\" = s.\"student_id\" where a.\"lesson_id\" = ?";
        return jdbcOperations.query(sql, studentRowMapper, id);
    }

}
