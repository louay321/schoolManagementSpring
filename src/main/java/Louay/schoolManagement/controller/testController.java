package Louay.schoolManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class testController {

    @GetMapping("/test")
    public String test(Model model,
                       @RequestParam(
                               value = "name",
                               required = false,
                               defaultValue = "Working"
                               ) String name )
    {
        model.addAttribute("name", name);
        return "test";
    }
}
