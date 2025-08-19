package com.example.epms.config;

import com.example.epms.entity.*;
import com.example.epms.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(DepartmentRepository departmentRepository,
                               EmployeeRepository employeeRepository,
                               ProjectRepository projectRepository,
                               EmployeeProjectRepository employeeProjectRepository,
                               PerformanceReviewRepository performanceReviewRepository) {
        return args -> {
            if (departmentRepository.count() > 0) return;

            Department eng = new Department();
            eng.setName("Engineering");
            eng.setBudget(new BigDecimal("1000000"));
            departmentRepository.save(eng);

            Department fin = new Department();
            fin.setName("Finance");
            fin.setBudget(new BigDecimal("500000"));
            departmentRepository.save(fin);

            Employee alice = new Employee();
            alice.setName("Alice Sharma");
            alice.setEmail("alice@corp.com");
            alice.setDepartment(eng);
            alice.setDateOfJoining(LocalDate.of(2022, 1, 10));
            alice.setSalary(new BigDecimal("1200000"));
            employeeRepository.save(alice);

            Employee bob = new Employee();
            bob.setName("Bob Kumar");
            bob.setEmail("bob@corp.com");
            bob.setDepartment(eng);
            bob.setDateOfJoining(LocalDate.of(2021, 3, 15));
            bob.setSalary(new BigDecimal("1500000"));
            bob.setManager(alice); // Alice manages Bob
            employeeRepository.save(bob);

            Employee chitra = new Employee();
            chitra.setName("Chitra Gupta");
            chitra.setEmail("chitra@corp.com");
            chitra.setDepartment(fin);
            chitra.setDateOfJoining(LocalDate.of(2020, 7, 1));
            chitra.setSalary(new BigDecimal("1100000"));
            chitra.setManager(alice); // cross-dept manager is okay in this sample
            employeeRepository.save(chitra);

            Project crm = new Project();
            crm.setName("CRM Rebuild");
            crm.setDepartment(eng);
            crm.setStartDate(LocalDate.of(2023, 1, 1));
            projectRepository.save(crm);

            Project web = new Project();
            web.setName("Website Revamp");
            web.setDepartment(eng);
            web.setStartDate(LocalDate.of(2024, 2, 1));
            projectRepository.save(web);

            Project audit = new Project();
            audit.setName("Quarterly Audit Automation");
            audit.setDepartment(fin);
            audit.setStartDate(LocalDate.of(2023, 9, 1));
            projectRepository.save(audit);

            // EmployeeProject assignments
            EmployeeProject ep1 = new EmployeeProject();
            ep1.setEmployee(alice);
            ep1.setProject(crm);
            ep1.setId(new EmployeeProjectId(alice.getId(), crm.getId()));
            ep1.setAssignedDate(LocalDate.of(2023, 1, 10));
            ep1.setRole("Lead");
            employeeProjectRepository.save(ep1);

            EmployeeProject ep2 = new EmployeeProject();
            ep2.setEmployee(bob);
            ep2.setProject(crm);
            ep2.setId(new EmployeeProjectId(bob.getId(), crm.getId()));
            ep2.setAssignedDate(LocalDate.of(2023, 2, 1));
            ep2.setRole("Backend Engineer");
            employeeProjectRepository.save(ep2);

            EmployeeProject ep3 = new EmployeeProject();
            ep3.setEmployee(bob);
            ep3.setProject(web);
            ep3.setId(new EmployeeProjectId(bob.getId(), web.getId()));
            ep3.setAssignedDate(LocalDate.of(2024, 2, 15));
            ep3.setRole("Fullstack");
            employeeProjectRepository.save(ep3);

            EmployeeProject ep4 = new EmployeeProject();
            ep4.setEmployee(chitra);
            ep4.setProject(audit);
            ep4.setId(new EmployeeProjectId(chitra.getId(), audit.getId()));
            ep4.setAssignedDate(LocalDate.of(2023, 9, 10));
            ep4.setRole("Analyst");
            employeeProjectRepository.save(ep4);

            // Reviews
            // Alice
            performanceReviewRepository.save(pr(alice, LocalDate.of(2023, 12, 31), 5, "Outstanding leadership."));
            performanceReviewRepository.save(pr(alice, LocalDate.of(2024, 06, 30), 4, "Strong delivery."));
            performanceReviewRepository.save(pr(alice, LocalDate.of(2024, 12, 31), 5, "Excellent outcomes."));
            performanceReviewRepository.save(pr(alice, LocalDate.of(2025, 06, 30), 5, "Consistently top-tier."));

            // Bob
            performanceReviewRepository.save(pr(bob, LocalDate.of(2023, 12, 31), 3, "Good, room for growth."));
            performanceReviewRepository.save(pr(bob, LocalDate.of(2024, 06, 30), 4, "Improving steadily."));
            performanceReviewRepository.save(pr(bob, LocalDate.of(2024, 12, 31), 4, "Solid performer."));
            performanceReviewRepository.save(pr(bob, LocalDate.of(2025, 06, 30), 5, "Great progress."));

            // Chitra
            performanceReviewRepository.save(pr(chitra, LocalDate.of(2023, 12, 31), 4, "Reliable and precise."));
            performanceReviewRepository.save(pr(chitra, LocalDate.of(2024, 06, 30), 4, "Consistent quality."));
            performanceReviewRepository.save(pr(chitra, LocalDate.of(2024, 12, 31), 5, "Excellent audit automation."));
            performanceReviewRepository.save(pr(chitra, LocalDate.of(2025, 06, 30), 5, "Process innovations recognized."));
        };
    }

    private static PerformanceReview pr(Employee e, LocalDate d, int score, String comments) {
        PerformanceReview p = new PerformanceReview();
        p.setEmployee(e);
        p.setReviewDate(d);
        p.setScore(score);
        p.setReviewComments(comments);
        return p;
    }
}
