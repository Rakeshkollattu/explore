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
@RequestMapping("/bloodDonor")
public class DonorController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserService userService;

    Page<Member> globalDonorList = null;

    //page
    @GetMapping({"", "/"})
    public String viewPage(@RequestParam(value = "page") int page,
                           @RequestParam(value = "size", defaultValue = "10") int size,
                           Principal principal, Model model) {

        globalDonorList = memberService.getAllMembers(page, size);
        User loggedInUser = getIsAdmin(principal, model);
        int next = page + 1;
        int previous = 0;
        model.addAttribute("filter", "false");
        model.addAttribute("donorList", globalDonorList);
        model.addAttribute("groups", memberService.getBloodGroups());
        model.addAttribute("currentPage", page);
        model.addAttribute("nextPage", next);
        model.addAttribute("previousPage", previous);
        model.addAttribute("totalPages", globalDonorList.getTotalPages());
        model.addAttribute("hasPrevious", globalDonorList.hasPrevious());
        model.addAttribute("isLast", globalDonorList.isLast());
        model.addAttribute("totalItems", globalDonorList.getTotalElements());

        return "/pages/BloodDonor";
    }

    //search
    @RequestMapping("/search")
    public String search(@RequestParam(value = "page") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size,
                         @RequestParam(value = "keyword") String keyword,
                         Principal principal, Model model) {

        globalDonorList = memberService.findByBloodGroupContainingStartsWithIgnoreCase(keyword, page, size);
        User loggedInUser = getIsAdmin(principal, model);
        int next = page + 1;
        int previous = 0;
        model.addAttribute("filter", "true");
        model.addAttribute("keyword", keyword);
        model.addAttribute("donorList", globalDonorList);
        model.addAttribute("groups", memberService.getBloodGroups());
        model.addAttribute("currentPage", page);
        model.addAttribute("nextPage", next);
        model.addAttribute("previousPage", previous);
        model.addAttribute("totalPages", globalDonorList.getTotalPages());
        model.addAttribute("hasPrevious", globalDonorList.hasPrevious());
        model.addAttribute("isLast", globalDonorList.isLast());
        model.addAttribute("totalItems", globalDonorList.getTotalElements());

        return "/pages/BloodDonor";
    }

    //worker profile
    @GetMapping("/details/{email}")
    public String getDonor(@PathVariable("email") String email, Principal principal, Model model) {

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


//  image handling.

//    @GetMapping("/showImage/{id}")
//    public void showImage(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
//        Optional<Contact> contact = contactService.getContact(id);
//        if (contact.isPresent()) {
//            response.setContentType("image/jpeg, image/jpg, image/png");
//            response.getOutputStream().write(contact.get().getPhotoContent());
//            response.getOutputStream().close();
//        }
//    }

//    @PostMapping("/upload")
//    public String updateProfiePicture(@RequestParam("file") MultipartFile file,
//                                      @RequestParam("id") String id,
//                                      Model model) throws SQLException, IOException {
//        contactService.updateProfilepicture(file.getOriginalFilename(), file.getBytes(), id);
//        return "/dashboard/BloodDonation";
//    }

//    @GetMapping("/downloadFile")
//    public void downloadFile(@Param("id") String id, HttpServletResponse response) throws IOException {
//        Optional<Contact> contact = contactService.getContact(id);
//        if (contact.isPresent()) {
//            response.setContentType("application/octet-stream");
//            response.setHeader("Content-Disposition", "attachment;filename = " + contact.get().getProfilePicture());
//            ServletOutputStream outputStream = response.getOutputStream();
//            outputStream.write(contact.get().getPhotoContent());
//            outputStream.close();
//        }
//    }
}
