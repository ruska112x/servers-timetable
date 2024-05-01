package org.karabalin.timetablebackend.core.controllers;

import org.karabalin.timetablebackend.core.models.Group;
import org.karabalin.timetablebackend.core.services.groups.interfaces.IGroupsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupsController {

    private final IGroupsService groupsService;

    public GroupsController(IGroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupsService.getAllGroups();
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable("id") long id) {
        Optional<Group> group = groupsService.getGrouById(id);
        if (group.isPresent()) {
            return group.get();
        } else {
            throw new RuntimeException();
        }
    }

    @PostMapping
    public String addGroup(@RequestBody String name) {
        groupsService.addGroup(name);
        return "addGroup";
    }

    @PutMapping
    public String editGroup(@RequestBody Group group) {
        groupsService.editGroup(group);
        return "editGroup";
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable("id") long id) {
        groupsService.deleteGroupById(id);
        return "deleteGroup";
    }
}
