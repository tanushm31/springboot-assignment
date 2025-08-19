package com.example.epms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EmployeeDetailDto {
    public static class DepartmentDto {
        public Long id;
        public String name;
        public String budget;
    }
    public static class ManagerDto {
        public Long id;
        public String name;
        public String email;
    }
    public static class ProjectDto {
        public Long id;
        public String name;
        public LocalDate startDate;
        public LocalDate endDate;
        public String role;
        public LocalDate assignedDate;
    }
    public static class ReviewDto {
        public Long id;
        public LocalDate reviewDate;
        public Integer score;
        public String reviewComments;
    }

    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfJoining;
    private BigDecimal salary;
    private DepartmentDto department;
    private ManagerDto manager;
    private List<ProjectDto> projects;
    private List<ReviewDto> last3Reviews;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }
    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    public DepartmentDto getDepartment() { return department; }
    public void setDepartment(DepartmentDto department) { this.department = department; }
    public ManagerDto getManager() { return manager; }
    public void setManager(ManagerDto manager) { this.manager = manager; }
    public List<ProjectDto> getProjects() { return projects; }
    public void setProjects(List<ProjectDto> projects) { this.projects = projects; }
    public List<ReviewDto> getLast3Reviews() { return last3Reviews; }
    public void setLast3Reviews(List<ReviewDto> last3Reviews) { this.last3Reviews = last3Reviews; }
}
