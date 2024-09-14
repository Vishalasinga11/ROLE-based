package com.ust.UST_Projects.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int projectId;
    private String projectName;
    private String projectDescription;
    @Enumerated(value=EnumType.STRING)
    private ProjectStatus projectStatus;
    private String username;
    private String projectLink;
    private String psnumber;
}
