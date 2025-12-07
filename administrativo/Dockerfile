# Use uma imagem base leve com Java 17
FROM eclipse-temurin:17-jdk-alpine

# Rótulo para identificar o autor/mantenedor da imagem
LABEL authors="Mateus"

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Argumento para o nome do JAR, facilitando a manutenção
ARG JAR_FILE=target/administrativo-1.0-SNAPSHOT.jar

# Copia o JAR para o contêiner
COPY ${JAR_FILE} app.jar

# Expõe a porta que a aplicação usará
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
