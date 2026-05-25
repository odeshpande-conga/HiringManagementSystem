# 💼 HireFlow — Hiring Management System

A modern, full-stack hiring management platform built with **Spring Boot 3** and a responsive frontend UI.

---

## 🏗️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Spring Boot 3.2, Spring Security, Spring Data JPA |
| Auth | JWT (jjwt 0.12.5) |
| Database | H2 (dev) / Azure SQL Server (prod) |
| Frontend | Vanilla JS, HTML5, CSS3 (modern UI) |
| Build | Maven, Java 17+ |
| Deployment | Azure App Service / Docker |

---

## 🚀 Quick Start

```bash
# Clone and run (requires Java 17+)
mvn spring-boot:run
```

Open **http://localhost:5000** in your browser.

---

## 📋 Features

### For Candidates
- Register & login with JWT auth
- Browse available job listings
- Apply for jobs with cover letter
- Track application status (Pending → Shortlisted → Accepted)

### For Recruiters
- Post new job openings
- Review candidate applications
- Update application status (shortlist, accept, reject)

### For Admins
- View all users on the platform
- Delete users
- Monitor platform activity

---

## 🔌 API Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/api/auth/register` | Register a user | ❌ |
| POST | `/api/auth/login` | Login & get token | ❌ |
| GET | `/api/users/profile` | Get user profile | ✅ |
| PUT | `/api/users/profile` | Update profile | ✅ |
| GET | `/api/jobs` | List all active jobs | ✅ |
| POST | `/api/jobs` | Create a job (recruiter) | ✅ |
| GET | `/api/jobs/{id}` | Get job details | ✅ |
| PUT | `/api/jobs/{id}` | Update a job | ✅ |
| DELETE | `/api/jobs/{id}` | Delete a job | ✅ |
| POST | `/api/applications` | Apply for a job | ✅ |
| GET | `/api/applications/my` | My applications | ✅ |
| GET | `/api/applications/job/{id}` | Applicants for job | ✅ |
| PUT | `/api/applications/{id}/status` | Update status | ✅ |
| GET | `/api/admin/users` | All users (admin) | ✅ ADMIN |
| DELETE | `/api/admin/users/{id}` | Delete user (admin) | ✅ ADMIN |
| POST | `/api/upload/resume` | Upload resume | ✅ |

---

## 📁 Project Structure

```
src/main/
├── java/com/hiringms/
│   ├── HiringManagementApplication.java   ← Main entry point
│   ├── config/                            ← Security & exception handling
│   ├── controller/                        ← REST API controllers
│   ├── dto/                               ← Request/Response DTOs
│   ├── model/                             ← JPA entities
│   ├── repository/                        ← Data access layer
│   ├── security/                          ← JWT filter & utilities
│   └── service/                           ← Business logic
├── resources/
│   ├── application.properties             ← Config (env-driven)
│   ├── application-azure.properties       ← Azure profile
│   └── static/                            ← Frontend UI
│       ├── index.html
│       ├── css/style.css
│       ├── js/app.js
│       └── pages/                         ← Login, Register, Dashboards
```

---

## ⚙️ Environment Configuration

All config is driven by environment variables (see `.env` files):

| Variable | Default | Description |
|----------|---------|-------------|
| `SERVER_PORT` | 5000 | Server port |
| `DB_URL` | jdbc:h2:mem:hiringdb | Database URL |
| `JWT_SECRET` | (dev key) | JWT signing secret |
| `SPRING_PROFILES_ACTIVE` | local | Active profile |

See [DEPLOYMENT.md](DEPLOYMENT.md) for Azure hosting instructions.

---

## 👤 Default Users

Register via API or UI. Example admin creation:
```bash
POST http://localhost:5000/api/auth/register
{
  "email": "admin@test.com",
  "password": "admin123",
  "fullName": "Admin User",
  "phone": "1234567890",
  "role": "ADMIN"
}
```

---

## 📄 License

Built for Conga Hackathon 2026.
