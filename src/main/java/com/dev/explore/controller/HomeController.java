package com.dev.explore.controller;

import com.dev.explore.dto.ContactDto;
import com.dev.explore.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/explore")
public class HomeController {

    @GetMapping({"", "/"})
    public String viewHomePage( Model model) {
        return "/dashboard/Home";
    }
}
