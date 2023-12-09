FROM openjdk:20-slim
RUN pwd
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} /app.jar
RUN ls -la
ENTRYPOINT ["java", "-jar", "/app.jar"]
