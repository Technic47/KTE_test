FROM eclipse-temurin:19-jdk-alpine as builder
MAINTAINER Pavel Kuznetsov
WORKDIR /app

COPY .mvn .mvn
COPY mvnw ./
COPY pom.xml ./
COPY src/ src

RUN ./mvnw clean package spring-boot:repackage

FROM eclipse-temurin:19-jre-alpine
WORKDIR /app

COPY --from=builder /app/target/soap-0.5.jar /app/app.jar

EXPOSE 8086
ENTRYPOINT ["java", "-jar", "/app/app.jar"]