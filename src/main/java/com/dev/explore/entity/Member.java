package com.dev.explore.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "contacts")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
//    @UuidGenerator
    @Column(name = "id", unique = true, updatable = false)
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String job;
    private String position;
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

//    @Lob
//    @Column(columnDefinition = "longblob")
//    private byte[] photoContent;
//    private String profilePicture;
}
