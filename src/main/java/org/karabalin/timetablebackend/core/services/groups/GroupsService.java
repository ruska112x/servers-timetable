package org.karabalin.timetablebackend.core.services.groups;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Group> getGrouById(long id) {
        Group group = groupsRepository.getGroupById(id);
        return Optional.of(group);
    }

    @Override
    public long addGroup(String name) {
        long id = groupsRepository.addGroup(name);
        return id;
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
