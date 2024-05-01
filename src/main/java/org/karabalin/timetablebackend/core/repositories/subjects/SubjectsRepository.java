package org.karabalin.timetablebackend.core.repositories.subjects;

import org.karabalin.timetablebackend.core.exceptions.AddInDatabaseException;
import org.karabalin.timetablebackend.core.exceptions.EditInDatabaseException;
import org.karabalin.timetablebackend.core.exceptions.NotFoundInDatabaseException;
import org.karabalin.timetablebackend.core.models.Subject;
import org.karabalin.timetablebackend.core.models.requests.AddSubject;
import org.karabalin.timetablebackend.core.repositories.subjects.interfaces.ISubjectRepository;
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
public class SubjectsRepository implements ISubjectRepository {

    private final JdbcOperations jdbcOperations;
    private final RowMapper<Subject> subjectRowMapper;

    public SubjectsRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.subjectRowMapper = (rs, rowNum) -> {
            long subjectId = rs.getLong("subject_id");
            String subjectName = rs.getString("subject_name");
            return new Subject(subjectId, subjectName);
        };
    }

    @Override
    public List<Subject> getSubjects() {
        String sql = "select * from \"subjects\"";
        return jdbcOperations.query(sql, subjectRowMapper);
    }

    @Override
    public Subject getSubjectById(long id) {
        try {
            String sql = "select * from \"subjects\" where \"subject_id\" = ?";
            return jdbcOperations.queryForObject(sql, subjectRowMapper, id);
        } catch (DataAccessException e) {
            throw new NotFoundInDatabaseException("Subject with id: " + id + " not found");
        }
    }

    @Override
    public long addSubject(AddSubject addSubject) {
        try {
            String sql = "insert into \"subjects\" (\"subject_name\") values (?)";
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator preparedStatementCreator = conn -> {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[]{"subject_id"});
                preparedStatement.setString(1, addSubject.getName());
                return preparedStatement;
            };
            jdbcOperations.update(preparedStatementCreator, generatedKeyHolder);
            return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
        } catch (DataAccessException e) {
            throw new AddInDatabaseException("Can't add subject: " + addSubject.getName());
        }
    }

    @Override
    public void editSubject(Subject subject) {
        try {
            String sql = "update \"subjects\" set \"subject_name\" = ? where \"subject_id\" = ?";
            jdbcOperations.update(sql, subject.getName(), subject.getId());
        } catch (DataAccessException e) {
            throw new EditInDatabaseException("Can't edit subject: " + subject);
        }
    }

    @Override
    public void deleteSubjectById(long id) {
        String sql = "delete from \"subjects\" where \"subject_id\" = ?";
        jdbcOperations.update(sql, id);
    }
}
