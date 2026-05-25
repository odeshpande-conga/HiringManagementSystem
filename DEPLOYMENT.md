# 🚀 HireFlow - Azure Deployment Guide

## Project Structure (Environment Files)

```
├── .env                  ← Default template (DO NOT commit secrets)
├── .env.local            ← Local development settings
├── .env.azure            ← Azure production settings
├── Dockerfile            ← Container build for Azure
├── .dockerignore         ← Excludes sensitive files from Docker
├── src/main/resources/
│   ├── application.properties         ← Base config (reads env vars)
│   └── application-azure.properties   ← Azure profile overrides
```

---

## 🏠 Local Development

No setup needed. Just run:
```bash
mvn spring-boot:run
```
The app starts with H2 in-memory DB on port 5000.

---

## ☁️ Azure Deployment (Hackathon Day)

### Option A: Azure App Service (Recommended - Easiest)

#### Step 1: Create Azure Resources
```bash
# Login to Azure
az login

# Create resource group
az group create --name rg-hireflow --location eastus

# Create App Service plan
az appservice plan create --name plan-hireflow --resource-group rg-hireflow --sku B1 --is-linux

# Create Web App (Java 17)
az webapp create --name hireflow-app --resource-group rg-hireflow --plan plan-hireflow --runtime "JAVA:17-java17"
```

#### Step 2: Create Azure SQL Database
```bash
# Create SQL Server
az sql server create --name hireflow-sql --resource-group rg-hireflow --admin-user sqladmin --admin-password <YourStrongPassword123!>

# Create Database
az sql db create --name hiringdb --server hireflow-sql --resource-group rg-hireflow --service-objective S0

# Allow Azure services to connect
az sql server firewall-rule create --server hireflow-sql --resource-group rg-hireflow --name AllowAzure --start-ip-address 0.0.0.0 --end-ip-address 0.0.0.0
```

#### Step 3: Configure App Settings (Environment Variables)
```bash
az webapp config appsettings set --name hireflow-app --resource-group rg-hireflow --settings \
  SPRING_PROFILES_ACTIVE=azure \
  SERVER_PORT=8080 \
  DB_URL="jdbc:sqlserver://hireflow-sql.database.windows.net:1433;database=hiringdb;encrypt=true;trustServerCertificate=false;" \
  DB_USERNAME=sqladmin \
  DB_PASSWORD=<YourStrongPassword123!> \
  JWT_SECRET=<GenerateA64CharRandomString> \
  CORS_ALLOWED_ORIGINS=https://hireflow-app.azurewebsites.net
```

#### Step 4: Deploy
```bash
# Build the JAR
mvn clean package -DskipTests

# Deploy JAR to Azure
az webapp deploy --name hireflow-app --resource-group rg-hireflow --src-path target/hiring-management-system-1.0-SNAPSHOT.jar --type jar
```

#### Step 5: Verify
```
https://hireflow-app.azurewebsites.net
```

---

### Option B: Azure Container (Docker)

```bash
# Build Docker image
docker build -t hireflow:latest .

# Tag for Azure Container Registry
az acr create --name hireflowacr --resource-group rg-hireflow --sku Basic
az acr login --name hireflowacr
docker tag hireflow:latest hireflowacr.azurecr.io/hireflow:latest
docker push hireflowacr.azurecr.io/hireflow:latest

# Deploy to App Service from ACR
az webapp create --name hireflow-app --resource-group rg-hireflow --plan plan-hireflow \
  --deployment-container-image-name hireflowacr.azurecr.io/hireflow:latest
```

---

## 🔑 Environment Variables Reference

| Variable | Local Default | Azure |
|----------|--------------|-------|
| `SERVER_PORT` | 5000 | 8080 |
| `DB_URL` | jdbc:h2:mem:hiringdb | Azure SQL connection string |
| `DB_DRIVER` | org.h2.Driver | com.microsoft.sqlserver.jdbc.SQLServerDriver |
| `DB_USERNAME` | sa | Your Azure SQL admin |
| `DB_PASSWORD` | (empty) | Your Azure SQL password |
| `DB_PLATFORM` | H2Dialect | SQLServerDialect |
| `JWT_SECRET` | mySecretKey... | Strong random 64-char string |
| `H2_CONSOLE_ENABLED` | true | false |
| `SPRING_PROFILES_ACTIVE` | (not set) | azure |

---

## ⚡ Quick Deploy Checklist (Hackathon Day)

- [ ] Azure subscription ready
- [ ] `az login` done
- [ ] Create resource group
- [ ] Create App Service + SQL DB
- [ ] Set environment variables in Azure portal
- [ ] `mvn clean package -DskipTests`
- [ ] Deploy JAR via `az webapp deploy`
- [ ] Test: `https://your-app.azurewebsites.net`
- [ ] Register admin user via API
- [ ] Done! 🎉

