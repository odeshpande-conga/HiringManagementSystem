#!/bin/bash
echo ""
echo "  ============================================================"
echo "       HireFlow - Hiring Management System"
echo "  ============================================================"
echo ""
echo "  [1/4] Checking Java installation..."
if ! command -v java &> /dev/null; then
    echo "  ERROR: Java is not installed!"
    echo "  Install Java 17+: https://adoptium.net/"
    exit 1
fi
echo "  Java found."
echo ""
echo "  [2/4] Checking Maven installation..."
if ! command -v mvn &> /dev/null; then
    echo "  ERROR: Maven is not installed!"
    echo "  Install Maven: https://maven.apache.org/download.cgi"
    exit 1
fi
echo "  Maven found."
echo ""
echo "  [3/4] Setting up project..."
cd "$(dirname "$0")"
echo "  Working directory: $(pwd)"
echo ""
echo "  [4/4] Checking port 5000..."
if lsof -Pi :5000 -sTCP:LISTEN -t >/dev/null 2>&1; then
    echo "  Port 5000 in use. Killing existing process..."
    kill -9 $(lsof -Pi :5000 -sTCP:LISTEN -t) 2>/dev/null
fi
echo "  Port 5000 is available."
echo ""
echo "  ============================================================"
echo "   Starting HireFlow Application..."
echo "  ============================================================"
echo ""
echo "   Homepage:         http://localhost:5000"
echo "   Login:            http://localhost:5000/pages/login.html"
echo "   Register:         http://localhost:5000/pages/register.html"
echo "   Jobs:             http://localhost:5000/pages/jobs.html"
echo "   H2 Console:       http://localhost:5000/h2-console"
echo ""
echo "   Press Ctrl+C to stop the server."
echo "  ============================================================"
echo ""
mvn spring-boot:run
