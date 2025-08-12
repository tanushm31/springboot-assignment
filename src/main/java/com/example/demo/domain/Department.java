package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "department", uniqueConstraints = {
  @UniqueConstraint(name = "uk_department_name", columnNames = "name")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Department {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  private Double budget;
}
