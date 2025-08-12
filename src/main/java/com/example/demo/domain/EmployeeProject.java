package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_project")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeProject {

  @EmbeddedId
  private EmployeeProjectId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("employeeId")
  @JoinColumn(name = "employee_id",
    foreignKey = @ForeignKey(name = "fk_emp_proj_employee"))
  private Employee employee;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("projectId")
  @JoinColumn(name = "project_id",
    foreignKey = @ForeignKey(name = "fk_emp_proj_project"))
  private Project project;

  private LocalDate assignedDate;

  @Column(length = 100)
  private String role;
}
