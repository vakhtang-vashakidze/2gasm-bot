# Stage 1: Build the application using Maven
FROM maven:3.8.6-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Run the application using JDK
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/target/rgsm-bot-0.0.1.jar /app/rgsm-bot-0.0.1.jar

EXPOSE 8080

# Command to run the JAR
CMD ["java", "-jar", "/app/rgsm-bot-0.0.1.jar"]