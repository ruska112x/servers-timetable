package org.karabalin.timetablebackend.core.services.groups.interfaces;

import org.karabalin.timetablebackend.core.models.Group;
import org.karabalin.timetablebackend.core.models.requests.AddGroup;

import java.util.List;

public interface IGroupsService {
    List<Group> getAllGroups();

    Group getGroupById(long id);

    long addGroup(AddGroup addGroup);

    void editGroup(Group group);

    void deleteGroupById(long id);
}
