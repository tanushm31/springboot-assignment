package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "project", uniqueConstraints = {
  @UniqueConstraint(name = "uk_project_name", columnNames = "name")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Project {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  private LocalDate startDate;
  private LocalDate endDate;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "department_id", nullable = false,
    foreignKey = @ForeignKey(name = "fk_project_department"))
  private Department department;
}
