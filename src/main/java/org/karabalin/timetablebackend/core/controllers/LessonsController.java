package org.karabalin.timetablebackend.core.controllers;

import jakarta.validation.Valid;
import org.karabalin.timetablebackend.core.models.FullLesson;
import org.karabalin.timetablebackend.core.models.LessonWithAttendance;
import org.karabalin.timetablebackend.core.models.requests.AddLessonInSchedule;
import org.karabalin.timetablebackend.core.services.lessons.interfaces.ILessonsService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public long addLesson(@Valid @RequestBody AddLessonInSchedule addLessonInSchedule) {
        return lessonsService.addLesson(addLessonInSchedule);
    }

    @PutMapping
    public void editLesson(@Valid @RequestBody FullLesson fullLesson) {
        lessonsService.editLesson(fullLesson);
    }

    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable("id") long id) {
        lessonsService.deleteLessonById(id);
        return "deleteLesson";
    }
}
