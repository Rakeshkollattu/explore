package com.dev.explore.controller;

import com.dev.explore.dto.*;
import com.dev.explore.entity.Member;
import com.dev.explore.entity.User;
import com.dev.explore.service.MemberService;
import com.dev.explore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserService userService;

    //page
    @GetMapping({"", "/"})
    public String viewPage(@ModelAttribute("user") UserDto userDto) {
        return "/pages/members";
    }

    //update
    @PostMapping("/updateMemberOfficials/{id}")
//    @Cacheable(value = "directory")
    public String updateMemberOfficials(@PathVariable("id") String id,
                                        @ModelAttribute("official") OfficialsDto officialsDto,
                                        Principal principal, Model model) {
        Optional<Member> member = memberService.updateMemberOfficials(officialsDto, id);
        if (member.isPresent()) {
            List<Member> membersList = new ArrayList<>();
            membersList.add(member.get());
            model.addAttribute("membersList", membersList);
        }
        return "redirect:/user/{id}(id=${id})";
    }

    //update
    @PostMapping("/updateMemberGenerals/{id}")
//    @CachePut(value = "directory")
    public String updateMemberGenerals(@PathVariable("id") String id,
                                       @ModelAttribute("general") GeneralDto generalDto,
                                       Principal principal, Model model) {
        Optional<Member> member = memberService.updateMemberGenerals(generalDto, id);
        if (member.isPresent()) {
            List<Member> membersList = new ArrayList<>();
            membersList.add(member.get());
            model.addAttribute("membersList", membersList);
        }
        return "redirect:/user/{id}(id=${id})";
    }

    //member profile
    @GetMapping("/details/{email}")
//    @Cacheable(value = "directory")
    public String getMember(@PathVariable("email") String email, Principal principal, Model model) {

        User loggedInUser = getIsAdmin(principal, model);
        Optional<Member> member = memberService.findByEmail(email);

        if (member.isPresent()) {
            List<Member> memberList = new ArrayList<Member>();
            memberList.add(member.get());
            model.addAttribute("memberList", memberList);
        }
        return "/pages/memberProfile";
    }

    private User getIsAdmin(Principal principal, Model model) {
        User loggedInUser = userService.findByEmail(principal.getName());
        if (loggedInUser.getRole().equals("ADMIN")) {
            model.addAttribute("admin", true);
        }
        return loggedInUser;
    }
}
