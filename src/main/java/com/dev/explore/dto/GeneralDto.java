package com.dev.explore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralDto {

    String ward;
    String booth;
    String bloodGroup;
    String dob;
    String houseName;
    String landmark;
    String streetName;
    String city;
    String district;

    public GeneralDto() {
    }

    public GeneralDto(String ward, String booth, String bloodGroup, String dob, String houseName, String landmark, String streetName, String city, String district) {
        this.ward = ward;
        this.booth = booth;
        this.bloodGroup = bloodGroup;
        this.dob = dob;
        this.houseName = houseName;
        this.landmark = landmark;
        this.streetName = streetName;
        this.city = city;
        this.district = district;
    }
}
