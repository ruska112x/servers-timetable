package org.karabalin.timetablebackend.core.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.karabalin.timetablebackend.core.models.Group;
import org.karabalin.timetablebackend.core.services.groups.interfaces.IGroupsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class GroupsController {

    private final IGroupsService groupsService;

    public GroupsController(IGroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @GetMapping("/groups")
    public List<Group> getAllGroups() {
        return groupsService.getAllGroups();
    }

    @GetMapping("/groups/{id}")
    public Group getGroupById(@PathVariable("id") long id) {
        Optional<Group> group = groupsService.getGrouById(id);
        if (group.isPresent()) {
            return group.get();
        } else {
            throw new RuntimeException();
        }
    }

    @PostMapping("/groups")
    public String addGroup(@RequestBody String name) {
        groupsService.addGroup(name);
        return "addGroup";
    }

    @PutMapping("/groups")
    public String editGroup(@RequestBody Group group) {
        groupsService.editGroup(group);
        return "editGroup";
    }

    @DeleteMapping("/groups/{id}")
    public String deleteGroup(@PathVariable("id") long id) {
        groupsService.deleteGroupById(id);
        return "deleteGroup";
    }
}
