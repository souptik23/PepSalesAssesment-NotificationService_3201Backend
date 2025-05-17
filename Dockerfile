# Stage 1: Build the JAR using Maven
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Use a smaller JDK base image to run the JAR
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy the built JAR from stage 1
COPY --from=build /app/target/Notification-0.0.1-SNAPSHOT.jar app.jar

# Create non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Expose port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "-XX:+UseContainerSupport", "/app.jar"]
