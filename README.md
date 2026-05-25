# Quiz App Microservices Architecture

A production-grade scalable Quiz Application built using **Spring Boot Microservices Architecture** with **React Frontend**, **Spring Cloud**, **Docker**, **Kubernetes**, **Kafka**, **Redis**, **JWT Authentication**, **Monitoring**, and **CI/CD Pipelines**.

---

# 🚀 Features

## Authentication & Security
- JWT Authentication
- Role-Based Authorization (ADMIN / USER)
- Spring Security
- API Gateway Security
- Password Encryption
- Refresh Token Support
- Rate Limiting
- CORS Configuration

---

## Quiz Features
- Quiz Creation & Management
- Dynamic Question Bank
- Category-Based Quizzes
- Difficulty Levels
- Timer-Based Quiz
- Auto Evaluation
- Quiz Result Analytics
- Leaderboard System
- User Progress Tracking
- Quiz History

---

## Production Features
- API Gateway
- Service Discovery
- Centralized Configurations
- Distributed Tracing
- Monitoring & Logging
- Event-Driven Architecture
- Dockerized Services
- Kubernetes Deployment
- CI/CD Pipelines
- Health Checks
- Fault Tolerance
- Circuit Breaker

---

# 🏗️ Microservices Architecture

```text
                    ┌─────────────────────┐
                    │   React Frontend    │
                    │  quiz-ui-client     │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │    API Gateway      │
                    │ Spring Cloud Gateway│
                    └──────────┬──────────┘
                               │
      ┌────────────────────────┼────────────────────────┐
      ▼                        ▼                        ▼

┌──────────────┐      ┌──────────────┐       ┌──────────────┐
│ Auth Service │      │ Quiz Service │       │QuestionService│
└──────┬───────┘      └──────┬───────┘       └──────┬───────┘
       │                     │                      │
       ▼                     ▼                      ▼

 ┌──────────┐          ┌──────────┐           ┌──────────┐
 │ Auth DB  │          │ Quiz DB  │           │QuestionDB│
 └──────────┘          └──────────┘           └──────────┘


        ┌──────────────────────────────────────────┐
        │      Eureka Service Discovery            │
        └──────────────────────────────────────────┘

        ┌──────────────────────────────────────────┐
        │        Spring Cloud Config Server        │
        └──────────────────────────────────────────┘

        ┌──────────────────────────────────────────┐
        │       Kafka / RabbitMQ Messaging         │
        └──────────────────────────────────────────┘

        ┌──────────────────────────────────────────┐
        │ Prometheus + Grafana + Zipkin Monitoring │
        └──────────────────────────────────────────┘
```

---

# 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 21 + Spring Boot 3 |
| Frontend | React + Vite |
| Security | Spring Security + JWT |
| API Gateway | Spring Cloud Gateway |
| Service Discovery | Eureka Server |
| Config Server | Spring Cloud Config |
| Messaging | Kafka / RabbitMQ |
| Database | PostgreSQL / MySQL / MongoDB |
| Cache | Redis |
| Monitoring | Prometheus + Grafana |
| Distributed Tracing | Zipkin |
| Containerization | Docker |
| Orchestration | Kubernetes |
| CI/CD | GitHub Actions / Jenkins |
| Logging | ELK Stack |
| Documentation | Swagger / OpenAPI |

---

# 📂 Production Grade Project Structure

