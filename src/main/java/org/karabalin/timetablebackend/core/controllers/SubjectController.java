package org.karabalin.timetablebackend.core.controllers;

import jakarta.validation.Valid;
import org.karabalin.timetablebackend.core.models.Subject;
import org.karabalin.timetablebackend.core.models.requests.AddSubject;
import org.karabalin.timetablebackend.core.services.subjects.interfaces.ISubjectsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final ISubjectsService subjectsService;

    public SubjectController(ISubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    @GetMapping
    public List<Subject> getSubjects() {
        return subjectsService.getSubjects();
    }

    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable("id") long id) {
        Optional<Subject> subject = subjectsService.getSubjectById(id);
        if (subject.isPresent()) {
            return subject.get();
        } else {
            throw new RuntimeException();
        }
    }

    @PostMapping
    public long addSubject(@Valid @RequestBody AddSubject addSubject) {
        return subjectsService.addSubject(addSubject);
    }

    @PutMapping
    public String editSubject(@Valid @RequestBody Subject subject) {
        subjectsService.editSubject(subject);
        return "editSubject";
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable("id") long id) {
        subjectsService.deleteSubjectById(id);
        return "deleteSubject";
    }
}
