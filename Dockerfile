# Etapa 1: Build e testes
FROM maven:3.8.8-eclipse-temurin-17 AS build

COPY pom.xml /app
COPY src /app/src

WORKDIR /app

# Executa os testes durante o build
RUN mvn clean install

# Etapa 2: Imagem final com JAR pronto
FROM openjdk:17-jdk-alpine

# Copia o JAR gerado na etapa anterior
COPY --from=build /app/target/*.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
