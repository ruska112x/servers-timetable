package org.karabalin.timetablebackend.core.controllers;

import java.util.List;
import java.util.Optional;

import org.karabalin.timetablebackend.core.models.Student;
import org.karabalin.timetablebackend.core.services.students.interfaces.IStudentsService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

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
    public String addStudent(@RequestBody Student student) {
        studentsService.addStudent(student);
        return "addStudent";
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
