package com.example.epms.repository;

import com.example.epms.entity.EmployeeProject;
import com.example.epms.entity.EmployeeProjectId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, EmployeeProjectId> {}
