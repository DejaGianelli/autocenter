# Autocenter Management System (Spring Boot)

This project is a personal application simulating an REST API for an Autocenter Management System. The goal of this project is not only to create a functional application but also to serve as a model for testing and implementing best practices in various areas such as:

- **Architecture Design**
- **Database Modeling**
- **Object-Oriented Programming (OOP)**
- **Domain-Driven Design (DDD)**

## Project Overview

The Autocenter Management System is built using **Spring Boot** and simulates the core functionalities needed to manage an autocenter. While the project focuses on the functional aspect of an Autocenter, its main purpose is to experiment with modern development techniques, design patterns, and project structuring.

### Key Features:
- Simulated operations for managing clients, vehicles, and services
- Application architecture based on principles like SOLID, DDD, and clean code
- Well-structured code and layered architecture
- Sample database schema and relations

## Goals of the Project
The primary goal is to apply and experiment with the following concepts:

- **Good practices in architectural design**: The project aims to establish an architecture with well established principles and separation of concerns (without over-engineering).
- **Database modeling**: Through a well-thought-out database schema and relationships, we aim to create a robust and scalable database structure.
- **Object-Oriented Programming (OOP)**: Implementing OOP principles like inheritance, polymorphism, encapsulation, and abstraction to organize the codebase.
- **Domain-Driven Design (DDD)**: Structuring the application around the domain and using DDD principles to design the system's core logic.

## Technology Stack

- **Spring Boot**: The main framework used to build the backend of the application.
- **Spring Data JPA**: For database access and persistence.
- **Test Containers**: For integration testing purposes.
- **JUnit & Mockito**: For unit testing and mocking dependencies.
- **MySQL**: As the relational database.

## How to Run the Project

1. Clone this repository to your local machine:

    ```bash
    git clone git@github.com:DejaGianelli/autocenter.git
    ```

2. Navigate to the project folder:

    ```bash
    cd autocenter
    ```

3. Build the project using Maven or Gradle:

    ```bash
    ./mvnw clean install
    ```

4. Run the application:

    ```bash
    ./mvnw spring-boot:run
    ```

The application will be accessible at `http://localhost:8080`.

## Contributing

Since this is a personal project, contributions are welcome if you have suggestions to improve the architecture, design, or codebase. Feel free to fork this repository and submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

