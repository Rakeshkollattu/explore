package com.dev.explore.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String bloodGroup;
    private String title;
    private String address;
    private Boolean status = true;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] photoContent;
    private String profilePicture;
}
