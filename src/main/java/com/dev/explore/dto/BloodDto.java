package com.dev.explore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BloodDto {

    private String bloodGroup;

    public BloodDto() {
    }

    public BloodDto(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
