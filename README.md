# Spring Boot with Clean Architecture Example

A practical example demonstrating Clean Architecture implementation using Java and Spring Boot.

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [Architecture Overview](#architecture-overview)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Design Decisions](#design-decisions)
- [Advantages](#advantages)
- [Disadvantages](#disadvantages)
- [Technologies](#technologies)

## 🎯 About the Project

This project serves as a reference implementation of Clean Architecture principles in a Spring Boot application. It demonstrates how to structure a Java application to achieve separation of concerns, with business rules completely isolated from framework and infrastructure details.

## 🏗️ Architecture Overview

The project follows Clean Architecture (also known as Hexagonal Architecture or Ports and Adapters) with the following layers:

```
├── domain/              # Enterprise Business Rules (entities)
├── application/         # Application Business Rules
│   ├── ports/
│   │   ├── in/         # Input Ports (use cases interfaces)
│   │   │   └── input/  # Input DTOs
│   │   └── out/        # Output Ports (repository interfaces)
│   │       └── output/ # Output DTOs
│   └── usecase/        # Use Case Implementations
├── infra/              # Infrastructure & Framework Details
│   ├── persistence/    # JPA entities and repositories
│   ├── security/       # Security implementations
│   └── config/         # Spring configurations
└── interfaces/         # Interface Adapters (Controllers)
    └── controllers/    # REST Controllers
```

**Key principle**: The `application` and `domain` layers can be extracted and moved to another framework without any changes, along with their unit tests.

## 📁 Project Structure

### Domain Layer
Contains pure business entities with no framework dependencies.

### Application Layer
- **Ports In**: Define use case interfaces that the application exposes
- **Ports Out**: Define interfaces for external dependencies (repositories, services)
- **UseCases**: Implement business logic using ports
- **Input/Output packages**: Separate folders for better visual organization of DTOs

### Infrastructure Layer
Implements the output ports using specific technologies (JPA, security frameworks, etc.)

### Interfaces Layer
Implements the input ports through REST controllers

## 🚀 Getting Started

### Prerequisites

- Java 23+
- Maven 3.9+
- Docker (for running dependencies)

### Running the Application

1. **Start PostgreSQL using Docker:**

```bash
docker run -d \
  --name intranet-postgres \
  -e POSTGRES_DB=intranet \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:16-alpine
```

2. **Run the application:**

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`


## 🎨 Design Decisions

### Extra Input/Output Packages

An additional layer of packages (`input/` and `output/`) was added to organize DTOs. While not strictly required by Clean Architecture, this improves visual organization and code readability.

### Reusing Input/Output Objects

**Controversial but pragmatic decision**: Input and Output objects are reused across layers (In/Out interfaces) to avoid overcoding.

**Rationale**: Creating separate DTOs for each layer boundary often leads to unnecessary boilerplate code. New DTOs should only be created when:
- There's a genuine business difference
- Attributes differ between layers
- The client specifically requests it (or your team lead insists 😄)

This approach balances Clean Architecture principles with practical development efficiency.

### Using Java Records

**Modern Java approach**: The use of Java 17+ enabled the adoption of Records, eliminating the need for classes with Lombok in the `application` and `domain` layers.

**Benefits**:
- Immutable data carriers by default
- Concise syntax without boilerplate (no getters, equals, hashCode, toString)
- Perfect fit for DTOs and domain entities
- Keeps the business layers clean and framework-independent (no Lombok annotations needed)

## ✅ Advantages

### 1. Technology-Independent Business Rules
The most significant advantage: business logic in `application` and `domain` layers is completely isolated from frameworks and libraries.

**Practical benefit**: You can extract these layers (with their unit tests) and migrate to a different framework (Quarkus, Micronaut, etc.) with minimal effort.

### 2. Testability
Business rules can be tested without spinning up the framework or database.

### 3. Flexibility
Easy to swap infrastructure implementations (e.g., PostgreSQL → MongoDB) without touching business logic.

### 4. Clear Boundaries
Each layer has a well-defined responsibility and dependency direction.

## ⚠️ Disadvantages

### 1. Manual Rule Implementation
Business rules and validations must be written as close to plain Java as possible, avoiding framework-specific libraries.

**Example**: Instead of using Bean Validation annotations (`@NotNull`, `@Email`), validations are implemented manually in the Input objects.

### 2. Manual Attribute Management
The business layer becomes responsible for populating attributes that frameworks could handle automatically.

**Example**: Generating UUIDs and timestamps manually in use cases instead of relying on JPA's `@GeneratedValue` or `@CreatedDate`.

### 3. Risk of Overcoding
Requires extra care to avoid unnecessarily replicating code between layers. Balance is key—too much isolation leads to maintenance overhead.

### 4. Learning Curve
Team members need to understand and follow architectural principles consistently.

## 🛠️ Technologies

- **Java 25**
- **Spring Boot 3.5.7**
- **Spring Security** (JWT authentication)
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**

## 📝 API Endpoints

### Authentication
- `POST /auth/signup` - Create new user
- `POST /auth/login` - Authenticate user

### Posts
- `POST /posts` - Create new post
- `GET /posts` - List user posts
- `GET /posts/{id}` - List user posts

## 🤝 Contributing

This is an educational project. Feel free to fork and adapt to your needs.

## 📄 License

This project is available under the MIT License.