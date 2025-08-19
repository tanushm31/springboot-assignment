package com.example.epms.spec;

import com.example.epms.entity.*;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public class EmployeeSpecifications {

    public static Specification<Employee> departmentNameContainsAny(List<String> tokens) {
        return (root, query, cb) -> {
            if (tokens == null || tokens.isEmpty()) return cb.conjunction();
            Join<Employee, Department> dept = root.join("department", JoinType.LEFT);
            Predicate or = cb.disjunction();
            for (String t : tokens) {
                or = cb.or(or, cb.like(cb.lower(dept.get("name")), "%" + t.toLowerCase() + "%"));
            }
            query.distinct(true);
            return or;
        };
    }

    public static Specification<Employee> projectNameContainsAny(List<String> tokens) {
        return (root, query, cb) -> {
            if (tokens == null || tokens.isEmpty()) return cb.conjunction();
            Join<Employee, EmployeeProject> ep = root.join("employeeProjects", JoinType.LEFT);
            Join<EmployeeProject, Project> proj = ep.join("project", JoinType.LEFT);
            Predicate or = cb.disjunction();
            for (String t : tokens) {
                or = cb.or(or, cb.like(cb.lower(proj.get("name")), "%" + t.toLowerCase() + "%"));
            }
            query.distinct(true);
            return or;
        };
    }

    public static Specification<Employee> hasReviewOnDateWithMinScore(LocalDate reviewDate, Integer minScore) {
        return (root, query, cb) -> {
            if (reviewDate == null) return cb.conjunction();
            Join<Employee, PerformanceReview> pr = root.join("performanceReviews", JoinType.LEFT);
            Predicate dateEq = cb.equal(pr.get("reviewDate"), reviewDate);
            if (minScore != null) {
                Predicate scoreGe = cb.greaterThanOrEqualTo(pr.get("score"), minScore);
                query.distinct(true);
                return cb.and(dateEq, scoreGe);
            } else {
                query.distinct(true);
                return dateEq;
            }
        };
    }
}
