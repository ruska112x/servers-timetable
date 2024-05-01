package org.karabalin.timetablebackend.core.controllers;

import jakarta.validation.Valid;
import org.karabalin.timetablebackend.core.models.Student;
import org.karabalin.timetablebackend.core.models.requests.AddStudent;
import org.karabalin.timetablebackend.core.services.students.interfaces.IStudentsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final IStudentsService studentsService;

    public StudentsController(IStudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping
    public List<Student> getStudentsByGroupId(@RequestParam("groupId") long groupId) {
        return studentsService.getStudentsByGroupId(groupId);
    }

    @GetMapping("/{id}")
    public Student getStudentsById(@PathVariable("id") long id) {
        Optional<Student> student = studentsService.getStudentById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new RuntimeException();
        }
    }

    @PostMapping
    public long addStudent(@Valid @RequestBody AddStudent addStudent) {
        return studentsService.addStudent(addStudent);
    }

    @PutMapping
    public String editStudent(@Valid @RequestBody Student student) {
        studentsService.editStudent(student);
        return "editStudent";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable long id) {
        studentsService.deleteStudentById(id);
        return "deleteStudent";
    }

}
