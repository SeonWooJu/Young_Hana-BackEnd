FROM openjdk:20-slim
ARG JAR_FILE=Young_Hana-BackEnd-0.0.1-SNAPSHOT.jar
COPY ./target/${JAR_FILE} /${JAR_FILE}
ENTRYPOINT ["java", "-jar", "/${JAR_FILE}"]
