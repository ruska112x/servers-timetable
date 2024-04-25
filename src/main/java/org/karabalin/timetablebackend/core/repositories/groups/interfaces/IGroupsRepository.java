package org.karabalin.timetablebackend.core.repositories.groups.interfaces;

import java.util.List;

import org.karabalin.timetablebackend.core.models.Group;

public interface IGroupsRepository {
    List<Group> getAllGroups();

    Group getGroupById(long id);

    long addGroup(String name);

    void editGroup(Group group);

    void deleteGroupById(long id);
}
