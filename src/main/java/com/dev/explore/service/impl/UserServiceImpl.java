package com.dev.explore.service.impl;


import com.dev.explore.dto.MottoPassDto;
import com.dev.explore.dto.UserDto;
import com.dev.explore.entity.Member;
import com.dev.explore.entity.User;
import com.dev.explore.repo.MemberRepository;
import com.dev.explore.repo.UserRepository;
import com.dev.explore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepo;

    private final MemberRepository contactRepo;

    public UserServiceImpl(UserRepository userRepo, MemberRepository contactRepo) {
        this.userRepo = userRepo;
        this.contactRepo = contactRepo;
    }

    @Override
    public Page<User> getAllUsers(int pageNumber, int pageSize) {
        return userRepo.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("name")));
    }

    @Override
    public Page<User> getALLAdmins(int pageNumber, int pageSize) {
        return userRepo.findAllAdmins(PageRequest.of(pageNumber,pageSize,Sort.by("name")),"ADMIN");
    }

    @Override
    public Optional<User> getUser(String id) {
        return userRepo.findById(id);
    }

    @Override
    public void addUser(UserDto userDto) {
        userRepo.save(new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), "USER"));

        //saving member details
        Member member = new Member();
        member.setName(userDto.getName());
        member.setEmail(userDto.getEmail());
        member.setPhoneNumber(userDto.getPhoneNumber());
        member.setJob(userDto.getJob());
        member.setPosition(userDto.getRole());
        member.setBloodGroup(userDto.getBloodGroup());
        member.setDob(userDto.getDob());
        member.setWard(userDto.getWard());
        member.setBooth(userDto.getBooth());
        member.setHouseName(userDto.getHouseName());
        member.setLandmark(userDto.getLandmark());
        member.setStreetName(userDto.getStreetName());
        member.setCity(userDto.getCity());
        member.setDistrict(userDto.getDistrict());
        member.setReadyForBloodDonation(true);
        member.setReadyForCommunityWork(true);
        contactRepo.save(member);

    }

    @Override
    public void registerUser(UserDto userDto) {

        //saving user
        userRepo.save(new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), "USER"));

        //saving member details
        Member member = new Member();
        member.setName(userDto.getName());
        member.setEmail(userDto.getEmail());
        member.setPhoneNumber(userDto.getPhoneNumber());
        member.setJob(userDto.getJob());
        member.setPosition(userDto.getRole());
        member.setBloodGroup(userDto.getBloodGroup());
        member.setDob(userDto.getDob());
        member.setWard(userDto.getWard());
        member.setBooth(userDto.getBooth());
        member.setHouseName(userDto.getHouseName());
        member.setLandmark(userDto.getLandmark());
        member.setStreetName(userDto.getStreetName());
        member.setCity(userDto.getCity());
        member.setDistrict(userDto.getDistrict());
        member.setReadyForBloodDonation(true);
        member.setReadyForCommunityWork(true);
        contactRepo.save(member);
    }

    @Override
    public User updateUpdate(User user, String id) {
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public String resetPassword(MottoPassDto mottoPassDto) {
        User userFromDB = userRepo.findByEmail(mottoPassDto.getEmail());
        User updatedUser = new User();
        updatedUser.setId(userFromDB.getId());
        updatedUser.setName(userFromDB.getName());
        updatedUser.setEmail(userFromDB.getEmail());
        updatedUser.setPassword(passwordEncoder.encode(mottoPassDto.getMotopass1()));
        updatedUser.setRole(userFromDB.getRole());
        userRepo.save(updatedUser);
        return "password changed successfully.";
    }
}
