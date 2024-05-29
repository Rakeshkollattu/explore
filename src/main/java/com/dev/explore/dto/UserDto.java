package com.dev.explore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String name;
    private String email;
    private String phoneNumber;//remove phone number from user . user = 3items . update user = member data only phone number shoul de in member.
    private String password;/// complete caching and unit testing . send resumes. call ajith. commmit code. bath.hospital.maman.
    private String role;
    private String job;
    private String bloodGroup;
    private String dob;
    private String ward;
    private String booth;
    private String houseName;
    private String landmark;
    private String streetName;
    private String city;
    private String district;
    private Boolean readyForBloodDonation = true;
    private Boolean readyForWork = true;

    public UserDto() {
    }

    public UserDto(String name, String email, String phoneNumber, String password, String role, String job, String bloodGroup, String dob, String ward, String booth, String houseName, String landmark, String streetName, String city, String district, Boolean readyForBloodDonation, Boolean readyForWork) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.job = job;
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
        this.readyForWork = readyForWork;
    }
}
