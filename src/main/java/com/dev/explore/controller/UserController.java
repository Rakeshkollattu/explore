package com.dev.explore.controller;

import com.dev.explore.dto.*;
import com.dev.explore.entity.Member;
import com.dev.explore.entity.User;
import com.dev.explore.service.MemberService;
import com.dev.explore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/userHome")
    public String viewExplorePage(Principal principal, Model model) {
        User loggedInUser = userService.findByEmail(principal.getName());

        model.addAttribute("id", loggedInUser.getId());
        model.addAttribute("name", loggedInUser.getName());
        if (loggedInUser.getRole().equals("ADMIN")) {
            model.addAttribute("admin", true);
        }

        return "pages/userHome";
    }

    @GetMapping("/adminHome")
    public String viewAdminPage(@ModelAttribute("user") UserDto userDto, Principal principal, Model model) {
        User loggedInUser = userService.findByEmail(principal.getName());
        model.addAttribute("id", loggedInUser.getId());
        model.addAttribute("name", loggedInUser.getName());
        if (loggedInUser.getRole().equals("ADMIN")) {
            model.addAttribute("admin", true);
        }
        return "pages/adminHome";
    }


    @PostMapping("/addMember")
    public String addMember(@ModelAttribute("user") UserDto userDto,
                            Principal principal, Model model) {

        userService.addUser(userDto);
        User loggedInUser = userService.findByEmail(principal.getName());
        model.addAttribute("name", loggedInUser.getName());

        if (loggedInUser.getRole().equals("ADMIN")) {
            model.addAttribute("message", "Member added successfully.");
            return "redirect:/admins?page=0";
        }
        model.addAttribute("message", "Member added successfully.");
        return "redirect:/members?page=0";
    }

    @GetMapping("/member/{email}")
    public String getMember(@PathVariable("email") String email, Principal principal, Model model) {

        //user from user table
        User loggedInUser = userService.findByEmail(principal.getName());
        Optional<Member> member = memberService.findByEmail(email);
        if (member.isPresent()) {
            List<ContactDto> contactDtoList = getContactDtos(member.get());
            model.addAttribute("contactDtoList", contactDtoList);
        }
        model.addAttribute("name", loggedInUser.getName());
        if (loggedInUser.getRole().equals("ADMIN")) {
            model.addAttribute("admin", true);
        }

        return "/pages/memberProfile";
    }

    private static List<ContactDto> getContactDtos(Member member) {
        List<ContactDto> contactDtoList = new ArrayList<>();
        ContactDto contactDto = new ContactDto(
                member.getName(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getJob(),
                member.getPosition(),
                member.getBloodGroup(),
                member.getDob(),
                member.getWard(),
                member.getBooth(),
                member.getHouseName(),
                member.getLandmark(),
                member.getStreetName(),
                member.getCity(),
                member.getDistrict(),
                member.getReadyForBloodDonation(),
                member.getReadyForCommunityWork()
        );
        contactDtoList.add(contactDto);
        return contactDtoList;
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") String id,
                          @ModelAttribute("user") UserDto userDto,
                          @ModelAttribute("official") OfficialsDto officialsDto,
                          @ModelAttribute("general") GeneralDto generalDto,
                          Model model) {

        Optional<User> user = userService.getUser(id);
        List<User> usersList = new ArrayList<>();
        User userFromDB = new User();
        if (user.isPresent()) {
            userFromDB.setId(user.get().getId());
            userFromDB.setName(user.get().getName());
            userFromDB.setEmail(user.get().getEmail());
            userFromDB.setRole(user.get().getRole());
            if (user.get().getRole().equals("ADMIN")) {
                model.addAttribute("admin", true);
            }
            usersList.add(userFromDB);
        }
        Optional<Member> memberFromDB = memberService.findByEmail(userFromDB.getEmail());
        List<Member> membersList = new ArrayList<>();
        memberFromDB.ifPresent(membersList::add);
        model.addAttribute("usersList", usersList);
        model.addAttribute("membersList", membersList);
        return "/pages/userProfile";
    }


    //    others - to be completed
    @GetMapping("/feeds")
    public String getFeeds(Model model) {
        return "/dummyPages/feeds";
    }

    @GetMapping("/services")
    public String getServices(Model model) {
        return "/dummyPages/services";
    }

    @GetMapping("/documents")
    public String getDocuments(Model model) {
        return "/dummyPages/documents";
    }

    @GetMapping("/vehicles")
    public String getVehicles(Model model) {
        return "/dummyPages/vehicles";
    }

//    @GetMapping("allUsers")
//    public ResponseEntity<Page<User>> getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
//                                                  @RequestParam(value = "size", defaultValue = "10") int size) {
//        return ResponseEntity.ok().body(userService.getAllUsers(page, size));
//    }


//    @Autowired
//    private ContactService contactService;
//
//    @GetMapping("/allContacts")
//    public ResponseEntity<Page<Member>> getAllContacts(@RequestParam(value = "page", defaultValue = "0") int page,
//                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
//        return ResponseEntity.ok().body(contactService.getAllContacts(page, size));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Member>> getContact(@PathVariable(value = "id") String id) {
//        return ResponseEntity.ok(contactService.getContact(id));
//    }
//
//    @PostMapping("/add/newContact")
//    public ResponseEntity<Member> addNewContact(@RequestBody Member member) throws SQLException, IOException {
//        return ResponseEntity.ok(contactService.addContact(member));
//    }
//
//    @PutMapping("/updateContact/{id}")
//    public ResponseEntity<Member> updateContact(@RequestBody Member member, @PathVariable(value = "id") String id) {
//        return ResponseEntity.ok(contactService.updateContact(member, id));
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteContact(@PathVariable(value = "id") String id) {
//        contactService.deleteContact(id);
//        return ResponseEntity.ok("Successfully deleted.");
//    }

}
