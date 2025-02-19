# Build stage
FROM gradle:7.6.4-jdk11 AS builder
WORKDIR /app
COPY . .
RUN gradle build -x test

# Runtime stage
FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx512m", "-Xms256m", "-jar", "app.jar"]