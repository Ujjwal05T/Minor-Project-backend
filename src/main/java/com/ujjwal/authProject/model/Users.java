package com.ujjwal.authProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobdetails_seq")
    @SequenceGenerator(name = "jobdetails_seq", sequenceName = "jobdetails_seq", allocationSize = 1)
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;

}
