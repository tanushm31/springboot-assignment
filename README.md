# EPMS Assignment (Spring Boot)

This is a working implementation of the **Employee Performance Management System** API per the assignment.

## Run

```bash
./mvnw spring-boot:run   # if you have mvnw; otherwise
mvn spring-boot:run
```

JDK 17+ and Maven 3.9+ recommended.

H2 is in-memory; no setup needed. Visit `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:epms`).

## API

### 1) List Employees with Filters
`GET /api/employees`

Query params (all optional, combine freely):
- `departments`: comma-separated tokens matched with **contains** on department name (case-insensitive).
- `projects`: comma-separated tokens matched with **contains** on project name (case-insensitive).
- `reviewDate`: `YYYY-MM-DD` — restrict to employees who have a review on this date.
- `score`: integer — when used with `reviewDate`, keeps employees whose score **>= score** on that date.
- `page`, `size`, `sort` — standard Spring pagination, e.g. `sort=name,asc`.

Example:
```
/api/employees?departments=engi,fin&projects=crm,web&reviewDate=2024-12-31&score=4&page=0&size=10&sort=name,asc
```

Returns `Page<EmployeeSummaryDto>`:
```json
{
  "content": [
    {
      "id": 1, "name": "Alice", "email": "alice@corp.com",
      "departmentName": "Engineering",
      "projectNames": ["CRM Rebuild","Website Revamp"]
    }
  ],
  "totalElements": 1,
  "totalPages": 1, "number": 0, "size": 10
}
```

### 2) Detailed Employee by ID
`GET /api/employees/{id}`

Returns department, current manager (if any), projects with role/assignedDate,
and last **3** performance reviews (most recent first).

## Sample Data
The app seeds data at startup (see `DataSeeder`). Tweak there if you want more.
