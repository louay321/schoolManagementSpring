package Louay.schoolManagement.controller;
import Louay.schoolManagement.domain.Subject;
import Louay.schoolManagement.service.SubjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    
    private static final Logger LOGGER = LogManager.getLogger();

    private SubjectService subjectService;

    public SubjectController(@Autowired SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getById(@PathVariable("id") String id) {
        Optional<Subject> sv = subjectService.findById(id);
        if (sv.isPresent()) {
            return ResponseEntity.of(sv);
        }
        throw new IllegalArgumentException(String.format(("%s id does not exist in the database"), id));
    }

    @GetMapping("/all")
    public List<Subject> getAll() {
        List<Subject> result = subjectService.findAll();
        return result;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        Optional<Subject> sv = subjectService.findById(id);
        if (sv.isPresent()){
            subjectService.delete(id);
            return;
        }
        throw new IllegalArgumentException(String.format(("%s id does not exist in the database"), id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") String id, @RequestBody Subject v) {
        Optional<Subject> sv = subjectService.findById(id);
        if (sv.isPresent()){
            Subject subject = sv.get();
            subject.setSubjectID(v.getSubjectID());
            subject.setCredits(v.getCredits());
            subject.setMaxStudents(v.getMaxStudents());
            subjectService.save(subject);
            return;
        }
        throw new IllegalArgumentException(String.format(("%d id does not exist in the database"), v.getId()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Subject v) {
        subjectService.save(v);
    }
}
