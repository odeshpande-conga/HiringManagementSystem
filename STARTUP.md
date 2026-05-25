# ============================================================
#   💼 HireFlow - Hiring Management System
#   STARTUP GUIDE
# ============================================================

## ⚡ QUICK START (One Click)

### Windows:
Double-click `start.bat` — that's it!

### Mac/Linux:
```bash
chmod +x start.sh
./start.sh
```

---

## 📋 Prerequisites

| Requirement | Version | Download |
|-------------|---------|----------|
| Java JDK | 17 or higher | https://adoptium.net/ |
| Apache Maven | 3.8+ | https://maven.apache.org/download.cgi |

> **Note:** Make sure `java` and `mvn` are available in your system PATH.

---

## 🔗 Important URLs (after startup)

| Page | URL |
|------|-----|
| 🏠 **Homepage** | http://localhost:5000 |
| 🔑 **Login** | http://localhost:5000/pages/login.html |
| 📝 **Register** | http://localhost:5000/pages/register.html |
| 💼 **Browse Jobs** | http://localhost:5000/pages/jobs.html |
| 👤 **Candidate Dashboard** | http://localhost:5000/pages/candidate-dashboard.html |
| 📋 **Recruiter Dashboard** | http://localhost:5000/pages/recruiter-dashboard.html |
| ⚙️ **Admin Panel** | http://localhost:5000/pages/admin.html |
| 🗄️ **H2 Database Console** | http://localhost:5000/h2-console |

### H2 Console Login:
- **JDBC URL:** `jdbc:h2:mem:hiringdb`
- **Username:** `sa`
- **Password:** *(leave empty)*

---

## 👤 Creating Users

Since this uses an in-memory database, you must register users after each startup.

### Register Admin (via API or Postman):
```
POST http://localhost:5000/api/auth/register
Content-Type: application/json

{
  "email": "admin@test.com",
  "password": "admin123",
  "fullName": "Admin User",
  "phone": "1234567890",
  "role": "ADMIN"
}
```

### Register Recruiter:
```
POST http://localhost:5000/api/auth/register
Content-Type: application/json

{
  "email": "recruiter@test.com",
  "password": "pass123",
  "fullName": "Test Recruiter",
  "phone": "9876543210",
  "role": "RECRUITER"
}
```

### Register Candidate:
Use the UI at http://localhost:5000/pages/register.html

---

## 🛑 Stopping the Server

- Press `Ctrl + C` in the terminal/command prompt
- Or close the terminal window

---

## 🔧 Manual Startup (if .bat doesn't work)

```bash
# 1. Open terminal in project folder
cd HiringManagementSystem

# 2. Set JAVA_HOME (Windows - adjust path to your JDK)
set JAVA_HOME=C:\Program Files\Java\jdk-18

# 3. Run the application
mvn spring-boot:run
```

---

## ❓ Troubleshooting

| Problem | Solution |
|---------|----------|
| Java not found | Install JDK 17+ and add to PATH |
| Maven not found | Install Maven and add bin folder to PATH |
| Port 5000 in use | Run: `netstat -ano | findstr :5000` then `taskkill /PID <pid> /F` |
| Release 17 not supported | Your Java is too old. Install JDK 17+ |
| Access denied (403) | Register a user first, then login |

