package org.karabalin.timetablebackend.core.repositories.groups;

import org.karabalin.timetablebackend.core.exceptions.AddInDatabaseException;
import org.karabalin.timetablebackend.core.exceptions.EditInDatabaseException;
import org.karabalin.timetablebackend.core.exceptions.NotFoundInDatabaseException;
import org.karabalin.timetablebackend.core.models.Group;
import org.karabalin.timetablebackend.core.models.requests.AddGroup;
import org.karabalin.timetablebackend.core.repositories.groups.interfaces.IGroupsRepository;
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
public class GroupsRepository implements IGroupsRepository {

    private final JdbcOperations jdbcOperations;
    private final RowMapper<Group> grouRowMapper;

    public GroupsRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.grouRowMapper = (rs, rowNum) -> {
            long groupId = rs.getInt("group_id");
            String groupName = rs.getString("group_name");
            return new Group(groupId, groupName);
        };
    }

    @Override
    public List<Group> getAllGroups() {
        String sql = "select * from \"groups\"";
        return jdbcOperations.query(sql, grouRowMapper);
    }

    @Override
    public List<Group> getGroupsByLessonId(long id) {
        String sql = "select g.\"group_id\", g.\"group_name\" from \"lessons_for_groups\" as lfg left join \"groups\" as g on lfg.\"group_id\" = g.\"group_id\" where lfg.\"lesson_id\" = ?";
        return jdbcOperations.query(sql, grouRowMapper, id);
    }

    @Override
    public void addGroupsForLesson(List<Object[]> idsList) {
        try {
            String sql = "insert into \"lessons_for_groups\" (\"lesson_id\", \"group_id\") values (?, ?)";
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
            throw new AddInDatabaseException("Can't add groups for lesson :: " + idsList);
        }
    }

    @Override
    public Group getGroupById(long id) {
        try {
            String sql = "select * from \"groups\" where \"group_id\" = ?";
            return jdbcOperations.queryForObject(sql, grouRowMapper, id);
        } catch (DataAccessException e) {
            throw new NotFoundInDatabaseException("Group with id: " + id + " not found!");
        }
    }

    @Override
    public long addGroup(AddGroup addGroup) {
        try {
            String sql = "insert into \"groups\" (\"group_name\") values (?)";
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator preparedStatementCreator = conn -> {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[]{"group_id"});
                preparedStatement.setString(1, addGroup.getName());
                return preparedStatement;
            };
            jdbcOperations.update(preparedStatementCreator, generatedKeyHolder);
            return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
        } catch (DataAccessException e) {
            throw new AddInDatabaseException("Can't add group with name" + addGroup.getName());
        }
    }

    @Override
    public void editGroup(Group group) {
        try {
            String sql = "update \"groups\" set \"group_name\" = ? where \"group_id\" = ?";
            jdbcOperations.update(sql, group.getName(), group.getId());
        } catch (DataAccessException e) {
            throw new EditInDatabaseException("Can't edit group: " + group);
        }
    }

    @Override
    public void deleteGroupById(long id) {
        String sql = "delete from \"groups\" where \"group_id\" = ?";
        jdbcOperations.update(sql, id);
    }
}
