package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee", indexes = {
  @Index(name = "idx_employee_email", columnList = "email", unique = true)
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Employee {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "department_id", nullable = false,
    foreignKey = @ForeignKey(name = "fk_employee_department"))
  private Department department;

  private LocalDate dateOfJoining;

  private Double salary;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "manager_id",
    foreignKey = @ForeignKey(name = "fk_employee_manager"))
  private Employee manager;
}
