package com.example.epms.controller;

import com.example.epms.dto.EmployeeDetailDto;
import com.example.epms.dto.EmployeeSummaryDto;
import com.example.epms.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Page<EmployeeSummaryDto> listEmployees(
            @RequestParam(value = "departments", required = false) String departments,
            @RequestParam(value = "projects", required = false) String projects,
            @RequestParam(value = "reviewDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reviewDate,
            @RequestParam(value = "score", required = false) Integer minScore,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        List<String> deptTokens = splitTokens(departments);
        List<String> projTokens = splitTokens(projects);
        return employeeService.searchEmployees(deptTokens, projTokens, reviewDate, minScore, pageable);
    }

    @GetMapping("/{id}")
    public EmployeeDetailDto getDetail(@PathVariable Long id) {
        return employeeService.getEmployeeDetail(id);
    }

    private List<String> splitTokens(String csv) {
        if (csv == null || csv.isBlank()) return List.of();
        return Arrays.stream(csv.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNotFound(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
