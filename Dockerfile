# Etapa 1: build con Maven usando Java 17
FROM eclipse-temurin:21 AS build
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests
RUN jar tf target/*.jar | grep mysql

# Etapa 2: run con JDK
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
