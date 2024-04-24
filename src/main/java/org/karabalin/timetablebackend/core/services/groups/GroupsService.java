package org.karabalin.timetablebackend.core.services.groups;

import java.util.List;

import org.karabalin.timetablebackend.core.models.Group;
import org.karabalin.timetablebackend.core.repositories.groups.interfaces.IGroupsRepository;
import org.karabalin.timetablebackend.core.services.groups.interfaces.IGroupsService;
import org.springframework.stereotype.Service;

@Service
public class GroupsService implements IGroupsService {

    private final IGroupsRepository groupsRepository;

    public GroupsService(IGroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupsRepository.getAllGroups();
    }
}
