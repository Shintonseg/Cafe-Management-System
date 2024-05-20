# Use a base image with Java runtime
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/Task-management.jar /app/Task-management-app.jar

# Specify the command to run the JAR file
ENTRYPOINT ["java", "-jar", "Task-management-app.jar"]
