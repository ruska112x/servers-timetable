package org.karabalin.timetablebackend.core.repositories.groups;

import java.sql.PreparedStatement;
import java.util.List;

import org.karabalin.timetablebackend.core.models.Group;
import org.karabalin.timetablebackend.core.repositories.groups.interfaces.IGroupsRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GroupsRepository implements IGroupsRepository {

    private final JdbcOperations jdbcTemplate;

    public GroupsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Group> getAllGroups() {
        String sql = "select * from \"groups\"";
        RowMapper<Group> rowMapper = (rs, rowNum) -> {
            long groupId = rs.getInt("group_id");
            String groupName = rs.getString("group_name");
            return new Group(groupId, groupName);
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Group getGroupById(long id) {
        String sql = "select * from \"groups\" where \"group_id\" = ?";
        RowMapper<Group> rowMapper = (rs, rowNum) -> {
            long groupId = rs.getInt("group_id");
            String groupName = rs.getString("group_name");
            return new Group(groupId, groupName);
        };
        Group group = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return group;
    }

    @Override
    public long addGroup(String name) {
        String sql = "insert into \"groups\" (\"group_name\") values (?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] { "group_id" });
            preparedStatement.setString(1, name);
            return preparedStatement;
        };
        long id = jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        return id;
    }

    @Override
    public void editGroup(Group group) {
        String sql = "update \"groups\" set \"group_name\" = ? where \"group_id\" = ?";
        jdbcTemplate.update(sql, group.getName(), group.getId());
    }

    @Override
    public void deleteGroupById(long id) {
        String sql = "delete from \"groups\" where \"group_id\" = ?";
        jdbcTemplate.update(sql, id);
    }
}
