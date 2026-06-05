# JobTracker Fullstack

A full-stack web application for tracking job applications. Register, log in, and manage your job search from a single dashboard.

## Features

- User registration and JWT-based authentication
- Create, view, and delete job applications
- Track application status: `APPLIED`, `INTERVIEW`, `OFFER`, `REJECTED`
- Per-user data isolation (users only see their own applications)

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | React 19, React Router 7 |
| Backend | Spring Boot 4, Java 21 |
| Database | MySQL 8 |
| Auth | JWT (HMAC-SHA256, 24 h expiry) |
| ORM | Spring Data JPA / Hibernate |
| Build | Maven (backend), npm / Create React App (frontend) |

## Project Structure

```
JobTracker Fullstack/
├── jobtracker/               # Spring Boot backend
│   ├── src/main/java/com/jobtracker/
│   │   ├── controller/       # REST endpoints
│   │   ├── service/          # Business logic
│   │   ├── repository/       # Spring Data JPA repos
│   │   ├── entity/           # JPA entities & enums
│   │   ├── dto/              # Request/response DTOs
│   │   ├── security/         # JWT filter, Spring Security config
│   │   └── exception/        # Global error handling
│   └── src/main/resources/
│       └── application.yml   # App configuration
└── jobtracker-frontend/      # React frontend
    └── src/
        ├── pages/            # LoginPage, DashboardPage, AddJobPage
        └── App.js            # Router setup
```

## Prerequisites

- Java 21
- Maven (or use the included `mvnw` wrapper)
- Node.js 18+ and npm
- MySQL 8

## Database Setup

```sql
CREATE DATABASE jobtracker;
CREATE USER 'jobtracker'@'localhost' IDENTIFIED BY 'jobtracker123';
GRANT ALL PRIVILEGES ON jobtracker.* TO 'jobtracker'@'localhost';
FLUSH PRIVILEGES;
```

The schema is managed by Hibernate. Set `ddl-auto: create` on first run to generate tables, then switch back to `none`.

## Running Locally

### 1. Backend

```bash
cd jobtracker
./mvnw spring-boot:run        # Linux/macOS
mvnw.cmd spring-boot:run      # Windows
```

The API starts on `http://localhost:8080`.

### 2. Frontend

```bash
cd jobtracker-frontend
npm install
npm start
```

The app opens at `http://localhost:3000`.

## API Reference

### Auth

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users/register` | Register a new user |
| POST | `/api/users/login` | Log in and receive a JWT |

### Job Applications (requires `Authorization: Bearer <token>`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/jobs` | List all jobs for the current user |
| POST | `/api/jobs` | Add a new job application |
| GET | `/api/jobs/{id}` | Get a single application |
| DELETE | `/api/jobs/{id}` | Delete an application |

## Configuration

Backend configuration lives in [`jobtracker/src/main/resources/application.yml`](jobtracker/src/main/resources/application.yml).

| Setting | Default | Description |
|---------|---------|-------------|
| `spring.datasource.url` | `jdbc:mysql://127.0.0.1:3306/jobtracker` | MySQL connection URL |
| `spring.datasource.username` | `jobtracker` | DB username |
| `spring.datasource.password` | `jobtracker123` | DB password |

> **This project is for learning and portfolio purposes only. It is not intended for production use.**

## Available Scripts

### Backend

```bash
mvnw clean install    # Build
mvnw spring-boot:run  # Run
mvnw test             # Test
```

### Frontend

```bash
npm start        # Development server
npm run build    # Production build
npm test         # Run tests
```
