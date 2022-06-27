FROM openjdk:11-jre-slim

LABEL maintainer="javi_1986@hotmail.com"

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} producer-service.jar
ENTRYPOINT ["java", "-jar", "producer-service.jar"]