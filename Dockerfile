FROM openjdk:21-jdk-slim 

ARG JAR_FILE=target/filmood-back-1.0.jar

COPY ${JAR_FILE} filmood-back.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "filmood-back.jar" ]