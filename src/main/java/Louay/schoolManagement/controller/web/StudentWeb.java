package Louay.schoolManagement.controller.web;


import Louay.schoolManagement.domain.Student;
import Louay.schoolManagement.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller

public class StudentWeb {

    private static final Logger LOGGER = LogManager.getLogger();

    private StudentService studentService;

    public StudentWeb(@Autowired StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/all")
    public String showStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students";
    }

    @GetMapping("/students/new-student")
    public String createForm(Model model) {
        model.addAttribute("student", new Student());
        return "new-student";
    }

    @GetMapping("/students/{id}")
    public String showStudentById(@PathVariable Long id, Model model) {
        Student v = studentService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id : " + id));
        model.addAttribute("student", v);
        return "edit-student";
    }

    @GetMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id : " + id));
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping(value = "/students/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNewStudent(@ModelAttribute Student student, BindingResult result, Model model) {
        if (result.hasErrors())
            return "new-student";

        studentService.save(student);
        model.addAttribute("students", studentService.findAll());
        return "redirect:/students/all";

    }

    @PostMapping(value = "/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student d, BindingResult result, Model model) {
        if (result.hasErrors())
            return "edit-student";

        Student student = studentService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id : " + id));

        student.setStudentID(d.getStudentID());
        student.setFirstName(d.getFirstName());
        student.setLastName(d.getLastName());
        student.setClassNumber(d.getClassNumber());
        student.setSpeciality(d.getSpeciality());
        student.setSubjectsTaking(d.getSubjectsTaking());
        student.setExpectedGraduation(d.getExpectedGraduation());
        student.setPersonAddress(d.getPersonAddress());
        studentService.save(student);
        model.addAttribute("students", studentService.findAll());
        return "redirect:/students/all";

    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id, Model model) {
        studentService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id : " + id));
        studentService.delete(id);
        model.addAttribute("students", studentService.findAll());
        return "redirect:/students/all";
    }
}
