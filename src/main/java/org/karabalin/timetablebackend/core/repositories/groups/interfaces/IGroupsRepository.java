package org.karabalin.timetablebackend.core.repositories.groups.interfaces;

import org.karabalin.timetablebackend.core.models.Group;

import java.util.List;

public interface IGroupsRepository {
    List<Group> getAllGroups();

    List<Group> getGroupsByLessonId(long id);

    Group getGroupById(long id);

    long addGroup(String name);

    void editGroup(Group group);

    void deleteGroupById(long id);
}
