package com.ujjwal.authProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="jobdetails")
public class JobDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobdetails_seq")
    @SequenceGenerator(name = "jobdetails_seq", sequenceName = "jobdetails_seq", allocationSize = 1)
    private int id;
    @Column(name="apply_link")
    @Lob
    private String apply_link;
    @Column(name="title")
    private String title;

    @Column(name="last_date")
    private Date last_date;

    @Column(name="company_name")
    private String company_name;
    @Lob
    @Column(name="description")
    private String description;
    @Column(name="salary")
    private int salary;

}
