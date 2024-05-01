package org.karabalin.timetablebackend.core.controllers;

import org.karabalin.timetablebackend.core.models.Student;
import org.karabalin.timetablebackend.core.services.students.interfaces.IStudentsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentsController {

    private final IStudentsService studentsService;

    public StudentsController(IStudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping("/students")
    public List<Student> getStudentsByGroupId(@RequestParam("groupId") long groupId) {
        return studentsService.getStudentsByGroupId(groupId);
    }

    @GetMapping("/students/{id}")
    public Student getStudentsById(@PathVariable("id") long id) {
        Optional<Student> student = studentsService.getStudentById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new RuntimeException();
        }
    }

    @PostMapping("/students")
    public long addStudent(@RequestBody Student student) {
        return studentsService.addStudent(student);
    }

    @PutMapping("students")
    public String editStudent(@RequestBody Student student) {
        studentsService.editStudent(student);
        return "editStudent";
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable long id) {
        studentsService.deleteStudentById(id);
        return "deleteStudent";
    }

}
