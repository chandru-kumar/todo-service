FROM openjdk:17-jdk
ARG JAR_FILE=target/*.jar
COPY ./target/todo-service-0.0.1-SNAPSHOT.jar todo-app.jar
ENTRYPOINT ["java", "-jar", "/todo-app.jar"]