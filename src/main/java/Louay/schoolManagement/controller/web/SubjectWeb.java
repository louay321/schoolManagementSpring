package Louay.schoolManagement.controller.web;

import Louay.schoolManagement.domain.Subject;
import Louay.schoolManagement.service.SubjectService;
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
public class SubjectWeb {
    
    private static final Logger LOGGER = LogManager.getLogger();

    private SubjectService subjectService;

    public SubjectWeb(@Autowired SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subjects/all")
    public String showSubjects(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "subjects";
    }

    @GetMapping("/subjects/new-subject")
    public String createForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "new-subject";
    }

    @GetMapping("/subjects/{id}")
    public String showSubjectById(@PathVariable Long id, Model model) {
        Subject v = subjectService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid subject id : " + id));
        model.addAttribute("subject", v);
        return "edit-subject";
    }

    @GetMapping("/subjects/edit/{id}")
    public String updateSubject(@PathVariable Long id, Model model) {
        Subject subject = subjectService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid subject id : " + id));
        model.addAttribute("subject", subject);
        return "edit-subject";
    }

    @PostMapping(value = "/subjects/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNewSubject( @ModelAttribute Subject subject, BindingResult result, Model model) {
        if (result.hasErrors())
            return "new-subject";

        subjectService.save(subject);
        model.addAttribute("subjects", subjectService.findAll());
        return "redirect:/subjects/all";

    }

    @PostMapping(value = "/subjects/{id}")
    public String updateSubject(@PathVariable Long id,  @ModelAttribute Subject v, BindingResult result, Model model) {
        if (result.hasErrors())
            return "edit-subject";

        Subject subject = subjectService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid subject id : " + id));
        subject.setSubjectID(v.getSubjectID());
        subject.setMaxStudents(v.getMaxStudents());
        subject.setCredits(v.getCredits());
        subjectService.save(subject);
        model.addAttribute("subjects", subjectService.findAll());
        return "redirect:/subjects/all";

    }

    @GetMapping("/subjects/delete/{id}")
    public String deleteSubject(@PathVariable Long id, Model model) {
        subjectService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid subject id : " + id));
        subjectService.delete(id);
        model.addAttribute("subjects", subjectService.findAll());
        return "redirect:/subjects/all";
    }
}
