package com.example.epms.dto;

import java.util.List;

public class EmployeeSummaryDto {
    private Long id;
    private String name;
    private String email;
    private String departmentName;
    private List<String> projectNames;

    public EmployeeSummaryDto() {}

    public EmployeeSummaryDto(Long id, String name, String email, String departmentName, List<String> projectNames) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentName = departmentName;
        this.projectNames = projectNames;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public List<String> getProjectNames() { return projectNames; }
    public void setProjectNames(List<String> projectNames) { this.projectNames = projectNames; }
}
