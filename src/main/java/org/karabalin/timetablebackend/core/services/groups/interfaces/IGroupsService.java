package org.karabalin.timetablebackend.core.services.groups.interfaces;

import java.util.List;
import java.util.Optional;

import org.karabalin.timetablebackend.core.models.Group;

public interface IGroupsService {
    List<Group> getAllGroups();

    Optional<Group> getGrouById(long id);

    long addGroup(String name);

    void editGroup(Group group);

    void deleteGroupById(long id);
}
