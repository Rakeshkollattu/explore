package com.dev.explore.dto;

import com.dev.explore.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactDto {

    private String name;
    private String email;
    private String phoneNumber;
    private String job;
    private String role;
    private String bloodGroup;
    private String dob;
    private String ward;
    private String booth;
    private String houseName;
    private String landmark;
    private String streetName;
    private String city;
    private String district;
    private Boolean readyForBloodDonation;
    private Boolean readyForCommunityWork;

    public ContactDto(String name, String email, String phoneNumber,
                      String job, String role, String bloodGroup,
                      String dob, String ward, String booth, String houseName,
                      String landmark, String streetName, String city,
                      String district, Boolean readyForBloodDonation,
                      Boolean readyForCommunityWork) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.job = job;
        this.role = role;
        this.bloodGroup = bloodGroup;
        this.dob = dob;
        this.ward = ward;
        this.booth = booth;
        this.houseName = houseName;
        this.landmark = landmark;
        this.streetName = streetName;
        this.city = city;
        this.district = district;
        this.readyForBloodDonation = readyForBloodDonation;
        this.readyForCommunityWork = readyForCommunityWork;
    }

    public Member getContact() {
        Member member = new Member();
        member.setName(this.name);
        member.setEmail(this.email);
        member.setPhoneNumber(this.phoneNumber);
        member.setJob(this.getJob());
        member.setPosition(this.role);
        member.setBloodGroup(this.bloodGroup);
        member.setDob(this.dob);
        member.setWard(this.ward);
        member.setBooth(this.booth);
        member.setHouseName(this.houseName);
        member.setLandmark(this.landmark);
        member.setStreetName(this.streetName);
        member.setCity(this.city);
        member.setDistrict(this.district);
        member.setReadyForBloodDonation(this.readyForBloodDonation);
        member.setReadyForCommunityWork(this.readyForCommunityWork);
        return member;
    }
}
