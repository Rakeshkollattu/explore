package com.dev.explore.controller;

import com.dev.explore.dto.UserDto;
import com.dev.explore.entity.User;
import com.dev.explore.service.MemberService;
import com.dev.explore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserService userService;

    @GetMapping({"", "/"})
    public String viewHomePage(@RequestParam(value = "page") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size,
                               @ModelAttribute("user") UserDto userDto,
                               Principal principal, Model model) {

        User loggedInUser = userService.findByEmail(principal.getName());
        Page<User> usersList = userService.getALLAdmins(page, size);


        if (loggedInUser.getRole().equals("ADMIN")) {
            model.addAttribute("admin", true);
        }
        int totalPages = usersList.getTotalPages();
        long totalItems = usersList.getTotalElements();
        int next = page + 1;
        int previous = 0;
        if (page != 0) {
            boolean havePreviousPage = true;
            model.addAttribute("havePreviousPage",havePreviousPage);
        }
        if (next == totalPages-1) {
            boolean noMorePages = true;
            model.addAttribute("noMorePages",noMorePages);
        }

        model.addAttribute("usersList", usersList);
        model.addAttribute("currentPage", page);
        model.addAttribute("name", loggedInUser.getName());
        model.addAttribute("nextPage", next);
        model.addAttribute("previousPage", previous);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);

        return "/pages/admins";
    }


}
