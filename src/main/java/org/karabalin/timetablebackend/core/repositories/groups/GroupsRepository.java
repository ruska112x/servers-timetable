package org.karabalin.timetablebackend.core.repositories.groups;

import org.karabalin.timetablebackend.core.exceptions.AddInDatabaseException;
import org.karabalin.timetablebackend.core.exceptions.EditInDatabaseException;
import org.karabalin.timetablebackend.core.exceptions.NotFoundInDatabaseException;
import org.karabalin.timetablebackend.core.models.Group;
import org.karabalin.timetablebackend.core.repositories.groups.interfaces.IGroupsRepository;
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
public class GroupsRepository implements IGroupsRepository {

    private final JdbcOperations jdbcTemplate;
    private final RowMapper<Group> grouRowMapper;

    public GroupsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.grouRowMapper = (rs, rowNum) -> {
            long groupId = rs.getInt("group_id");
            String groupName = rs.getString("group_name");
            return new Group(groupId, groupName);
        };
    }

    @Override
    public List<Group> getAllGroups() {
        String sql = "select * from \"groups\"";
        return jdbcTemplate.query(sql, grouRowMapper);
    }

    @Override
    public Group getGroupById(long id) {
        try {
            String sql = "select * from \"groups\" where \"group_id\" = ?";
            return jdbcTemplate.queryForObject(sql, grouRowMapper, id);
        } catch (DataAccessException e) {
            throw new NotFoundInDatabaseException("Group with id: " + id + " not found!");
        }
    }

    @Override
    public long addGroup(String name) {
        try {
            String sql = "insert into \"groups\" (\"group_name\") values (?)";
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator preparedStatementCreator = conn -> {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[]{"group_id"});
                preparedStatement.setString(1, name);
                return preparedStatement;
            };
            jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
            return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
        } catch (DataAccessException e) {
            throw new AddInDatabaseException("Can't add group with name" + name);
        }
    }

    @Override
    public void editGroup(Group group) {
        try {
            String sql = "update \"groups\" set \"group_name\" = ? where \"group_id\" = ?";
            jdbcTemplate.update(sql, group.getName(), group.getId());
        } catch (DataAccessException e) {
            throw new EditInDatabaseException("Can't edit group: " + group);
        }
    }

    @Override
    public void deleteGroupById(long id) {
        String sql = "delete from \"groups\" where \"group_id\" = ?";
        jdbcTemplate.update(sql, id);
    }
}
