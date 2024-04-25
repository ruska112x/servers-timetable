package org.karabalin.timetablebackend.core.repositories.students;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import org.karabalin.timetablebackend.core.models.Student;
import org.karabalin.timetablebackend.core.repositories.students.interfaces.IStudentsRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class StudentsRepository implements IStudentsRepository {

    private final JdbcOperations jdbcTemplate;
    private final RowMapper<Student> studentRowMapper;

    public StudentsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
    public long addStudent(Student student) {
        String sql = "insert into \"students\"" +
                "(\"student_surname\", \"student_name\", \"student_patronymic\","
                + " \"student_status\", \"group_id\") values " +
                "(?, ?, ?, ?, ?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] { "student_id" });
            preparedStatement.setString(1, student.getSurname());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getPatronymic());
            preparedStatement.setString(4, student.getStatus());
            preparedStatement.setLong(5, student.getGroupId());
            return preparedStatement;
        };
        long id = jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        return id;
    }

    @Override
    public void deleteStudentById(long id) {
        String sql = "delete from \"students\" where \"student_id\" = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void editStudent(Student student) {
        String sql = "update \"students\"" +
                "set \"student_surname\" = ?, " +
                "\"student_name\" = ?, " +
                "\"student_patronymic\" = ?, " +
                "\"student_status\" = ?, " +
                "\"group_id\" = ? " +
                "where \"student_id\" = ?";
        jdbcTemplate.update(sql,
                student.getSurname(),
                student.getName(),
                student.getPatronymic(),
                student.getStatus(),
                student.getGroupId(),
                student.getId());
    }

    @Override
    public Student getStudentById(long id) {
        String sql = "select * from \"students\" where \"student_id\" = ?";
        return jdbcTemplate.queryForObject(sql, studentRowMapper, id);
    }

    @Override
    public List<Student> getStudentsByGroupId(long id) {
        String sql = "select * from \"students\" where \"group_id\" = ?";
        return jdbcTemplate.query(sql, studentRowMapper, id);
    }

}