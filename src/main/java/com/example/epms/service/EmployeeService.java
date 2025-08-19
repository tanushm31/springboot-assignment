package com.example.epms.service;

import com.example.epms.dto.EmployeeDetailDto;
import com.example.epms.dto.EmployeeSummaryDto;
import com.example.epms.entity.*;
import com.example.epms.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.epms.spec.EmployeeSpecifications.*;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PerformanceReviewRepository performanceReviewRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           PerformanceReviewRepository performanceReviewRepository) {
        this.employeeRepository = employeeRepository;
        this.performanceReviewRepository = performanceReviewRepository;
    }

    public Page<EmployeeSummaryDto> searchEmployees(List<String> departmentsTokens,
                                                    List<String> projectTokens,
                                                    LocalDate reviewDate,
                                                    Integer minScore,
                                                    Pageable pageable) {
        Specification<Employee> spec = Specification.where(departmentNameContainsAny(departmentsTokens))
                .and(projectNameContainsAny(projectTokens))
                .and(hasReviewOnDateWithMinScore(reviewDate, minScore));

        Page<Employee> page = employeeRepository.findAll(spec, pageable);
        return page.map(this::toSummary);
    }

    @Transactional(readOnly = true)
    public EmployeeDetailDto getEmployeeDetail(Long id) {
        Employee e = employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Employee not found: " + id));
        EmployeeDetailDto dto = new EmployeeDetailDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setEmail(e.getEmail());
        dto.setDateOfJoining(e.getDateOfJoining());
        dto.setSalary(e.getSalary());

        if (e.getDepartment() != null) {
            EmployeeDetailDto.DepartmentDto d = new EmployeeDetailDto.DepartmentDto();
            d.id = e.getDepartment().getId();
            d.name = e.getDepartment().getName();
            d.budget = e.getDepartment().getBudget() == null ? null : e.getDepartment().getBudget().toPlainString();
            dto.setDepartment(d);
        }

        if (e.getManager() != null) {
            EmployeeDetailDto.ManagerDto m = new EmployeeDetailDto.ManagerDto();
            m.id = e.getManager().getId();
            m.name = e.getManager().getName();
            m.email = e.getManager().getEmail();
            dto.setManager(m);
        }

        List<EmployeeDetailDto.ProjectDto> projects = e.getEmployeeProjects().stream().map(ep -> {
            EmployeeDetailDto.ProjectDto p = new EmployeeDetailDto.ProjectDto();
            p.id = ep.getProject().getId();
            p.name = ep.getProject().getName();
            p.startDate = ep.getProject().getStartDate();
            p.endDate = ep.getProject().getEndDate();
            p.role = ep.getRole();
            p.assignedDate = ep.getAssignedDate();
            return p;
        }).sorted(Comparator.comparing(p -> p.name.toLowerCase())).collect(Collectors.toList());
        dto.setProjects(projects);

        List<EmployeeDetailDto.ReviewDto> last3 = performanceReviewRepository
                .findTop3ByEmployeeIdOrderByReviewDateDesc(e.getId())
                .stream().map(pr -> {
                    EmployeeDetailDto.ReviewDto r = new EmployeeDetailDto.ReviewDto();
                    r.id = pr.getId();
                    r.reviewDate = pr.getReviewDate();
                    r.score = pr.getScore();
                    r.reviewComments = pr.getReviewComments();
                    return r;
                }).collect(Collectors.toList());
        dto.setLast3Reviews(last3);

        return dto;
    }

    private EmployeeSummaryDto toSummary(Employee e) {
        String deptName = e.getDepartment() != null ? e.getDepartment().getName() : null;
        List<String> projects = e.getEmployeeProjects().stream()
                .map(ep -> ep.getProject().getName())
                .distinct()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
        return new EmployeeSummaryDto(e.getId(), e.getName(), e.getEmail(), deptName, projects);
    }
}
