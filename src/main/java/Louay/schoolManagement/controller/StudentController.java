package Louay.schoolManagement.controller;

import Louay.schoolManagement.domain.Student;
import Louay.schoolManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/student")

public class StudentController {

    private static final Logger logger = LogManager.getLogger();
    private StudentService studentService;

    public StudentController(@Autowired StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") String id){
        Optional<Student> s = studentService.findById(id);
        if (s.isPresent()){
            return ResponseEntity.of(s);
        }
        throw new IllegalArgumentException("id not existing.");
    }

    @GetMapping("/all")
    public List<Student> getAll(){
        List<Student> found = studentService.findAll();
        return found;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        Optional<Student> sv = studentService.findById(id);
        if (sv.isPresent()){
            studentService.delete(id);
            return;
        }
        throw new IllegalArgumentException("id not existing.");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") String id, @RequestBody Student s){
        Optional<Student> sp = studentService.findById(id);
        if (sp.isPresent()){
            Student student = sp.get();
            student.setStudentID(s.getStudentID());
            student.setFirstName(s.getFirstName());
            student.setLastName(s.getLastName());
            student.setClassNumber(s.getClassNumber());
            student.setExpectedGraduation(s.getExpectedGraduation());
            student.setSubjectsTaking(s.getSubjectsTaking());
            student.setSpeciality(s.getSpeciality());
            studentService.save(student);

        }
        throw new IllegalArgumentException("wrong id.");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Student s){ studentService.save(s);}
}
