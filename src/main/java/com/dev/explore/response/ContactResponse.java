package com.dev.explore.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactResponse {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String bloodGroup;
    private String title;
    private String address;
    private Boolean status;

    public ContactResponse(Long id, String name, String email, String phoneNumber,
                           String bloodGroup, String title, String address, Boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bloodGroup = bloodGroup;
        this.title = title;
        this.address = address;
        this.status = status;
    }
}
