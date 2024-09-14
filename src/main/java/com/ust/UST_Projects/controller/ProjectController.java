package com.ust.UST_Projects.controller;

import com.ust.UST_Projects.model.Project;
import com.ust.UST_Projects.model.ProjectStatus;
import com.ust.UST_Projects.repository.Projectrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
//Controller for handling project requests and responses.
public class ProjectController {
    @Autowired
    private Projectrepo projectrepo;

    @PostMapping("/create")
    public String createPost(@RequestBody Project project, Principal principal) {
        project.setProjectStatus(ProjectStatus.INPROGRESS);
        project.setUsername(principal.getName());
        //project.setPsnumber(project.getPsnumber());
        projectrepo.save(project);
        return principal.getName() + " Your post published successfully , Required ADMIN/MODERATOR Action !";
    }

    @GetMapping("/approveAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String approveAll() {
        projectrepo.findAll().stream().filter(post -> post.getProjectStatus().equals(ProjectStatus.INPROGRESS))
                .forEach(post -> {
                    post.setProjectStatus(ProjectStatus.COMPLETED);
                    projectrepo.save(post);
                });
        return "Approved all posts !";
    }

    @GetMapping("/removeProject/{projectId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String removeProject(@PathVariable int projectId) {
        Project project = projectrepo.findById(projectId).get();
        project.setProjectStatus(ProjectStatus.REJECTED);
        projectrepo.save(project);
        return "project Rejected !!";
    }
    @GetMapping("/updateProject/{projectId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String updateProject(@PathVariable int projectId) {
        Project project = projectrepo.findById(projectId).get();
        project.setProjectStatus(ProjectStatus.COMPLETED);
        projectrepo.save(project);
        return "project updated  !!";
    }
    @GetMapping("/viewAll")
    public List<Project> viewAll(){
        return projectrepo.findAll().stream()
                .filter(post -> post.getProjectStatus().equals(ProjectStatus.INPROGRESS))
                .collect(Collectors.toList());
    }


}
