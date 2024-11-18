package com.ujjwal.authProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="jobdetails")
public class JobDetails {

    @Id
    private int id;
    @Column(name="applylink")
    private String applyLink;
    private String title;
    @Lob
    private String description;

}
