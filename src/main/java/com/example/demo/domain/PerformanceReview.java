package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "performance_review")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PerformanceReview {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false,
    foreignKey = @ForeignKey(name = "fk_review_employee"))
  private Employee employee;

  private LocalDate reviewDate;

  private Integer score;

  @Column(length = 2000)
  private String reviewComments;
}
