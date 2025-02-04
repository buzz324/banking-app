# Banking Web App

This project is a simple banking web application that uses a PostgreSQL database and a Java backend. The application allows users to view their accounts and perform transfers between accounts.

## Project Structure

- `Dockerfile`: Defines the Docker image for the Java application.
- `docker-compose.yml`: Defines the services for PostgreSQL and the Java application.
- `src/`: Contains the source code for the front-end code.
- `com/banking/bank_web_app/`: Contains the Java application for the application.

## Prerequisites

- Docker
- Docker Compose

## Getting Started

### Step 1

Open your terminal or command prompt and navigate to the project directory. Then run the following command to build and start the Docker Compose environment:

```sh
docker-compose up --build
