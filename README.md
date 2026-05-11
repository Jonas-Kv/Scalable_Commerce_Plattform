# Scalable E-Commerce Microservices Platform

## Overview

This project is a **scalable e-commerce backend platform** built using a **microservices architecture with Spring Boot**.
It is built as a production-ready backend system that can be extended into a fully functional application, with a strong focus on scalability, modularity, and clean separation of concerns.

The system is structured around independently deployable services, each responsible for a specific domain.

---

# Final Architecture (Target State)

The finished system consists of multiple microservices:

* **API Gateway** – central entry point, routing, and security
* **Auth Service** – user authentication & JWT-based authorization
* **Product Service** – product management
* **Order Service** – order processing
* **Payment Service** – payment handling
* **Notification Service** – event-based communication (e.g. emails)
* **User Service** - profile information management
---

##  Security

Authentication and authorization are handled using **JWT (JSON Web Tokens)**:

* Users authenticate via the Auth Service
* The API Gateway validates JWTs for every incoming request
* Services trust validated requests from the Gateway

---


##  Data Management

* Each microservice has its **own dedicated database**
* No direct database sharing between services
* Ensures loose coupling and independent scalability

---

##  Infrastructure

The entire system is containerized using Docker:

* Each service runs in its own container
* Services communicate via a shared Docker network
* Central orchestration is handled through `docker-compose`

---

##  API Gateway

Acts as the single entry point:

* Routes requests to appropriate services
* Handles authentication (JWT validation)
* Provides a clean external API

---

#  Technologies

* Java + Spring Boot
* Spring Security (JWT-based authentication)
* Docker & Docker Compose
* PostgreSQL
* (Planned) Apache Kafka for event-driven architecture

---

#  Current Project Status

##  Completed

* Microservice architecture fully designed
* Separate Spring Boot applications created for each service
* Each service has:

  * its own **Dockerfile**
  * its own **environment configuration (.env)**
  * its own **dependency setup**
* API Gateway service created and containerized
* Central **docker-compose.yml** for orchestrating all services

---

##  In Progress / Next Steps

The current state represents the **infrastructure and architectural foundation**.

The following components are planned:

* Implementation of business logic for each service
* REST endpoints (Controllers, Services, DTOs)
* JWT authentication flow (login, token generation, validation)
* Inter-service communication (REST + Kafka)
* Database schema design and persistence logic
* Error handling and resilience patterns

---

#  Purpose of This Project

This project is designed to:

* Deeply understand microservice architecture
* Learn real-world backend system design
* Gain hands-on experience with distributed systems
* Build production-like infrastructure from scratch

---

#  Summary

This repository already establishes a **production-ready foundation** for a scalable backend system:

* Independent services
* Containerized environment
* Clear architectural boundaries

The next phase focuses on transforming this structure into a **fully functional e-commerce platform with real business logic**.
