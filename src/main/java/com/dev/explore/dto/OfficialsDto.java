package com.dev.explore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficialsDto {

    String job;
    String position;
    String phoneNumber;

    public OfficialsDto() {
    }

    public OfficialsDto(String job, String position, String phoneNumber) {
        this.job = job;
        this.position = position;
        this.phoneNumber = phoneNumber;
    }
}
