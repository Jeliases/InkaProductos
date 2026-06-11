# Stage 1: Construcción (usa Maven y Java 17)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Ejecución (usa solo el JRE)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8082
# Este flag fuerza la codificación UTF-8 en la JVM
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]