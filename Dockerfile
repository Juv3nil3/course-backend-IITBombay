# Use the official OpenJDK 17 as the base image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /src

# Copy the JAR file into the container at
COPY target/course-management-0.0.1-SNAPSHOT.jar /app.jar

# Expose port 8080 for the Spring Boot application
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "/app.jar"]
