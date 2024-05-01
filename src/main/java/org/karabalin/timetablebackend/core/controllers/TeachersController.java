package org.karabalin.timetablebackend.core.controllers;

import org.karabalin.timetablebackend.core.models.Lesson;
import org.karabalin.timetablebackend.core.models.Teacher;
import org.karabalin.timetablebackend.core.services.lessons.interfaces.ILessonsService;
import org.karabalin.timetablebackend.core.services.teachers.interfaces.ITeachersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeachersController {
    private final ITeachersService teachersService;
    private final ILessonsService lessonsService;

    public TeachersController(ITeachersService teachersService, ILessonsService lessonsService) {
        this.teachersService = teachersService;
        this.lessonsService = lessonsService;
    }

    @GetMapping
    public List<Teacher> getTeachers() {
        return teachersService.getTeachers();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable("id") long id) {
        Optional<Teacher> teacher = teachersService.getTeacherById(id);
        if (teacher.isPresent()) {
            return teacher.get();
        } else {
            throw new RuntimeException();
        }
    }

    @PostMapping
    public long addTeacher(@RequestBody Teacher teacher) {
        return teachersService.addTeacher(teacher);
    }

    @PutMapping
    public String editTeacher(@RequestBody Teacher teacher) {
        teachersService.editTeacher(teacher);
        return "editTeacher";
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable("id") long id) {
        teachersService.deleteTeacherById(id);
        return "deleteTeacher";
    }

    @GetMapping("/{id}/lessons")
    public List<Lesson> getLessons(@PathVariable("id") long id, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return lessonsService.getLessonsByTeacherId(startDate, endDate, id);
    }
}
