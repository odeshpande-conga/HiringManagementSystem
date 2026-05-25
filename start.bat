@echo off
title HireFlow - Hiring Management System
color 0A

echo.
echo  ============================================================
echo       💼 HireFlow - Hiring Management System
echo  ============================================================
echo.

:: Check if Java is available
echo  [1/4] Checking Java installation...
where java >nul 2>&1
if %errorlevel% neq 0 (
    echo  ❌ ERROR: Java is not installed or not in PATH!
    echo  Please install Java 17+ from: https://adoptium.net/
    echo.
    pause
    exit /b 1
)

:: Try to find Java 17+
java -version 2>&1 | findstr /i "version" >nul
if %errorlevel% neq 0 (
    echo  ❌ ERROR: Could not determine Java version.
    pause
    exit /b 1
)

:: Check for JDK 17+ (try common locations)
if exist "C:\Program Files\Java\jdk-18" (
    set JAVA_HOME=C:\Program Files\Java\jdk-18
    echo  ✅ Found JDK 18 at: %JAVA_HOME%
) else if exist "C:\Program Files\Java\jdk-17" (
    set JAVA_HOME=C:\Program Files\Java\jdk-17
    echo  ✅ Found JDK 17 at: %JAVA_HOME%
) else if exist "C:\Program Files\Java\jdk-21" (
    set JAVA_HOME=C:\Program Files\Java\jdk-21
    echo  ✅ Found JDK 21 at: %JAVA_HOME%
) else if exist "C:\Program Files\Java\jdk-20" (
    set JAVA_HOME=C:\Program Files\Java\jdk-20
    echo  ✅ Found JDK 20 at: %JAVA_HOME%
) else if exist "C:\Program Files\Java\jdk-19" (
    set JAVA_HOME=C:\Program Files\Java\jdk-19
    echo  ✅ Found JDK 19 at: %JAVA_HOME%
) else (
    echo  ⚠️  Could not auto-detect JAVA_HOME. Using system default.
    echo     If build fails, set JAVA_HOME to your JDK 17+ path.
)

echo.

:: Check if Maven is available
echo  [2/4] Checking Maven installation...
where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo  ❌ ERROR: Maven is not installed or not in PATH!
    echo  Please install Maven from: https://maven.apache.org/download.cgi
    echo.
    pause
    exit /b 1
)
echo  ✅ Maven found.
echo.

:: Navigate to project directory
echo  [3/4] Setting up project...
cd /d "%~dp0"
echo  ✅ Working directory: %cd%
echo.

:: Kill any process on port 5000
echo  [4/4] Checking port 5000...
for /f "tokens=5" %%a in ('netstat -aon ^| findstr ":5000.*LISTENING"') do (
    echo  ⚠️  Port 5000 is in use (PID: %%a). Killing process...
    taskkill /PID %%a /F >nul 2>&1
)
echo  ✅ Port 5000 is available.
echo.

echo  ============================================================
echo   🚀 Starting HireFlow Application...
echo  ============================================================
echo.
echo   After startup, open your browser to:
echo.
echo     🌐 Homepage:         http://localhost:5000
echo     🔑 Login:            http://localhost:5000/pages/login.html
echo     📝 Register:         http://localhost:5000/pages/register.html
echo     💼 Jobs:             http://localhost:5000/pages/jobs.html
echo     🗄️  H2 Console:       http://localhost:5000/h2-console
echo.
echo   Press Ctrl+C to stop the server.
echo  ============================================================
echo.

:: Run Spring Boot
mvn spring-boot:run

:: If we get here, the app stopped
echo.
echo  ============================================================
echo   ⏹️  Application stopped.
echo  ============================================================
pause

