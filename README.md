# Task-Management-System
Task Management System is a web application designed to help users organize and track their tasks effectively.

# Front-end
Angular - `16.2.3`

Node - `16.14.2`

Package Manager - npm - `8.5.0`.

To add angular material  -> ng add @angular/material

# Backend
Java - `17`

Spring Boot - `3.2.5`

Gradle - `8.7`

# Database
MySQL

# Angular commands
`ng g c login` - for generating login components- (html, scss, ts, spec.ts).
`ng g s auth` - for generating auth service - (ts, spec.ts).
`ng g m admin` - for generating admin module - (ts).
`ng g m admin --routing=true` - for generating admin routing module - (module ts + routing module ts).
`ng serve` - compile angular application and start a development server

# Docker File
we can create Docker file for springboot app

-- Use a base image with Java runtime
FROM openjdk:17
-- Set the working directory inside the container
WORKDIR /app

-- Copy the JAR file into the container
COPY build/libs/Task-management.jar /app/Task-management-app.jar

-- Specify the command to run the JAR file
ENTRYPOINT ["java", "-jar", "Task-management-app.jar"]
