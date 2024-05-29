package com.dev.explore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MottoPassDto {

    private String email;
    private String motopass1;

    public MottoPassDto() {
    }

    public MottoPassDto(String email, String motopass1) {
        this.email = email;
        this.motopass1 = motopass1;
    }
}
