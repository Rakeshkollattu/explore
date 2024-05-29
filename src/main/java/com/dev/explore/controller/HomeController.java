package com.dev.explore.controller;

import com.dev.explore.dto.MottoPassDto;
import com.dev.explore.dto.UserDto;
import com.dev.explore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping({"/home", "/", ""})
    public String viewHomePage(@ModelAttribute("motopass") MottoPassDto passwordDto,
    Model model){
        return "home";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") UserDto userDto,
                          Model model) {
        userService.registerUser(userDto);
        model.addAttribute("message", "Registered Successfully.Lets LOGIN.");
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "home";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@ModelAttribute("password") MottoPassDto mottoPassDto,
                                Model model) {
        String message = userService.resetPassword(mottoPassDto);
        model.addAttribute("message", message);
        return "redirect:/home";
    }

}