```text
quiz-app-microservices/
│
├── .github/
│   └── workflows/
│       ├── backend-ci.yml
│       ├── frontend-ci.yml
│       ├── docker-build.yml
│       └── kubernetes-deploy.yml
│
├── docs/
│   ├── architecture/
│   ├── api-docs/
│   ├── deployment/
│   └── database/
│
├── infrastructure/
│   ├── docker/
│   │   ├── docker-compose.yml
│   │   ├── mysql/
│   │   ├── postgres/
│   │   ├── mongodb/
│   │   ├── kafka/
│   │   ├── redis/
│   │   ├── prometheus/
│   │   ├── grafana/
│   │   └── zipkin/
│   │
│   ├── kubernetes/
│   │   ├── gateway/
│   │   ├── services/
│   │   ├── monitoring/
│   │   └── ingress/
│   │
│   └── terraform/
│
├── backend/
│   │
│   ├── service-registry/
│   │   ├── src/
│   │   ├── Dockerfile
│   │   └── pom.xml
│   │
│   ├── config-server/
│   │   ├── src/
│   │   ├── Dockerfile
│   │   └── pom.xml
│   │
│   ├── api-gateway/
│   │   ├── src/
│   │   ├── Dockerfile
│   │   └── pom.xml
│   │
│   ├── auth-service/
│   │   ├── src/
│   │   ├── Dockerfile
│   │   └── pom.xml
│   │
│   ├── user-service/
│   │   ├── src/
│   │   ├── Dockerfile
│   │   └── pom.xml
│   │
│   ├── quiz-service/
│   │   ├── src/
│   │   ├── Dockerfile
│   │   └── pom.xml
│   │
│   ├── question-service/
│   │   ├── src/
│   │   ├── Dockerfile
│   │   └── pom.xml
│   │
│   ├── result-service/
│   │   ├── src/
│   │   ├── Dockerfile
│   │   └── pom.xml
│   │
│   ├── notification-service/
│   │   ├── src/
│   │   ├── Dockerfile
│   │   └── pom.xml
│   │
│   ├── analytics-service/
│   │   ├── src/
│   │   ├── Dockerfile
│   │   └── pom.xml
│   │
│   ├── common-lib/
│   │   ├── src/
│   │   └── pom.xml
│   │
│   └── config-repo/
│       ├── api-gateway.yml
│       ├── auth-service.yml
│       ├── quiz-service.yml
│       └── question-service.yml
│
├── frontend/
│   └── quiz-ui-client/
│       ├── public/
│       ├── src/
│       ├── Dockerfile
│       ├── nginx.conf
│       └── package.json
│
├── scripts/
│   ├── start-all.sh
│   ├── stop-all.sh
│   ├── backup-db.sh
│   └── deploy.sh
│
├── .env
├── .gitignore
├── .gitattributes
├── README.md
└── LICENSE
```

---

# 🔥 Microservices Description

| Service | Description |
|---|---|
| service-registry | Eureka Server for service discovery |
| config-server | Centralized configuration management |
| api-gateway | Entry point for all APIs |
| auth-service | Authentication & JWT |
| user-service | User management |
| quiz-service | Quiz CRUD operations |
| question-service | Question management |
| result-service | Result calculation & history |
| notification-service | Email/SMS notifications |
| analytics-service | Reports & analytics |

---

# 🗄️ Database Design

| Service | Database |
|---|---|
| auth-service | PostgreSQL |
| user-service | PostgreSQL |
| quiz-service | MySQL |
| question-service | MongoDB |
| result-service | PostgreSQL |
| analytics-service | Elasticsearch |

---

# 🔄 Communication Pattern

## Synchronous Communication
- REST APIs
- OpenFeign Client

## Asynchronous Communication
- Kafka
- RabbitMQ

---

# ⚡ API Gateway Responsibilities

- Authentication Validation
- Route Management
- Rate Limiting
- Load Balancing
- Logging
- Security Filtering

---

# 🔐 Security Architecture

## Authentication
- JWT Token Authentication
- Refresh Tokens
- Stateless Sessions

## Authorization
- Role-Based Access
- API-Level Security

## Production Security
- HTTPS
- Secret Management
- API Throttling
- Secure Headers

---

# 📡 Event Driven Architecture

Kafka Topics Example:

| Topic | Purpose |
|---|---|
| quiz-created | Quiz events |
| user-registered | User events |
| result-generated | Result processing |
| notification-topic | Notifications |

---

# 📈 Monitoring & Observability

| Tool | Purpose |
|---|---|
| Prometheus | Metrics collection |
| Grafana | Visualization dashboards |
| Zipkin | Distributed tracing |
| ELK Stack | Logging |
| Spring Boot Actuator | Health monitoring |

---

# 🐳 Docker Deployment

