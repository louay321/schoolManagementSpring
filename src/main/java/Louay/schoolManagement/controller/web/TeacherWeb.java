package Louay.schoolManagement.controller.web;

import Louay.schoolManagement.domain.Teacher;
import Louay.schoolManagement.service.TeacherService;
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

import java.text.ParseException;


@Controller
public class TeacherWeb {

    private static final Logger LOGGER = LogManager.getLogger();

    private TeacherService teacherService;

    public TeacherWeb(@Autowired TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers/all")
    public String showTeachers(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "teachers";
    }

    @GetMapping("/teachers/new-teacher")
    public String createForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "new-teacher";
    }

    @GetMapping("/teachers/{id}")
    public String showTeacherById(@PathVariable Long id, Model model) {
        Teacher p = teacherService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid teacher id : " + id));
        model.addAttribute("teacher", p);
        return "edit-teacher";
    }

    @GetMapping("/teachers/edit/{id}")
    public String updateTeacher(@PathVariable Long id, Model model) {
        Teacher teacher = teacherService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid teacher id : " + id));
        model.addAttribute("teacher", teacher);
        return "edit-teacher";
    }

    @PostMapping(value = "/teachers/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNewTeacher( @ModelAttribute Teacher teacher, BindingResult result, Model model) {
        if (result.hasErrors())
            return "new-teacher";

        teacherService.save(teacher);
        model.addAttribute("teachers", teacherService.findAll());
        return "redirect:/teachers/all";

    }

    @PostMapping(value = "/teachers/{id}")
    public String updateTeacher(@PathVariable Long id,  @ModelAttribute Teacher p, BindingResult result, Model model) throws ParseException {
        if (result.hasErrors())
            return "edit-teacher";

        Teacher teacher = teacherService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid teacher id : " + id));
        teacher.setTeacherID(p.getTeacherID());
        teacher.setFirstName(p.getFirstName());
        teacher.setLastName(p.getLastName());
        teacher.setPrimarySubject(p.getPrimarySubject());
        teacher.setSecondarySubject(p.getSecondarySubject());
        teacher.setStudentsSupervising(p.getStudentsSupervising());
        teacher.setPersonAddress(p.getPersonAddress());
        teacherService.save(teacher);
        model.addAttribute("teachers", teacherService.findAll());
        return "redirect:/teachers/all";

    }

    @GetMapping("/teachers/delete/{id}")
    public String deleteTeacher(@PathVariable Long id, Model model) {
        teacherService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid teacher id : " + id));
        teacherService.delete(id);
        model.addAttribute("teachers", teacherService.findAll());
        return "redirect:/teachers/all";
    }
}
