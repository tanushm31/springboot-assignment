package com.example.demo.repo;

import com.example.demo.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceReviewRepo extends JpaRepository<PerformanceReview, Long> {
}