## Build All Services

```bash
docker-compose build
```

## Start All Containers

```bash
docker-compose up -d
```

## Stop Containers

```bash
docker-compose down
```

---

# ☸️ Kubernetes Deployment

## Apply Kubernetes Configurations

```bash
kubectl apply -f infrastructure/kubernetes/
```

## Check Pods

```bash
kubectl get pods
```

## Check Services

```bash
kubectl get svc
```

---

# 🚀 Running Locally

## 1. Clone Repository

```bash
git clone https://github.com/your-username/quiz-app-microservices.git
cd quiz-app-microservices
```

---

## 2. Start Service Registry

```bash
cd backend/service-registry
mvn spring-boot:run
```

---

## 3. Start Config Server

```bash
cd backend/config-server
mvn spring-boot:run
```

---

## 4. Start API Gateway

```bash
cd backend/api-gateway
mvn spring-boot:run
```

---

## 5. Start All Microservices

```bash
cd backend/auth-service
mvn spring-boot:run
```

Repeat for all services.

---

## 6. Start Frontend

```bash
cd frontend/quiz-ui-client

npm install

npm run dev
```

---

# 🌐 Service URLs

| Service | URL |
|---|---|
| Eureka Server | http://localhost:8761 |
| API Gateway | http://localhost:9090 |
| Config Server | http://localhost:8888 |
| Grafana | http://localhost:3000 |
| Prometheus | http://localhost:9091 |
| Zipkin | http://localhost:9411 |

---

# 🔌 Default Service Ports

| Service | Port |
|---|---|
| Gateway | 9090 |
| Eureka | 8761 |
| Config Server | 8888 |
| Auth Service | 8081 |
| User Service | 8082 |
| Quiz Service | 8083 |
| Question Service | 8084 |
| Result Service | 8085 |
| Notification Service | 8086 |
| Analytics Service | 8087 |

---

# 📦 Dockerized Services

- API Gateway
- Eureka Server
- Config Server
- Auth Service
- Quiz Service
- User Service
- Question Service
- Result Service
- Analytics Service
- Notification Service
- React Frontend
- Kafka
- Redis
- MySQL
- MongoDB

---

# 📜 API Documentation

Swagger/OpenAPI available for all services:

```bash
http://localhost:8081/swagger-ui.html
```

Example:

```bash
http://localhost:8083/swagger-ui.html
```

---

# 🧪 Testing Strategy

## Unit Testing
- JUnit 5
- Mockito

## Integration Testing
- Testcontainers

## API Testing
- Postman
- Rest Assured

## Load Testing
- JMeter
- Gatling

---

# 🛡️ Resilience & Fault Tolerance

- Circuit Breaker (Resilience4j)
- Retry Mechanism
- Fallback Methods
- Timeout Handling
- Bulkhead Pattern

---

# ⚙️ CI/CD Pipeline

GitHub Actions Included:

- Backend Build
- Frontend Build
- Unit Testing
- Docker Image Build
- Docker Push
- Kubernetes Deployment

---

# 🧠 Future Enhancements

- AI Quiz Generator
- AI Question Recommendation
- RAG-based Question Generation
- Real-Time Quiz Battle
- WebSocket Leaderboard
- Voice-Based Quiz
- Payment Gateway
- Multi-Language Support
- Mobile Application

---

# 📋 Best Practices Followed

- DTO Pattern
- Layered Architecture
- Global Exception Handling
- API Versioning
- Centralized Logging
- Environment-Based Configuration
- SOLID Principles
- Clean Code
- Microservices Isolation
- Database Per Service

---

# 🏆 Recommended Production Tools

| Purpose | Tool |
|---|---|
| CI/CD | GitHub Actions |
| Container Registry | Docker Hub |
| Monitoring | Grafana |
| Logging | ELK Stack |
| Cloud | AWS / Azure |
| CDN | Cloudflare |

---

# 👨‍💻 Author

Dharmendra Kumar

---

# 📄 License

This project is licensed under the MIT License.

---

# ⭐ Support

If you like this project, give it a ⭐ on GitHub.