Research Project Tracker – Backend
Description

This is the backend API for the Research Project Tracker system.
It allows users to manage research projects, milestones, and documents with secure JWT-based authentication and role-based access control.

Key Features:

RESTful API built with Spring Boot

JWT-based authentication and stateless security

Role-based authorization (ADMIN, PI, MEMBER, VIEWER)

CRUD operations for Projects, Milestones, and Documents

MySQL database for data persistence

Tech Stack

Java, Spring Boot

Spring Security with JWT

MySQL

Maven

Entities

Project

id (String), title (String), summary (String)

status (Enum: PLANNING, ACTIVE, ON_HOLD, COMPLETED, ARCHIVED)

pi (User), tags (String)

startDate, endDate, createdAt, updatedAt

Milestone

id, project, title, description

dueDate (LocalDate), isCompleted (Boolean)

createdBy (User)

Document

id, project, title, description

urlOrPath, uploadedBy, uploadedAt

User

id, username, password, fullName

role (Enum: ADMIN, PI, MEMBER, VIEWER)

createdAt

Authentication & Authorization

Sign Up: /api/auth/signup (default role: MEMBER)

Sign In: /api/auth/login (returns JWT)

Roles:

ADMIN: Full system access

PI: Manage own projects and associated members

MEMBER: Create/update milestones, upload documents

VIEWER: Read-only access

Security Features:

Stateless JWT authentication

Password hashing with BCrypt

Role-based endpoint protection

Custom exception handling for unauthorized access

API Endpoints

Auth:

POST /api/auth/signup – Register new users

POST /api/auth/login – Login and receive JWT

Projects:

GET /api/projects

POST /api/projects

PUT /api/projects/{id}

DELETE /api/projects/{id}

Milestones:

GET /api/milestones

POST /api/milestones

PUT /api/milestones/{id}

DELETE /api/milestones/{id}

Documents:

GET /api/documents

POST /api/documents

PUT /api/documents/{id}

DELETE /api/documents/{id}

Setup Instructions

Clone this repository:

git clone <your-backend-repo-link>


Configure MySQL in application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/ResearchProjectTrackerCMJD110
spring.datasource.username=root
spring.datasource.password=#Luthmin2007


Build and run the backend:

mvn spring-boot:run


Backend API is available at: http://localhost:8044
