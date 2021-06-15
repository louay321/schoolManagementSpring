package Louay.schoolManagement.controller;

import Louay.schoolManagement.domain.Teacher;
import Louay.schoolManagement.service.TeacherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    
    private static final Logger LOGGER = LogManager.getLogger();

    private TeacherService teacherService;

    public TeacherController(@Autowired TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable("id") String id) {
        Optional<Teacher> sp = teacherService.findById(id);
        if (sp.isPresent()) {
            return ResponseEntity.of(sp);
        }
        throw new IllegalArgumentException(String.format(("%s id does not exist in the database"), id));
    }

    @GetMapping("/all")
    public List<Teacher> getAll() {
        List<Teacher> result = teacherService.findAll();
        return result;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        Optional<Teacher> sv = teacherService.findById(id);
        if (sv.isPresent()){
            teacherService.delete(id);
            return;
        }
        throw new IllegalArgumentException("id not existing");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") String id, @RequestBody Teacher p) {
        Optional<Teacher> sp = teacherService.findById(id);
        if (sp.isPresent()){
            Teacher teacher = sp.get();
            teacher.setTeacherID(p.getTeacherID());
            teacher.setFirstName(p.getFirstName());
            teacher.setLastName(p.getLastName());
            teacher.setPrimarySubject(p.getPrimarySubject());
            teacher.setSecondarySubject(p.getSecondarySubject());
            teacher.setStudentsSupervising(p.getStudentsSupervising());
            teacherService.save(teacher);
            return;
        }
        throw new IllegalArgumentException(String.format(("%d id does not exist in the database"), p.getId()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Teacher p) {
        teacherService.save(p);
    }
}
