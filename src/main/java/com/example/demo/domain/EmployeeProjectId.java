package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class EmployeeProjectId implements Serializable {
  @Column(name = "employee_id")
  private Long employeeId;

  @Column(name = "project_id")
  private Long projectId;
}
