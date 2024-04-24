package org.karabalin.timetablebackend.core.repositories.groups;

import java.util.List;

import org.karabalin.timetablebackend.core.models.Group;
import org.karabalin.timetablebackend.core.repositories.groups.interfaces.IGroupsRepository;
import org.springframework.jdbc.core.JdbcOperations;
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
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Group(rs.getInt("group_id"), rs.getString("group_name")));
    }
}
