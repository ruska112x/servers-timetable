package org.karabalin.timetablebackend.core.controllers;

import org.karabalin.timetablebackend.core.models.LessonWithAttendance;
import org.karabalin.timetablebackend.core.services.lessons.interfaces.ILessonsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final ILessonsService lessonsService;

    public LessonsController(ILessonsService lessonsService) {
        this.lessonsService = lessonsService;
    }

    @GetMapping("/{id}")
    public LessonWithAttendance getLessonById(@PathVariable("id") long id) {
        return lessonsService.getLessonById(id);
    }
}
