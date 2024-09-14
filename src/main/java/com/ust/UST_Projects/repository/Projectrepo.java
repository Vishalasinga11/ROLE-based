package com.ust.UST_Projects.repository;

import com.ust.UST_Projects.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Projectrepo extends JpaRepository<Project,Integer> {
}
