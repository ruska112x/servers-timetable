package org.karabalin.timetablebackend.core.repositories.groups.interfaces;

import java.util.List;

import org.karabalin.timetablebackend.core.models.Group;

public interface IGroupsRepository {
    List<Group> getAllGroups();
}
