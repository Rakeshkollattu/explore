package com.dev.explore.controller;

import com.dev.explore.entity.Member;
import com.dev.explore.entity.User;
import com.dev.explore.service.MemberService;
import com.dev.explore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/booths")
public class BoothController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserService userService;

    Page<Member> globalMembersList = null;

    //page
    @GetMapping({"", "/"})
    public String memberPage(@RequestParam(value = "page") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size,
                             Principal principal, Model model) {
        globalMembersList = memberService.getAllMembers(page, size);
        User loggedInUser = getIsAdmin(principal, model);
        int next = page + 1;
        int previous = 0;
        model.addAttribute("filter", "false");
        model.addAttribute("membersList", globalMembersList);
        model.addAttribute("booths",memberService.getBooths());
        model.addAttribute("currentPage", page);
        model.addAttribute("nextPage", next);
        model.addAttribute("previousPage", previous);
        model.addAttribute("totalPages", globalMembersList.getTotalPages());
        model.addAttribute("hasPrevious", globalMembersList.hasPrevious());
        model.addAttribute("isLast", globalMembersList.isLast());
        model.addAttribute("totalItems", globalMembersList.getTotalElements());
        return "/pages/booths";
    }


    //search
    @RequestMapping("/search")
    public String search(@RequestParam(value = "page") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size,
                         @RequestParam(value = "keyword") String keyword,
                         Principal principal, Model model) {

        globalMembersList = memberService.findByBoothContainingStartsWithIgnoreCase(keyword, page, size);
        User loggedInUser = getIsAdmin(principal, model);
        int next = page + 1;
        int previous = 0;
        model.addAttribute("filter", "true");
        model.addAttribute("keyword", keyword);
        model.addAttribute("membersList", globalMembersList);
        model.addAttribute("currentPage", page);
        model.addAttribute("nextPage", next);
        model.addAttribute("previousPage", previous);
        model.addAttribute("totalPages", globalMembersList.getTotalPages());
        model.addAttribute("hasPrevious", globalMembersList.hasPrevious());
        model.addAttribute("isLast", globalMembersList.isLast());
        model.addAttribute("totalItems", globalMembersList.getTotalElements());

        return "/pages/booths";
    }


    //worker profile
    @GetMapping("/details/{email}")
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


    //    @Cacheable(value = "directory")
    private User getIsAdmin(Principal principal, Model model) {
        User loggedInUser = userService.findByEmail(principal.getName());
        if (loggedInUser.getRole().equals("ADMIN")) {
            model.addAttribute("admin", true);
        }
        return loggedInUser;
    }

}
