# Stage 1: Build the application using Maven
FROM maven:3.8.6-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Run the application using Tomcat
FROM tomcat:10-jdk17

WORKDIR /usr/local/tomcat

RUN rm -rf webapps/*

COPY --from=builder /app/target/rgsm-bot-0.0.1.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]