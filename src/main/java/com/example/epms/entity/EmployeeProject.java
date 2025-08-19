package com.example.epms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_projects")
public class EmployeeProject {
    @EmbeddedId
    private EmployeeProjectId id = new EmployeeProjectId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    private LocalDate assignedDate;
    private String role;

    // getters/setters
    public EmployeeProjectId getId() { return id; }
    public void setId(EmployeeProjectId id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public LocalDate getAssignedDate() { return assignedDate; }
    public void setAssignedDate(LocalDate assignedDate) { this.assignedDate = assignedDate; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
