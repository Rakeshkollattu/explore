package com.dev.explore.controller;

import com.dev.explore.dto.ContactDto;
import com.dev.explore.entity.Contact;
import com.dev.explore.service.ContactService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/bloodDonation")
public class BloodDonationController {

    @Autowired
    private ContactService contactService;

    @GetMapping({"", "/"})
    public String viewHomePage(@RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size, Model model) {
        Page<Contact> contactList = contactService.getAllContacts(page, size);
        model.addAttribute("contactList", contactList);
        model.addAttribute("contactDto", new ContactDto());
        return "/dashboard/BloodDonation";
    }

    @PostMapping("/newContact")
    public String newContact(@ModelAttribute("contactDto") ContactDto contactDto, Model model) {
        model.addAttribute("contactDto", contactDto);
        contactService.addContact(contactDto.getContact());
        return "redirect:/dashboard/bloodDonation";
    }

    @PostMapping("/updateContact/{id}")
    public String updateContact(@ModelAttribute("contactDto") ContactDto contactDto,
                                @PathVariable("id") String id, Model model) {
        model.addAttribute("contactDto", contactDto);
        contactService.updateContact(contactDto.getContact(), parseLong(id));
        return "redirect:/dashboard//bloodDonation";
    }

    @GetMapping("/showImage/{id}")
    public void showImage(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        Optional<Contact> contact = contactService.getContact(parseLong(id));
        if (contact.isPresent()) {
            response.setContentType("image/jpeg, image/jpg, image/png");
            response.getOutputStream().write(contact.get().getPhotoContent());
            response.getOutputStream().close();
        }
    }

    ///////////////////////////////////////////

    //old
//    @GetMapping("/addNewContact")
//    public String addNewContact(Model model) {
//        Contact contact = new Contact();
//        model.addAttribute(contact);
//        return "AddContact";
//    }


    @PostMapping("/upload")
    public String updateProfiePicture(@RequestParam("file") MultipartFile file,
                                      @RequestParam("id") String id,
                                      Model model) throws SQLException, IOException {
        contactService.updateProfilepicture(file.getOriginalFilename(), file.getBytes(), parseLong(id));
        return "/dashboard/BloodDonation";
    }

    @GetMapping("/downloadFile")
    public void downloadFile(@Param("id") Long id, HttpServletResponse response) throws IOException {
        Optional<Contact> contact = contactService.getContact(id);
        if (contact.isPresent()) {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename = " + contact.get().getProfilePicture());
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(contact.get().getPhotoContent());
            outputStream.close();
        }
    }
}
