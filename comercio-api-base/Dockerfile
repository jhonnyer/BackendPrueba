# Dockerfile content would go here
# Etapa 1: Build
FROM maven:3.8.5-openjdk-11-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/comercio-api-1.0.0.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
