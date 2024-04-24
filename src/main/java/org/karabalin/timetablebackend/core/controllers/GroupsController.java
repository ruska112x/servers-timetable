package org.karabalin.timetablebackend.core.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public String getGroupById(@PathVariable("id") int id) {
        return String.valueOf(id);
    }

    @PostMapping("/groups")
    public String addGroup(@RequestBody String entity) {
        return entity;
    }

    @PutMapping("/groups/{id}")
    public String editGroup(@PathVariable int id, @RequestBody String entity) {
        return entity;
    }

    @DeleteMapping("/groups/{id}")
    public String deleteGroup(@PathVariable("id") int id) {
        return String.valueOf(id);
    }
}
