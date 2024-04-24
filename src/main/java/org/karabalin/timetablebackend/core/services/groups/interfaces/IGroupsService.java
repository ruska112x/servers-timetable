package org.karabalin.timetablebackend.core.services.groups.interfaces;

import java.util.List;

import org.karabalin.timetablebackend.core.models.Group;

public interface IGroupsService {
    List<Group> getAllGroups();
}
