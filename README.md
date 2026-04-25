# 💸 Secure Funds Transfer Application

## 📌 Overview
A full-stack web application that enables users to securely manage accounts and transfer funds.

The system includes authentication, authorization, transaction handling, and a complete deployment setup with Docker.

---

## 🚀 Live Demo

🔗 Frontend:  
https://secure-funds-transfer-frontend.vercel.app

👉 No setup required — you can use the app directly.

---

## 🔐 Features

- User registration & login
- JWT-based authentication & authorization
- Secure password hashing using BCrypt
- Fund transfer between accounts
- Transaction handling & balance updates
- Protected routes and API endpoints
- Error handling & loading states (frontend)
- HTTP Interceptors for auth & requests

---

## 🧪 Sample Credentials

**User 1**
- Username: `ghida`
- Password: `123456`
- Account: `ACC1001`

**User 2**
- Username: `aliaa`
- Password: `123456`
- Account: `ACC1002`

👉 Or create your own account (e.g. password: `Pa$$w0rd`)

---

## 🛠️ Tech Stack

### Frontend
- Angular
- TypeScript
- HTML / CSS

### Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Hibernate (JPA)

### Database
- PostgreSQL (Supabase)

### Deployment
- Frontend: Vercel
- Backend: Railway
- Dockerized Backend for easy deployment

---

## 🐳 Run with Docker

You can run the backend locally using Docker:

```bash
docker build -t secure-funds-backend .
docker run -p 8080:8080 secure-funds-backend
