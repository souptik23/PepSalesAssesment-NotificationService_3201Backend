# Use Eclipse Temurin JDK 21 (Alpine for minimal size)
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy ONLY the built JAR (faster builds)
COPY target/Notification-0.0.1-SNAPSHOT.jar app.jar

# Run as non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Expose port (Spring Boot default is 8080)
EXPOSE 8080

# Optimized JVM flags for containers
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "-XX:+UseContainerSupport", "/app.jar"]