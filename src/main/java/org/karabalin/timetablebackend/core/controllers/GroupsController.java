package org.karabalin.timetablebackend.core.controllers;

import jakarta.validation.Valid;
import org.karabalin.timetablebackend.core.models.Group;
import org.karabalin.timetablebackend.core.models.Lesson;
import org.karabalin.timetablebackend.core.models.requests.AddGroup;
import org.karabalin.timetablebackend.core.services.groups.interfaces.IGroupsService;
import org.karabalin.timetablebackend.core.services.lessons.interfaces.ILessonsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupsController {

    private final IGroupsService groupsService;
    private final ILessonsService lessonsService;

    public GroupsController(IGroupsService groupsService, ILessonsService lessonsService) {
        this.groupsService = groupsService;
        this.lessonsService = lessonsService;
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupsService.getAllGroups();
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable("id") long id) {
        return groupsService.getGroupById(id);
    }

    @PostMapping
    public long addGroup(@Valid @RequestBody AddGroup addGroup) {
        return groupsService.addGroup(addGroup);
    }

    @PutMapping
    public String editGroup(@Valid @RequestBody Group group) {
        groupsService.editGroup(group);
        return "editGroup";
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable("id") long id) {
        groupsService.deleteGroupById(id);
        return "deleteGroup";
    }

    @GetMapping("/{id}/lessons")
    public List<Lesson> getLessons(@PathVariable("id") long id, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return lessonsService.getLessonsByGroupId(startDate, endDate, id);
    }
}
