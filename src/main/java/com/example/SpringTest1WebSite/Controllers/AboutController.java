package com.example.SpringTest1WebSite.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("about", "About us");
        return "about-main";

        /**adding comment into test branch
         *
         */
    }
}

