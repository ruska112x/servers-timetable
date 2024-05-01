package org.karabalin.timetablebackend.core.repositories.groups.interfaces;

import org.karabalin.timetablebackend.core.models.Group;
import org.karabalin.timetablebackend.core.models.requests.AddGroup;

import java.util.List;

public interface IGroupsRepository {
    List<Group> getAllGroups();

    List<Group> getGroupsByLessonId(long id);

    void addGroupsForLesson(List<Object[]> idsList);

    Group getGroupById(long id);

    long addGroup(AddGroup addGroup);

    void editGroup(Group group);

    void deleteGroupById(long id);
}
