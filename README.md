# Hiring Management System

A Java Maven Spring Boot project with UI frontend and REST API backend, plus a comprehensive API test automation framework.

---

## Project Structure

```
src/
├── main/                              ← DEVELOPMENT
│   ├── java/
│   │   ├── com/hiringms/             ← Spring Boot Application (Backend APIs)
│   │   │   ├── model/               - JPA Entities (User, Job, Application)
│   │   │   ├── repository/          - Spring Data JPA Repositories
│   │   │   ├── service/             - Business Logic Layer
│   │   │   ├── controller/          - REST API Controllers (18 APIs)
│   │   │   ├── dto/                 - Request/Response DTOs
│   │   │   ├── security/            - JWT Auth Filter & Utilities
│   │   │   └── config/              - Security & App Configuration
│   │   │
│   │   └── com/framework/           ← Test Framework Utilities
│   │       ├── helpers/             - REST helper classes
│   │       ├── utils/               - Config reader, test data utils
│   │       └── constants/           - API endpoint constants
│   │
│   └── resources/
│       ├── static/                   ← UI Pages (HTML/CSS/JS)
│       │   ├── pages/               - Login, Register, Jobs, Dashboards, Admin
│       │   ├── css/                 - Stylesheets
│       │   └── js/                  - JavaScript
│       └── application.properties    - App configuration
│
├── test/                              ← TESTING (API Automation)
│   ├── java/
│   │   └── com/testcases/
│   │       ├── base/                - BaseTest with common setup
│   │       ├── api/                 - API test classes (Auth, User, Job, Application, Admin)
│   │       ├── smoke/               - Quick sanity tests
│   │       ├── regression/          - End-to-end regression tests
│   │       └── runners/             - Custom test runners
│   │
│   └── resources/
│       ├── config/                  - Environment configs (qa.properties)
│       ├── testdata/                - Test data JSONs
│       └── payloads/                - Request payload templates
│
├── pom.xml                           - Maven dependencies
└── testng.xml                        - TestNG suite configuration
```

---

## UI Pages

| Page | URL | Description |
|------|-----|-------------|
| Home | `/` | Landing page |
| Login | `/pages/login.html` | User login form |
| Register | `/pages/register.html` | New user registration |
| Job Listings | `/pages/jobs.html` | Browse & search jobs |
| Apply Job | `/pages/apply.html` | Job application form |
| Recruiter Dashboard | `/pages/recruiter-dashboard.html` | Post jobs, view applicants |
| Candidate Dashboard | `/pages/candidate-dashboard.html` | Track applications |
| Admin Panel | `/pages/admin.html` | Manage users & system |

---

## API Endpoints

| # | API Name | Method | URL |
|---|----------|--------|-----|
| 1 | Register User | POST | `/api/auth/register` |
| 2 | Login User | POST | `/api/auth/login` |
| 3 | Get User Profile | GET | `/api/users/profile` |
| 4 | Update User Profile | PUT | `/api/users/profile` |
| 5 | Create Job | POST | `/api/jobs` |
| 6 | Get All Jobs | GET | `/api/jobs` |
| 7 | Get Single Job | GET | `/api/jobs/:id` |
| 8 | Update Job | PUT | `/api/jobs/:id` |
| 9 | Delete Job | DELETE | `/api/jobs/:id` |
| 10 | Apply for Job | POST | `/api/applications` |
| 11 | Get My Applications | GET | `/api/applications/my` |
| 12 | Get Applicants for Job | GET | `/api/applications/job/:jobId` |
| 13 | Update Application Status | PUT | `/api/applications/:id/status` |
| 14 | Get All Users | GET | `/api/admin/users` |
| 15 | Delete User | DELETE | `/api/admin/users/:id` |
| 16 | Search Jobs | GET | `/api/jobs/search?keyword=react` |
| 17 | Filter Jobs by Location | GET | `/api/jobs/filter?location=Pune` |
| 18 | Upload Resume | POST | `/api/upload/resume` |

---

## Tech Stack

### Development
- Java 17
- Spring Boot 3.2.5
- Spring Security + JWT
- Spring Data JPA + H2 Database
- Thymeleaf + Static HTML/CSS/JS

### Testing
- TestNG 7.9.0
- Rest Assured 5.4.0
- ExtentReports 5.1.1
- Log4j2

---

## How to Run

### Start the Application
```bash
mvn spring-boot:run
```
App will be available at: `http://localhost:8080`

### Run Tests
```bash
# Run all tests
mvn clean test

# Run specific test suite
mvn test -DsuiteXmlFile=testng.xml
```

### Access H2 Console
URL: `http://localhost:8080/h2-console`
JDBC URL: `jdbc:h2:mem:hiringdb`
Username: `sa` | Password: (empty)

