package org.karabalin.timetablebackend.core.services.groups;

import org.karabalin.timetablebackend.core.models.Group;
import org.karabalin.timetablebackend.core.repositories.groups.interfaces.IGroupsRepository;
import org.karabalin.timetablebackend.core.services.groups.interfaces.IGroupsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Group> getGroupById(long id) {
        return Optional.of(groupsRepository.getGroupById(id));
    }

    @Override
    public long addGroup(String name) {
        return groupsRepository.addGroup(name);
    }

    @Override
    public void editGroup(Group group) {
        groupsRepository.editGroup(group);
    }

    @Override
    public void deleteGroupById(long id) {
        groupsRepository.deleteGroupById(id);
    }
}
