FROM openjdk:8
EXPOSE 9292
ARG JAR_FILE=target/tipodecambio-api.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]