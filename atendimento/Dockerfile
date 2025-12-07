FROM eclipse-temurin:17-jdk-alpine
LABEL authors="Mateus"

WORKDIR /app

COPY target/atendimento-1.0-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
