FROM eclipse-temurin:23-jdk-alpine

ARG JAR_FILE=*.jar

COPY ./target/${JAR_FILE} application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]